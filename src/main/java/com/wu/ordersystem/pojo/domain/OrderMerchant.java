package com.wu.ordersystem.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wujianxin
 * @date 2021-09-26日
 * @description
 */

@Entity
@Table(name = "order_merchant")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class OrderMerchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String telephone;

    /**
     * 商户状态, 0-失效, 1-有效
     */
    private Short status;

    /**
     * 公告
     */
    private String announcement;

    /**
     * 营业开始时间
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime businessStartTime;

    /**
     * 营业结束时间
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime businessEndTime;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
