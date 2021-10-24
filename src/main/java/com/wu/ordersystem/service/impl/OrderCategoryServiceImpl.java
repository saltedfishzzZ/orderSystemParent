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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author saltedfishzzZ
 * @date 2021-10-10
 * @description
 */
@Service("orderCategoryServiceImpl")
public class OrderCategoryServiceImpl implements OrderCategoryService {

    @Autowired
    private OrderCategoryRepo orderCategoryRepo;

    @Override
    public Page<OrderCategory> listCategory(Long id, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        SpecificationFactory<OrderCategory> specificationFactory = new SpecificationFactory<>();
        Specification<OrderCategory> specification = specificationFactory.equal("merchantId", id);
        return orderCategoryRepo.findAll(specification, pageable);
    }

    @Override
    public boolean addCategory(OrderCategoryDTO orderCategoryDTO) {
        OrderCategory entity = new OrderCategory();
        entity.setMerchantId(orderCategoryDTO.getMerchantId());
        entity.setCategoryName(orderCategoryDTO.getName());
        OrderCategory orderCategory = orderCategoryRepo.saveAndFlush(entity);
        if (Objects.isNull(orderCategory.getId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public boolean updateById(Long id, OrderCategoryDTO orderCategoryDTO) {
        OrderCategory entity = orderCategoryRepo.findById(id).get();
        BeanUtils.copyProperties(entity, orderCategoryDTO);
        OrderCategory orderCategory = orderCategoryRepo.save(entity);
        return Boolean.TRUE;
    }

    @Override
    public void deleteById(Long id) {
        if (orderCategoryRepo.existsById(id)) {
            orderCategoryRepo.deleteById(id);
        }
    }

    @Override
    public void batchDeleteByIdList(List<Long> idList) {
        orderCategoryRepo.deleteAllByIdInBatch(idList);
    }
}
