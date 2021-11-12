package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.resource.OrderCategory;
import com.wu.ordersystem.pojo.dto.resource.OrderCategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */

public interface OrderCategoryService {
    List<OrderCategory> listCategoryByIds(List<Long> idList);

    Page<OrderCategory> listCategory(Long id, Integer pageNo, Integer pageSize);

    boolean addCategory(OrderCategoryDTO orderCategoryDTO);

    boolean updateById(Long id, OrderCategoryDTO orderCategoryDTO);

    void deleteById(Long id);

    void batchDeleteByIdList(List<Long> idList);

    Integer getMaxShowOrder(Long merchantId);

    Integer getMinShowOrder(Long merchantId);

    void upOrder(Long id);

    void downOrder(Long id);

    List<OrderCategory> findAllCategory(Long merchantId);
}
