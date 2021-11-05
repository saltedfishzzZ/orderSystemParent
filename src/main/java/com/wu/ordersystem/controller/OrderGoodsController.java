package com.wu.ordersystem.controller;

import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.enums.GoodStatusEnum;
import com.wu.ordersystem.pojo.domain.OrderCategory;
import com.wu.ordersystem.pojo.domain.OrderGoods;
import com.wu.ordersystem.pojo.dto.OrderGoodAddDTO;
import com.wu.ordersystem.pojo.dto.OrderGoodPageDTO;
import com.wu.ordersystem.pojo.vo.OrderGoodsStatusVO;
import com.wu.ordersystem.pojo.vo.OrderGoodsVO;
import com.wu.ordersystem.service.OrderCategoryService;
import com.wu.ordersystem.service.OrderGoodsService;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author saltedfishzzZ
 * @date 2021-10-29
 * @description
 */

@RestController
@RequestMapping(value = "/good", method = RequestMethod.POST)
public class OrderGoodsController {
    private final Logger logger = LoggerFactory.getLogger(OrderGoodsController.class);
    @Autowired
    OrderGoodsService orderGoodsService;
    @Autowired
    OrderCategoryService orderCategoryService;

    @RequestMapping(value = "/list")
    public CommonResult listGoods(@Validated @RequestBody OrderGoodPageDTO dto) {
        logger.info("{}-----请求商品列表接口", GenerateTimeUtil.generateNowTime());
        if (Objects.isNull(dto.getPageNo()) || dto.getPageNo() < 1) {
            dto.setPageNo(1);
        }
        if (Objects.isNull(dto.getPageSize()) || dto.getPageSize() < 1) {
            dto.setPageSize(10);
        }
        Page<OrderGoods> goods = orderGoodsService.listGoods(dto);
        // 所有类别id集合
        List<Long> categoryIdList = goods.getContent().stream().map(OrderGoods::getCategoryId).collect(Collectors.toList());
        // 查询所有的类别
        List<OrderCategory> categories = orderCategoryService.listCategoryByIds(categoryIdList);

        Map<Long, String> categoryMap = new HashMap<>();
        orderCategoryService.listCategoryByIds(categoryIdList)
                .forEach(category -> {
                    categoryMap.putIfAbsent(category.getId(), category.getCategoryName());
                });

        Page<OrderGoodsVO> goodVOs = goods.map(good -> {
            OrderGoodsVO goodsVO = new OrderGoodsVO(good);

            goodsVO.setCategoryName(categoryMap.get(good.getCategoryId()));

            return goodsVO;
        });
        return CommonResult.success().data("goods", goodVOs);
    }

    @RequestMapping(value = "/getAllStatus")
    public CommonResult getAllStatus() {
        logger.info("{}-----请求获取所有商品状态接口", GenerateTimeUtil.generateNowTime());

        List<OrderGoodsStatusVO> list = new ArrayList<>();

        for (GoodStatusEnum value : GoodStatusEnum.values()) {
            list.add(new OrderGoodsStatusVO(value.getCode(), value.getStatus()));
        }

        return CommonResult.success()
                .data("allStatus", list);
    }

    @RequestMapping(value = "/addGood")
    public CommonResult addGood(@RequestBody OrderGoodAddDTO addDTO) {
        boolean result = orderGoodsService.addGood(addDTO);
        if (!result) {
            return CommonResult.failed();
        }
        return CommonResult.success();
    }

    @RequestMapping(value = "/editStatus")
    public CommonResult editStatus(Long id,Integer status) {
        logger.info("{}-----请求修改商品状态接口", GenerateTimeUtil.generateNowTime());

        if (Objects.isNull(id) || Objects.isNull(status)) {
            return CommonResult.failed();
        }

        orderGoodsService.editStatus(id, status);
        return CommonResult.success();
    }

    @RequestMapping(value = "/deleteById")
    public CommonResult deleteById(Long id) {
        logger.info("{}-----请求删除单个商品接口", GenerateTimeUtil.generateNowTime());
        orderGoodsService.deleteById(id);
        return CommonResult.success();
    }


    @RequestMapping(value = "/batchDelete")
    public CommonResult batchDelete(@RequestBody List<Long> idList) {
        logger.info("{}-----请求批量删除商品接口", GenerateTimeUtil.generateNowTime());
        orderGoodsService.batchDelete(idList);
        return CommonResult.success();
    }
}
