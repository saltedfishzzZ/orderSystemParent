package com.wu.ordersystem.repository.resource;

import com.wu.ordersystem.pojo.domain.resource.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wujianxin
 * @date 2021-09-23
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
