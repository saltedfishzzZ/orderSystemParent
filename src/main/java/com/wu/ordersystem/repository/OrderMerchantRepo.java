package com.wu.ordersystem.repository;

import com.wu.ordersystem.pojo.domain.OrderMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wujianxin
 * @date 2021-09-27
 * @description
 */

@Repository
public interface OrderMerchantRepo extends JpaRepository<OrderMerchant, Long> {
}
