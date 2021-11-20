package com.wu.ordersystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ordersystem.common.Constants;
import com.wu.ordersystem.pojo.domain.resource.OrderUser;
import com.wu.ordersystem.repository.resource.OrderUserRepo;
import com.wu.ordersystem.service.OrderUserService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import com.wu.ordersystem.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

@Service
public class OrderUserServiceImpl implements OrderUserService {

    private static final Logger logger = LoggerFactory.getLogger(OrderUserServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderUserRepo orderUserRepo;

    @Override
    public OrderUser queryUserByUsername(String username) {
        // 从缓存中获取用户信息
        Object value = stringRedisTemplate.opsForHash().get(Constants.ORDER_USER_KEY, username);

        if (Objects.nonNull(value)) {
            try {
                return objectMapper.readValue((String) value, OrderUser.class);
            } catch (JsonProcessingException e) {
                logger.error("{}-----{}用户缓存信息反序列化失败-----{}",
                        GenerateTimeUtil.generateNowTime(), username, e.getMessage());
            }
        }

        // 缓存没有命中, 从数据库中查询
        List<OrderUser> userList = orderUserRepo.findByUsername(username);

        if (Objects.nonNull(userList) && !userList.isEmpty()) {
            OrderUser orderUser = userList.get(0);
            // 缓存到redis中
            if (Objects.nonNull(orderUser)) {
                try {
                    stringRedisTemplate.multi();
                    stringRedisTemplate
                            .opsForHash()
                            .put(Constants.ORDER_USER_KEY, username,
                                    objectMapper.writeValueAsString(orderUser));
                    stringRedisTemplate.expire(Constants.ORDER_USER_KEY, Constants.ORDER_USER_TIME, TimeUnit.DAYS);
                    stringRedisTemplate.exec();
                } catch (JsonProcessingException e) {
                    logger.error("{}-----{}用户信息序列化失败-----{}",
                            GenerateTimeUtil.generateNowTime(), username, e.getMessage());
                }
            }
            return orderUser;
        }

        return null;
    }

    @Override
    public String getTokenFromCache(String username) {
        return (String) stringRedisTemplate.opsForHash().get(Constants.ORDER_USER_TOKEN_KEY, username);
    }

    @Override
    public String generateTokenByUsername(OrderUser user) {
        String token = jwtTokenUtil.generateToken(user);
        stringRedisTemplate.multi();
        stringRedisTemplate.opsForHash()
                .put(Constants.ORDER_USER_TOKEN_KEY, user.getUsername(), token);
        stringRedisTemplate.expire(Constants.ORDER_USER_TOKEN_KEY, Constants.ORDER_USER_TOKEN_TIME, TimeUnit.DAYS);
        stringRedisTemplate.exec();
        return token;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
