package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.common.Constants;
import com.wu.ordersystem.pojo.domain.OrderMerchant;
import com.wu.ordersystem.pojo.dto.OrderMerchantDTO;
import com.wu.ordersystem.service.OrderMerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
        logger.info("{}----请求获取商户信息接口", LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
        OrderMerchant merchantInfo = orderMerchantService.getMerchantById(id);
        return CommonResult.success()
                .data("merchantInfo", merchantInfo);
    }

    @PostMapping("/{id}")
    public CommonResult updateMerchantInfo(@PathVariable Long id,
                                           @Valid  @RequestBody OrderMerchantDTO orderMerchantDTO) {
        logger.info("{}----请求编辑商户信息接口", LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
        OrderMerchant merchant = orderMerchantService.getMerchantById(id);

        merchant.setName(orderMerchantDTO.getName());
        merchant.setAddress(orderMerchantDTO.getAddress());
        merchant.setTelephone(orderMerchantDTO.getTelephone());
        if (Objects.nonNull(orderMerchantDTO.getAnnouncement())) {
            merchant.setAnnouncement(orderMerchantDTO.getAnnouncement());
        }
        merchant.setBusinessStartTime(orderMerchantDTO.getBusinessStartTime());
        merchant.setBusinessEndTime(orderMerchantDTO.getBusinessEndTime());

        orderMerchantService.updateMerchantById(merchant);

        return CommonResult.success();
    }
}
