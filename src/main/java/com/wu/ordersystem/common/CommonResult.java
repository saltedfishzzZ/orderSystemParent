package com.wu.ordersystem.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujianxin
 * @date 2021-09-26日
 * @description
 */

public class CommonResult {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 操作成功
     * @return result
     */
    public static CommonResult success() {
        return new CommonResult(ResultCode.SUCCESS);
    }

    /**
     * 操作失败
     * @return result
     */
    public static CommonResult failed() {
        return new CommonResult(ResultCode.FAILED);
    }

    /**
     * 错误请求
     * @return result
     */
    public static CommonResult badRequest() {
        return new CommonResult(ResultCode.BAD_REQUEST);
    }

    /**
     * 未登录或token过期
     * @return result
     */
    public static CommonResult unauth() {
        return new CommonResult(ResultCode.UNAUTHORIZED);
    }

    /**
     * 无权限操作
     * @return result
     */
    public static CommonResult forbidden() {
        return new CommonResult(ResultCode.FORBIDDEN);
    }

    public CommonResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public CommonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public CommonResult data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }

    private CommonResult() {
    }

    private CommonResult(ResultCode resultCode) {
        this.code = resultCode.getcode();
        this.message = resultCode.getMessage();
    }

    private CommonResult(Integer code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
