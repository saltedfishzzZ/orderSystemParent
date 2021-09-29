package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.domain.OrderMerchant;
import com.wu.ordersystem.pojo.dto.OrderMerchantDTO;
import com.wu.ordersystem.service.OrderMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @PostMapping("/{id}")
    public CommonResult updateMerchantInfo(@PathVariable Long id,
                                           @Valid  @RequestBody OrderMerchantDTO orderMerchantDTO) {
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
