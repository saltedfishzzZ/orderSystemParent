package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.repository.OrderCategoryRepo;
import com.wu.ordersystem.service.OrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    OrderCategoryRepo orderCategoryRepo;
}
