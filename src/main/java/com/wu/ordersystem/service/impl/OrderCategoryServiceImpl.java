package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderCategory;
import com.wu.ordersystem.pojo.dto.OrderCategoryDTO;
import com.wu.ordersystem.repository.OrderCategoryRepo;
import com.wu.ordersystem.service.OrderCategoryService;
import com.wu.ordersystem.utils.SpecificationFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */
@Service("orderCategoryServiceImpl")
public class OrderCategoryServiceImpl implements OrderCategoryService {

    @Autowired
    private OrderCategoryRepo orderCategoryRepo;

    public Page<OrderCategory> listCategory(Long id, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderCategoryRepo.findAll(SpecificationFactory.equal("merchantId", id), pageable);
    }

    public boolean updateById(Long id, OrderCategoryDTO orderCategoryDTO) {
        OrderCategory entity = orderCategoryRepo.findById(id).get();
        BeanUtils.copyProperties(entity, orderCategoryDTO);
        OrderCategory orderCategory = orderCategoryRepo.save(entity);
        if (Objects.isNull(orderCategory)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
