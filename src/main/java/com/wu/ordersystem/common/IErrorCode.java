package com.wu.ordersystem.common;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

public interface IErrorCode {
    /**
     * 返回状态码
     * @return code
     */
    Integer getcode();

    /**
     * 返回信息
     * @return message
     */
    String getMessage();
}
