package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.OrderCategory;
import com.wu.ordersystem.pojo.dto.OrderCategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */

public interface OrderCategoryService {
    Page<OrderCategory> listCategory(Long id, Integer pageNo, Integer pageSize);

    boolean updateById(Long id, OrderCategoryDTO orderCategoryDTO);

    void batchDeleteByIdList(List<Long> idList);
}
