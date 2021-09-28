package com.wu.ordersystem.service;

import com.wu.ordersystem.pojo.domain.OrderUser;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月23日
 * @description
 */

public interface OrderUserService {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return user
     */
    OrderUser queryUserByUsername(String username);

    String generateTokenByUsername(OrderUser user);

    String getUsernameFromToken(String token);
}
