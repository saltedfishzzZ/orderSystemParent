package com.wu.ordersystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ordersystem.WhiteListUrlProps;
import com.wu.ordersystem.common.CommonResult;
import com.wu.ordersystem.utils.GenerateTimeUtil;
import com.wu.ordersystem.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("{}-----检验token中", GenerateTimeUtil.generateNowTime());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 请求路径
        String requestURI = request.getRequestURI();
        // 请求白名单
        List<String> whiteListUrl = whiteListUrlProps.getWhiteListUrl();

        // 校验是否在白名单内
        if (whiteListUrl.contains(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 进行token存在校验
        String token = request.getHeader("token");
        if (Objects.isNull(token)) {
            logger.warn("{}-----请求时没有token", GenerateTimeUtil.generateNowTime());
            CommonResult commonResult = CommonResult.unauth().message("header中不存在token");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(commonResult));
            response.flushBuffer();
            return;
        }
        // 进行token是否有效校验
        if (jwtTokenUtil.validateToken(token)) {
            logger.warn("{}-----请求时token已过期", GenerateTimeUtil.generateNowTime());
            CommonResult commonResult = CommonResult.unauth().message("token已过期");
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
