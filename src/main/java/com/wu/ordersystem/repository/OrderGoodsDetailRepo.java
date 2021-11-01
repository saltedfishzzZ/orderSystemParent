package com.wu.ordersystem.repository;

import com.wu.ordersystem.pojo.domain.OrderGoodsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saltedfishzzZ
 * @date 2021-10-29
 * @description
 */

@Repository
public interface OrderGoodsDetailRepo extends JpaRepository<OrderGoodsDetail, Long>, JpaSpecificationExecutor<OrderGoodsDetail> {
    void deleteAllByGoodId(Long goodId);

    void deleteAllByGoodIdIn(List<Long> idList);
}
