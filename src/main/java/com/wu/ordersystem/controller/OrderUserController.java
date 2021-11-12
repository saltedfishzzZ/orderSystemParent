package com.wu.ordersystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.domain.resource.OrderUser;
import com.wu.ordersystem.pojo.dto.resource.OrderUserDTO;
import com.wu.ordersystem.service.OrderUserService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

@RestController
@RequestMapping("/")
public class OrderUserController {

    private static final Logger logger = LoggerFactory.getLogger(OrderUserController.class);

    @Autowired
    private OrderUserService orderUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Valid @RequestBody OrderUserDTO orderUserDTO) throws JsonProcessingException {
        logger.info("{}-----请求登录接口", GenerateTimeUtil.generateNowTime());

        OrderUser orderUser = orderUserService.queryUserByUsername(orderUserDTO.getUsername());

        if (Objects.isNull(orderUser)) {
            return CommonResult.failed().message("用户不存在");
        }
        if (!orderUserDTO.getPassword().equals(orderUser.getPassword())) {
            return CommonResult.failed().message("密码错误");
        }
        String token = orderUserService.getTokenFromCache(orderUserDTO.getUsername());
        if (Objects.isNull(token)) {
            token = orderUserService.generateTokenByUsername(orderUser);
        }
        return CommonResult.success()
                .message("登录成功")
                .data("token", token)
                .data("merchantId", orderUser.getMerchantId())
                .data("nickName", orderUser.getNickName())
                .data("avatar", orderUser.getAvatar());
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public CommonResult getUserInfo(@RequestHeader("token") String token) throws JsonProcessingException {
        logger.info("{}-----请求获取用户信息接口", GenerateTimeUtil.generateNowTime());
        if (Objects.isNull(token)) {
            return CommonResult.failed()
                    .message("token参数为空");
        }
        String username = orderUserService.getUsernameFromToken(token);

        OrderUser orderUser = orderUserService.queryUserByUsername(username);
        return CommonResult.success()
                .data("merchantId", orderUser.getMerchantId())
                .data("nickName", orderUser.getNickName())
                .data("avatar", orderUser.getAvatar());
    }

    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success();
    }
}
