package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.OrderMerchant;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

public interface OrderMerchantService {

    OrderMerchant getMerchantById(Long id);

    OrderMerchant updateMerchantById(OrderMerchant orderMerchant);
}
