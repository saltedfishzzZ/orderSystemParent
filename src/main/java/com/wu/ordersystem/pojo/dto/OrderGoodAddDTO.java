package com.wu.ordersystem.pojo.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author saltedfishzzZ
 * @date 2021-10-31
 * @description
 */

@Validated
public class OrderGoodAddDTO {

    @NotNull(message = "商户id不能为空")
    private Long merchantId;

    @NotNull(message = "类别id不能为空")
    private Long categoryId;

    @NotEmpty(message = "商品名不能为空或空格")
    private String name;

    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @NotNull(message = "打包费不能为空")
    private BigDecimal packingFee;

    private GoodDetail goodDetail;

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

    public GoodDetail getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(GoodDetail goodDetail) {
        this.goodDetail = goodDetail;
    }
}
