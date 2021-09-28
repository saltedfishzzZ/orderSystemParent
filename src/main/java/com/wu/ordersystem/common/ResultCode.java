package com.wu.ordersystem.common;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description 枚举一些常用的api操作码
 */

public enum ResultCode implements IErrorCode {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAILED(500, "操作失败"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "请求错误"),
    /**
     * 未登录或token过期
     */
    UNAUTHORIZED(401, "未登录或token过期"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getcode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
