package com.wu.ordersystem.utils;

import com.wu.ordersystem.common.Constants;

import java.time.LocalDateTime;

/**
 * @author saltedfishzzZ
 * @date 2021-09-30
 * @description
 */

public class GenerateTimeUtil {
    public static String generateNowTime() {
        return LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER);
    }

}
