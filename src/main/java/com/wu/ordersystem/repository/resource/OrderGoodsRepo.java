package com.wu.ordersystem.repository.resource;

import com.wu.ordersystem.pojo.domain.resource.OrderGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

@Repository
public interface OrderGoodsRepo extends JpaRepository<OrderGoods, Long>, JpaSpecificationExecutor<OrderGoods> {
}
