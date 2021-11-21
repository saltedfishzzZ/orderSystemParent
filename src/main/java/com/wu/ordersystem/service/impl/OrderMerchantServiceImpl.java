package com.wu.ordersystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ordersystem.common.Constants;
import com.wu.ordersystem.pojo.domain.resource.OrderMerchant;
import com.wu.ordersystem.repository.resource.OrderMerchantRepo;
import com.wu.ordersystem.service.OrderMerchantService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

@Service
public class OrderMerchantServiceImpl implements OrderMerchantService {
    private static final Logger logger = LoggerFactory.getLogger(OrderMerchantServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderMerchantRepo orderMerchantRepo;

    @Override
    public OrderMerchant getMerchantById(Long id) {
        String value = (String)
                stringRedisTemplate.boundHashOps(Constants.ORDER_MERCHANT_INFO_KEY).get(Long.toString(id));
        if (Objects.nonNull(value)) {
            try {
                return objectMapper.readValue(value, OrderMerchant.class);
            } catch (JsonProcessingException e) {
                logger.error("{}-----序列化商户json字符串失败: {}",
                        GenerateTimeUtil.generateNowTime(), e.getMessage());
            }
        }
        OrderMerchant orderMerchant = orderMerchantRepo.getById(id);
        try {
            String orderMerchantStr = objectMapper.writeValueAsString(orderMerchant);
            SessionCallback<Object> callback = new SessionCallback<>() {
                @Override
                public Object execute(RedisOperations redisOperations) throws  DataAccessException {
                    redisOperations.multi();
                    redisOperations.boundHashOps(Constants.ORDER_MERCHANT_INFO_KEY)
                            .put(Long.toString(id), orderMerchantStr);
                    redisOperations.expire(Constants.ORDER_MERCHANT_INFO_KEY,
                            Constants.ORDER_MERCHANT_INFO_TIME, TimeUnit.DAYS);
                    return redisOperations.exec();
                };
            };
            stringRedisTemplate.execute(callback);
        } catch (JsonProcessingException e) {
            logger.error("{}-----反序列化商户json字符串失败: {}",
                    GenerateTimeUtil.generateNowTime(), e.getMessage());
        }
        return orderMerchant;
    }

    @Override
    public void updateMerchantById(OrderMerchant orderMerchant) {
        // 先更新数据库, 再删除缓存
        OrderMerchant save = orderMerchantRepo.save(orderMerchant);
        stringRedisTemplate.opsForHash()
                .delete(Constants.ORDER_MERCHANT_INFO_KEY, Long.toString(save.getId()));
    }
}
