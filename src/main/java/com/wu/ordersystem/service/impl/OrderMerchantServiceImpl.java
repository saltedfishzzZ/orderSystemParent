package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderMerchant;
import com.wu.ordersystem.repository.OrderMerchantRepo;
import com.wu.ordersystem.service.OrderMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月27日
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
}
