package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.resource.OrderGoods;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodAddDTO;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodEditDTO;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodPageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

public interface OrderGoodsService {

    Page<OrderGoods> listGoods(OrderGoodPageDTO dto);

    boolean addGood(OrderGoodAddDTO addDTO);

    void deleteById(Long id);

    void editStatus(Long id, Integer status);

    void batchDelete(List<Long> idList);

    OrderGoods getGood(Long id);

    void editGood(OrderGoodEditDTO editDTO);
}
