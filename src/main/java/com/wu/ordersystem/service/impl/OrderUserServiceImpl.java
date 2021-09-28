package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderUser;
import com.wu.ordersystem.repository.OrderUserRepo;
import com.wu.ordersystem.service.OrderUserService;
import com.wu.ordersystem.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

@Service
public class OrderUserServiceImpl implements OrderUserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private OrderUserRepo orderUserRepo;

    @Override
    public OrderUser queryUserByUsername(String username) {
        List<OrderUser> userList = orderUserRepo.findByUsername(username);

        if (Objects.nonNull(userList) && !userList.isEmpty()) {
            return userList.get(0);
        }

        return null;
    }

    @Override
    public String generateTokenByUsername(OrderUser user) {
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}
