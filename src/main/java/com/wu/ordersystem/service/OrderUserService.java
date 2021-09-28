package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.OrderUser;
import org.springframework.stereotype.Service;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

public interface OrderUserService {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return user
     */
    OrderUser queryUserByUsername(String username);

    String generateTokenByUsername(OrderUser user);

    String getUsernameFromToken(String token);
}
