package com.wu.ordersystem.repository;

import com.wu.ordersystem.pojo.domain.OrderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author saltedfishzzZ
 * @date 2021-10-02
 * @description
 */

@Repository
public interface OrderCategoryRepo extends JpaRepository<OrderCategory, Long>, JpaSpecificationExecutor<OrderCategory> {
}
