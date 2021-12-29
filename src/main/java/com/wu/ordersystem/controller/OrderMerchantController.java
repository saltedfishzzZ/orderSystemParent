package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.domain.resource.OrderMerchant;
import com.wu.ordersystem.pojo.dto.resource.OrderMerchantDTO;
import com.wu.ordersystem.service.OrderMerchantService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

@RestController
@RequestMapping("/merchant")
public class OrderMerchantController {

    private static final Logger logger = LoggerFactory.getLogger(OrderMerchantController.class);

    @Autowired
    private OrderMerchantService orderMerchantService;

    @GetMapping("/{id}")
    public CommonResult getMerchantInfo(@PathVariable Long id) {
        logger.info("{}----请求获取商户信息接口", GenerateTimeUtil.generateNowTime());
        OrderMerchant merchantInfo = orderMerchantService.getMerchantById(id);
        return CommonResult.success()
                .data("merchantInfo", merchantInfo);
    }

    @PostMapping("/{id}")
    public CommonResult updateMerchantInfo(@PathVariable Long id,
                                           @Valid  @RequestBody OrderMerchantDTO orderMerchantDTO) {
        logger.info("{}----请求编辑商户信息接口", GenerateTimeUtil.generateNowTime());
        OrderMerchant merchant = orderMerchantService.getMerchantById(id);

        merchant.setName(orderMerchantDTO.getName());
        merchant.setAddress(orderMerchantDTO.getAddress());
        merchant.setTelephone(orderMerchantDTO.getTelephone());
        if (Objects.nonNull(orderMerchantDTO.getAnnouncement())) {
            merchant.setAnnouncement(orderMerchantDTO.getAnnouncement());
        }
        merchant.setBusinessStartTime(orderMerchantDTO.getBusinessStartTime());
        merchant.setBusinessEndTime(orderMerchantDTO.getBusinessEndTime());
        merchant.setPicture(orderMerchantDTO.getPicture());

        orderMerchantService.updateMerchantById(merchant);

        return CommonResult.success();
    }
}
