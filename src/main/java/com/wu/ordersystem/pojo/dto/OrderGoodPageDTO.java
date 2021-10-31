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

    private Integer pageNo;

    private Integer pageSize;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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
