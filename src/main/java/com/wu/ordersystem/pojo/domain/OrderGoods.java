package com.wu.ordersystem.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author saltedfishzzZ
 * @date 2021-10-27
 * @description
 */

@Entity
@Table(name = "order_goods")
@DynamicUpdate
@DynamicInsert
public class OrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long merchantId;

    private Long categoryId;

    private String name;

    private String img;

    // 价格
    private BigDecimal price;
    // 餐盒费
    private BigDecimal packingFee;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateTime;

    @OneToOne(targetEntity = OrderGoodsDetail.class)
    @JoinColumn(name = "id", referencedColumnName = "good_id")
    private OrderGoodsDetail goodsDetail = new OrderGoodsDetail();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(BigDecimal packingFee) {
        this.packingFee = packingFee;
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

    public OrderGoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(OrderGoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
