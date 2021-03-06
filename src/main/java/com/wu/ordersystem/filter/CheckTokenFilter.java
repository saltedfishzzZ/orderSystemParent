package com.wu.ordersystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ordersystem.common.Constants;
import com.wu.ordersystem.config.WhiteListUrlProps;
import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import com.wu.ordersystem.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author saltedfishzzZ
 * @date 2021-09-28
 * @description
 */

@WebFilter(filterName = "checkTokenFilter", urlPatterns = "/")
@Component
public class CheckTokenFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CheckTokenFilter.class);

    @Autowired
    private WhiteListUrlProps whiteListUrlProps;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Headers", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        // ????????????
        String requestURI = request.getRequestURI();
        // ???????????????
        List<String> whiteListUrl = whiteListUrlProps.getWhiteListUrl();

        // ???????????????????????????
        for (String str : whiteListUrl) {
            if (requestURI.startsWith(str)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
//        if (whiteListUrl.contains(requestURI)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }

        // ??????token????????????
        String token = request.getHeader("token");
        if (Objects.isNull(token)) {
            CommonResult commonResult = CommonResult.unauth().message("?????????, ????????????!");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(commonResult));
            response.flushBuffer();
            return;
        }
        // ??????token??????????????????
        if (jwtTokenUtil.validateToken(token)) {
            logger.warn("{}-----?????????token?????????", GenerateTimeUtil.generateNowTime());
            // token????????? ??????redis????????????token
            String username = jwtTokenUtil.getUsernameFromToken(token);
            stringRedisTemplate.boundHashOps(Constants.ORDER_USER_TOKEN_KEY).delete(username);
            CommonResult commonResult = CommonResult.unauth().message("token?????????");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(commonResult));
            response.flushBuffer();
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
