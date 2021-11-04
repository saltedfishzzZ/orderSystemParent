package com.wu.ordersystem.pojo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author saltedfishzzZ
 * @date 2021-10-29
 * @description
 */

@Entity
@Table(name = "order_goods_detail")
@DynamicUpdate
@DynamicInsert
public class OrderGoodsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    // 主料
    private String mainElements;

    // 辅料
    private String extraElements;

    // 描述
    private String desc;

    // 份量
    private String weight;

    // 口味
    private String taste;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createTime;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
