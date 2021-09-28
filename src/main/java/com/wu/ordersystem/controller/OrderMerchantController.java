package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.domain.OrderMerchant;
import com.wu.ordersystem.service.OrderMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

@RestController
@RequestMapping("/merchant")
public class OrderMerchantController {

    @Autowired
    private OrderMerchantService orderMerchantService;

    @GetMapping("/{id}")
    public CommonResult getMerchantInfo(@PathVariable Long id) {
        OrderMerchant merchantInfo = orderMerchantService.getMerchantById(id);
        return CommonResult.success()
                .data("merchantInfo", merchantInfo);
    }
}
