package com.wu.ordersystem.pojo.vo;

/**
 * @author saltedfishzzZ
 * @date 2021-11-01
 * @description
 */

public class OrderCategoryNameVO {
    private Long id;

    private String categoryName;

    public OrderCategoryNameVO() {
    }

    public OrderCategoryNameVO(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
