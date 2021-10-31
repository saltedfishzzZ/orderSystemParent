package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderGoods;
import com.wu.ordersystem.repository.OrderCategoryRepo;
import com.wu.ordersystem.repository.OrderGoodsRepo;
import com.wu.ordersystem.service.OrderGoodsService;
import com.wu.ordersystem.utils.SpecificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    OrderGoodsRepo orderGoodsRepo;

    @Override
    public Page<OrderGoods> listGoods(Long merchantId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        SpecificationFactory<OrderGoods> factory = new SpecificationFactory<>();
        Specification<OrderGoods> specification = factory.equal("merchantId", merchantId);
        return orderGoodsRepo.findAll(specification, pageable);
    }
}
