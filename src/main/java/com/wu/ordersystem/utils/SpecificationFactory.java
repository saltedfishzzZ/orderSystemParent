package com.wu.ordersystem.utils;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author saltedfishzzZ
 * @date 2021-10-11
 * @description JPA查询条件 的构造工厂类
 */

public final class SpecificationFactory {
    /**
     * 模糊查询, 匹配对应字段
     */
    public static Specification containsLike(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    /**
     * 等于查询
     * @param attribute
     * @param value
     * @return
     */
    public static Specification equal(String attribute, Object value) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    /**
     * 范围查询
     * @param attribute
     * @param min
     * @param max
     * @return
     */
    public static Specification isBetween(String attribute, int min, int max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static Specification isBetween(String attribute, double min, double max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static Specification isBetween(String attribute, LocalDate min, LocalDate max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    /**
     * in 范围查询
     * @param attribute
     * @param c
     * @return
     */
    public static Specification in(String attribute, Collection c) {
        return (root, query, cb) -> root.get(attribute).in(c);
    }

    /**
     * 通过属性名构建大于等于value的查询条件
     */
    public static Specification greaterThan(String attribute, BigDecimal value) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
    }
    public static Specification greaterThan(String attribute, Long value) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), value);
    }
}
