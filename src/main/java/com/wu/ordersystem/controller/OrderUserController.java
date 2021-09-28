package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.common.Constants;
import com.wu.ordersystem.pojo.domain.OrderUser;
import com.wu.ordersystem.pojo.dto.OrderUserDTO;
import com.wu.ordersystem.service.OrderUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月23日
 * @description
 */

@RestController
@RequestMapping("/")
public class OrderUserController {

    private static final Logger logger = LoggerFactory.getLogger(OrderUserController.class);

    @Autowired
    private OrderUserService orderUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Valid @RequestBody OrderUserDTO orderUserDTO) {
        logger.info("{}-----请求登录接口", LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
        OrderUser orderUser = orderUserService.queryUserByUsername(orderUserDTO.getUsername());

        if (Objects.isNull(orderUser)) {
            return CommonResult.failed().message("用户不存在");
        }
        if (!orderUserDTO.getPassword().equals(orderUser.getPassword())) {
            return CommonResult.failed().message("密码错误");
        }
        String token = orderUserService.generateTokenByUsername(orderUser);
        return CommonResult.success()
                .message("登录成功")
                .data("token", token)
                .data("merchantId", orderUser.getMerchantId())
                .data("nickName", orderUser.getNickName())
                .data("avatar", orderUser.getAvatar());
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public CommonResult getUserInfo(@RequestHeader("token") String token) {
        logger.info("{}-----请求获取用户信息接口", LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
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
}
