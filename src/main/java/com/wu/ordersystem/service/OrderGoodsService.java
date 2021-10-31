package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.OrderGoods;
import org.springframework.data.domain.Page;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

public interface OrderGoodsService {

    Page<OrderGoods> listGoods(Long merchantId, Integer pageNo, Integer pageSize);

    void deleteById(Long id);

    void editStatus(Long id, Integer status);
}
