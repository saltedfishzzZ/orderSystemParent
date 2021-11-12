package com.wu.ordersystem.pojo.dto.resource;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author saltedfishzzZ
 * @date 2021-11-05
 * @description
 */

@Validated
public class OrderGoodEditDTO {
    @NotNull(message = "商品id不能为空")
    private Long id;

    @NotNull(message = "类别id不能为空")
    private Long categoryId;

    @NotEmpty(message = "商品名不能为空或空格")
    private String name;

    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @NotNull(message = "打包费不能为空")
    private BigDecimal packingFee;

    @NotNull(message = "库存不能为空")
    private Integer stock;

    private GoodDetail goodsDetail;

    public static class GoodDetail {
        @NotEmpty(message = "主料不能为空")
        private String mainElements;

        private String extraElements;

        private String description;

        private String weight;

        private String taste;

        public String getMainElements() {
            return mainElements;
        }

        public void setMainElements(String mainElements) {
            this.mainElements = mainElements;
        }

        public String getExtraElements() {
            return extraElements;
        }

        public void setExtraElements(String extraElements) {
            this.extraElements = extraElements;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getTaste() {
            return taste;
        }

        public void setTaste(String taste) {
            this.taste = taste;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public GoodDetail getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(GoodDetail goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
