package com.wu.ordersystem.repository;

import com.wu.ordersystem.pojo.domain.OrderMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月27日
 * @description
 */

@Repository
public interface OrderMerchantRepo extends JpaRepository<OrderMerchant, Long> {
}
