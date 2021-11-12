package com.wu.ordersystem.service.impl;

import com.wu.ordersystem.enums.GoodStatusEnum;
import com.wu.ordersystem.pojo.domain.resource.OrderGoods;
import com.wu.ordersystem.pojo.domain.resource.OrderGoodsDetail;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodAddDTO;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodEditDTO;
import com.wu.ordersystem.pojo.dto.resource.OrderGoodPageDTO;
import com.wu.ordersystem.repository.resource.OrderGoodsDetailRepo;
import com.wu.ordersystem.repository.resource.OrderGoodsRepo;
import com.wu.ordersystem.service.OrderGoodsService;
import com.wu.ordersystem.utils.SpecificationFactory;
import org.springframework.beans.BeanUtils;
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
    public OrderGoods getGood(Long id) {
        return orderGoodsRepo.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addGood(OrderGoodAddDTO addDTO) {
        // 添加商品详情表
        OrderGoodsDetail detail = new OrderGoodsDetail();
        BeanUtils.copyProperties(addDTO.getGoodsDetail(), detail);
        OrderGoodsDetail orderGoodDetail = orderGoodsDetailRepo.saveAndFlush(detail);

        if (Objects.isNull(orderGoodDetail.getId())) {
            return Boolean.FALSE;
        }

        OrderGoods good = new OrderGoods();
        BeanUtils.copyProperties(addDTO, good);
        // 新添加商品状态都为未上架
        good.setStatus(GoodStatusEnum.TO_BE_SELL.getCode());
        good.setGoodDetailId(detail.getId());
        good.setGoodsDetail(null);
        OrderGoods orderGoods = orderGoodsRepo.save(good);
        if (Objects.isNull(orderGoods.getId())) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editGood(OrderGoodEditDTO editDTO) {
        OrderGoods good = orderGoodsRepo.getById(editDTO.getId());

        // 修改商品详情表
        Long goodDetailId = good.getGoodDetailId();
        OrderGoodsDetail goodsDetail = orderGoodsDetailRepo.getById(goodDetailId);
        goodsDetail.setId(goodDetailId);
        BeanUtils.copyProperties(editDTO.getGoodsDetail(), goodsDetail);
        orderGoodsDetailRepo.saveAndFlush(goodsDetail);

        // 修改商品表
        BeanUtils.copyProperties(editDTO, good);
        orderGoodsRepo.saveAndFlush(good);
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
        Long goodDetailId = orderGoodsRepo.getById(id).getGoodDetailId();

        // 删除商品表
        orderGoodsRepo.deleteById(id);
        // 删除商品详情表
        orderGoodsDetailRepo.deleteById(goodDetailId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> idList) {
        List<Long> goodDetailIds = orderGoodsRepo.findAllById(idList).stream()
                .map(OrderGoods::getGoodDetailId)
                .collect(Collectors.toList());
        // 删除商品表
        orderGoodsRepo.deleteAllById(idList);
        // 删除商品详情表
        orderGoodsDetailRepo.deleteAllById(goodDetailIds);
    }
}
