package com.wu.ordersystem.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * @author saltedfishzzZ
 * @date 2021-09-29
 * @description
 */

@Validated
public class OrderMerchantDTO {
    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "联系方式不能为空")
    private String telephone;

    private String announcement;

    @NotNull(message = "营业开始时间不能为空")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private LocalTime businessStartTime;

    @NotNull(message = "营业结束时间不能为空")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private LocalTime businessEndTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public LocalTime getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(LocalTime businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public LocalTime getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(LocalTime businessEndTime) {
        this.businessEndTime = businessEndTime;
    }
}
