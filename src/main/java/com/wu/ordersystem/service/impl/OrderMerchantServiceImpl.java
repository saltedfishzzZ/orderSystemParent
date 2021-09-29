package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderMerchant;
import com.wu.ordersystem.repository.OrderMerchantRepo;
import com.wu.ordersystem.service.OrderMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

@Service
public class OrderMerchantServiceImpl implements OrderMerchantService {
    @Autowired
    private OrderMerchantRepo orderMerchantRepo;

    @Override
    public OrderMerchant getMerchantById(Long id) {
        return orderMerchantRepo.getById(id);
    }

    @Override
    public OrderMerchant updateMerchantById(OrderMerchant orderMerchant) {
        return orderMerchantRepo.save(orderMerchant);
    }
}
