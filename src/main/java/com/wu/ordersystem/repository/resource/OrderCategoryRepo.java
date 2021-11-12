package com.wu.ordersystem.repository.resource;

import com.wu.ordersystem.pojo.domain.resource.OrderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author saltedfishzzZ
 * @date 2021-10-02
 * @description
 */

@Repository
public interface OrderCategoryRepo extends JpaRepository<OrderCategory, Long>, JpaSpecificationExecutor<OrderCategory> {
    @Query(value = "select max(show_order) from order_category where merchant_id = ?1", nativeQuery = true)
    Integer findMaxShowOrderByMerchantId(Long merchantId);

    @Query(value = "select min(show_order) from order_category where merchant_id = ?1", nativeQuery = true)
    Integer findMinShowOrderByMerchantId(Long merchantId);
}
