package com.wu.ordersystem.pojo.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author saltedfishzzZ
 * @date 2021-10-30
 * @description
 */

@Validated
public class OrderGoodPageDTO {

    @NotNull(message = "商户id不能为空")
    private Long merchantId;

    private String name;

    private Long categoryId;

    private Integer status;

    private Integer pageNo;

    private Integer pageSize;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
