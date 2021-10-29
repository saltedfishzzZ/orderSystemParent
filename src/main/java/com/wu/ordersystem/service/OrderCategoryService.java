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

    boolean addCategory(OrderCategoryDTO orderCategoryDTO);

    boolean updateById(Long id, OrderCategoryDTO orderCategoryDTO);

    void deleteById(Long id);

    void batchDeleteByIdList(List<Long> idList);

    Integer getMaxShowOrder(Long merchantId);

    Integer getMinShowOrder(Long merchantId);

    void upOrder(Long id);

    void downOrder(Long id);
}
