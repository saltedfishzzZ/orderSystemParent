package com.wu.ordersystem.repository.resource;

import com.wu.ordersystem.pojo.domain.resource.OrderGoodsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author saltedfishzzZ
 * @date 2021-10-29
 * @description
 */

@Repository
public interface OrderGoodsDetailRepo extends JpaRepository<OrderGoodsDetail, Long>, JpaSpecificationExecutor<OrderGoodsDetail> {
}
