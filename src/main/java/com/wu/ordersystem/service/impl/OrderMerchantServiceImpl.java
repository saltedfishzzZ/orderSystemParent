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
        String value
                = stringRedisTemplate.opsForValue().get(String.format(Constants.ORDER_MERCHANT_INFO_KEY, id));
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
            stringRedisTemplate.opsForValue().set(String.format(Constants.ORDER_MERCHANT_INFO_KEY, id),
                    objectMapper.writeValueAsString(orderMerchant),
                    Constants.ORDER_MERCHANT_INFO_TIME, TimeUnit.DAYS);
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
        stringRedisTemplate.delete(String.format(Constants.ORDER_MERCHANT_INFO_KEY, save.getId()));
    }
}
