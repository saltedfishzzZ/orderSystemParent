package com.wu.ordersystem.pojo.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description
 */

@Validated
public class OrderUserDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public OrderUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OrderUserDTO() {
    }
}
