package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.domain.OrderGoods;
import com.wu.ordersystem.pojo.dto.OrderGoodPageDTO;
import com.wu.ordersystem.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author saltedfishzzZ
 * @date 2021-10-29
 * @description
 */

@RestController
@RequestMapping(value = "/good", method = RequestMethod.POST)
public class OrderGoodsController {
    @Autowired
    OrderGoodsService orderGoodsService;

    @RequestMapping(value = "/list")
    public CommonResult listGoods(@Validated @RequestBody OrderGoodPageDTO dto) {
        if (Objects.isNull(dto.getPageNo()) || dto.getPageNo() < 1) {
            dto.setPageNo(1);
        }
        if (Objects.isNull(dto.getPageSize()) || dto.getPageSize() < 1) {
            dto.setPageSize(10);
        }
        Page<OrderGoods> goods = orderGoodsService.listGoods(dto.getMerchantId(), dto.getPageNo(), dto.getPageSize());
        return CommonResult.success().data("goods", goods);
    }

    @RequestMapping(value = "/deleteById")
    public CommonResult deleteById(Long id) {
        orderGoodsService.deleteById(id);
        return CommonResult.success();
    }
}
