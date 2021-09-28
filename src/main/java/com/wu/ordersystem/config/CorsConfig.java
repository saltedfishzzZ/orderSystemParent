package com.wu.ordersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description 跨域处理器
 */

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1.允许任何来源
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        // 2.允许任何请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 3.允许任何方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
