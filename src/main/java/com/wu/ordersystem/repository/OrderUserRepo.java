package com.wu.ordersystem.repository;

import com.wu.ordersystem.pojo.domain.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月23日
 * @description
 */

@Repository
public interface OrderUserRepo extends JpaRepository<OrderUser, Long> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return user
     */
    List<OrderUser> findByUsername(String username);
}
