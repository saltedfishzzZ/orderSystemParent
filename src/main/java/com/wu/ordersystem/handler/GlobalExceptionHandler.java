package com.wu.ordersystem.handler;

import com.wu.ordersystem.common.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wujianxin
 * @date 2021-09-23
 * @description 全局异常处理器
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult bindExceptionHandle(BindException e) {
        return CommonResult.failed().message(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        return CommonResult.failed().message(e.getMessage());
    }
}
