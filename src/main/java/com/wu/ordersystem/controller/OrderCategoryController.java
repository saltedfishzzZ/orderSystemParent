package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.dto.OrderCategoryDTO;
import com.wu.ordersystem.service.OrderCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */

@RestController
@RequestMapping(value = "/category")
public class OrderCategoryController {
    @Autowired
    private OrderCategoryService orderCategoryService;

    @GetMapping("/list/{id}")
    public CommonResult list(@PathVariable Long id, Integer pageNo, Integer pageSize) {
        if (Objects.isNull(pageNo) || pageNo < 1) {
            pageNo = 1;
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            pageSize = 10;
        }
        return CommonResult.success()
                .data("categoryList", orderCategoryService.listCategory(id, pageNo, pageSize));
    }

    @PostMapping("/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OrderCategoryDTO orderCategoryDTO) {
        boolean result = orderCategoryService.updateById(id, orderCategoryDTO);
        if (!result) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }
}
