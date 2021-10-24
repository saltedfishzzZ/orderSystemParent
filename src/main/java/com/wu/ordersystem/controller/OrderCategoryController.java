package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.pojo.dto.OrderCategoryDTO;
import com.wu.ordersystem.service.OrderCategoryService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */

@RestController
@RequestMapping(value = "/category")
public class OrderCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(OrderCategoryController.class);
    @Autowired
    private OrderCategoryService orderCategoryService;

    @GetMapping("/list/{id}")
    public CommonResult list(@PathVariable Long id, Integer pageNo, Integer pageSize) {
        logger.info("{}-----请求查询类别列表接口", GenerateTimeUtil.generateNowTime());
        if (Objects.isNull(pageNo) || pageNo < 1) {
            pageNo = 1;
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            pageSize = 10;
        }
        return CommonResult.success()
                .data("categoryList", orderCategoryService.listCategory(id, pageNo, pageSize));
    }

    @PostMapping
    public CommonResult add(@RequestBody OrderCategoryDTO orderCategoryDTO) {
        logger.info("{}-----请求添加类别接口", GenerateTimeUtil.generateNowTime());
        boolean result = orderCategoryService.addCategory(orderCategoryDTO);
        if (result) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @PutMapping("/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody OrderCategoryDTO orderCategoryDTO) {
        logger.info("{}-----请求更新类别接口", GenerateTimeUtil.generateNowTime());
        boolean result = orderCategoryService.updateById(id, orderCategoryDTO);
        if (!result) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        logger.info("{}-----请求删除单个类别接口", GenerateTimeUtil.generateNowTime());
        orderCategoryService.deleteById(id);
        return CommonResult.success();
    }

    @DeleteMapping("/batch/delete")
    public CommonResult batchDelete(@RequestBody List<Long> idList) {
        logger.info("{}-----请求批量删除接口", GenerateTimeUtil.generateNowTime());
        orderCategoryService.batchDeleteByIdList(idList);
        return CommonResult.success();
    }
}
