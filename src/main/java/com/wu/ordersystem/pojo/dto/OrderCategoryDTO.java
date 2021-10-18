package com.wu.ordersystem.pojo.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author saltedfishzzZ
 * @date 2021-10-18
 * @description
 */

@Validated
public class OrderCategoryDTO {
    @NotBlank(message = "类别名不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
