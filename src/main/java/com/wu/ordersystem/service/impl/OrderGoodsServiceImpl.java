package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.pojo.domain.OrderGoods;
import com.wu.ordersystem.pojo.domain.OrderGoodsDetail;
import com.wu.ordersystem.pojo.dto.OrderGoodPageDTO;
import com.wu.ordersystem.repository.OrderGoodsDetailRepo;
import com.wu.ordersystem.repository.OrderGoodsRepo;
import com.wu.ordersystem.service.OrderGoodsService;
import com.wu.ordersystem.utils.SpecificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Autowired
    OrderGoodsRepo orderGoodsRepo;
    @Autowired
    OrderGoodsDetailRepo orderGoodsDetailRepo;

    @Override
    public Page<OrderGoods> listGoods(OrderGoodPageDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo() - 1, dto.getPageSize());
        SpecificationFactory<OrderGoods> factory = new SpecificationFactory<>();
        Specification<OrderGoods> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("merchantId").as(Long.class), dto.getMerchantId()));
            if (Objects.nonNull(dto.getName())) {
                predicates.add(cb.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
            }
            if (Objects.nonNull(dto.getCategoryId())) {
                predicates.add(cb.equal(root.get("categoryId").as(Long.class), dto.getCategoryId()));
            }
            if (Objects.nonNull(dto.getStatus())) {
                predicates.add(cb.equal(root.get("status").as(Integer.class), dto.getStatus()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        return orderGoodsRepo.findAll(Specification.where(specification), pageable);
    }

    @Override
    public void editStatus(Long id, Integer status) {
        OrderGoods good = orderGoodsRepo.getById(id);
        good.setStatus(status);
        orderGoodsRepo.save(good);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        // 删除商品表
        orderGoodsRepo.deleteById(id);

        // 删除商品详情表
        Long goodDetailId = orderGoodsRepo.findById(id).get().getGoodDetailId();
        orderGoodsDetailRepo.deleteById(goodDetailId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> idList) {
        // 删除商品表
        orderGoodsRepo.deleteAllById(idList);

        // 删除商品详情表
        orderGoodsDetailRepo.deleteAllById(orderGoodsRepo.findAllById(idList).stream()
                .map(OrderGoods::getGoodDetailId)
                .collect(Collectors.toList()));
    }
}
