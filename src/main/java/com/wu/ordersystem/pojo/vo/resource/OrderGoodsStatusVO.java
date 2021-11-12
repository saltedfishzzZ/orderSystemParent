package com.wu.ordersystem.pojo.vo.resource;

/**
 * @author saltedfishzzZ
 * @date 2021-11-01
 * @description
 */

public class OrderGoodsStatusVO {
    private Integer code;

    private String status;

    public OrderGoodsStatusVO() {
    }

    public OrderGoodsStatusVO(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
