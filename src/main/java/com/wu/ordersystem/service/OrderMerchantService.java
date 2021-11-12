package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.resource.OrderMerchant;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

public interface OrderMerchantService {

    OrderMerchant getMerchantById(Long id);

    void updateMerchantById(OrderMerchant orderMerchant);
}
