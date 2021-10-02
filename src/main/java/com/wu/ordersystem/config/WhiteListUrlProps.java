package com.wu.ordersystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author saltedfishzzZ
 * @date 2021-09-29
 * @description
 */

@Component
@ConfigurationProperties(prefix = "order-system")
public class WhiteListUrlProps {

    private List<String> whiteListUrl;

    public List<String> getWhiteListUrl() {
        return whiteListUrl;
    }

    public void setWhiteListUrl(List<String> whiteListUrl) {
        this.whiteListUrl = whiteListUrl;
    }
}
