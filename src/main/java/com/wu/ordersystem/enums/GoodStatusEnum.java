package com.wu.ordersystem.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saltedfishzzZ
 * @date 2021-10-31
 * @description 商品状态enum
 */

public enum GoodStatusEnum {
    TO_BE_SELL(0, "待上架"), ON_SELLING(2, "已上架"), NOT_ON_SELLING(3, "已下架");

    GoodStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    private Integer code;

    private String status;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final Map<Integer, GoodStatusEnum> _allMap = new HashMap<>();

    static {
        for (GoodStatusEnum goodStatus : GoodStatusEnum.values()) {
            _allMap.putIfAbsent(goodStatus.getCode(), goodStatus);
        }
    }

    public static GoodStatusEnum getByCode(Integer code) {
        return _allMap.get(code);
    }
}
