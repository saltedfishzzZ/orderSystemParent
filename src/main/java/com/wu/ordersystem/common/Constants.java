package com.wu.ordersystem.common;

import java.time.format.DateTimeFormatter;

/**
 * @author wujianxin
 * @date 2021-09-26日
 * @description
 */

public class Constants {
    // 时间序列化格式
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // redis缓存key
    public static final String ORDER_USER_KEY = "order:user";
    public static final Integer ORDER_USER_TIME = 30;

    // order:user:token - username
    public static final String ORDER_USER_TOKEN_KEY = "order:userToken";
    public static final Integer ORDER_USER_TOKEN_TIME = 7;

    // order:merchant:info - id
    public static final String ORDER_MERCHANT_INFO_KEY = "order:merchant:info";
    public static final Integer ORDER_MERCHANT_INFO_TIME = 30;
}
