package com.wu.ordersystem.pojo.vo;

import com.wu.ordersystem.enums.GoodStatusEnum;
import com.wu.ordersystem.pojo.domain.OrderGoods;
import com.wu.ordersystem.pojo.domain.OrderGoodsDetail;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author saltedfishzzZ
 * @date 2021-10-31
 * @description
 */

public class OrderGoodsVO {
    private Long id;

    private Long merchantId;

    private Long categoryId;

    private String categoryName;

    private String name;

    private String img;

    private Integer status;

    private String goodStatus;

    private Integer stock;

    // 价格
    private BigDecimal price;
    // 餐盒费
    private BigDecimal packingFee;

    private LocalDateTime createTime;

    private OrderGoodsDetail goodsDetail;

    public OrderGoodsVO() {}

    public OrderGoodsVO(OrderGoods good) {
        BeanUtils.copyProperties(good, this);
        this.goodStatus = GoodStatusEnum.getByCode(this.status).getStatus();
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public OrderGoodsDetail getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(OrderGoodsDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
