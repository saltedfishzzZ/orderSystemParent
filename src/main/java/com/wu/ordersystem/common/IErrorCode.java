package com.wu.ordersystem.common;

/**
 * @author <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @name BaseService CopyRright (c) 2021 by
 * <a href="mailto:wujianxin@corp.51xiaodou.com">wujianxin</a>
 * @date 2021年09月23日
 * @description 封装api的接口
 */

public interface IErrorCode {
    /**
     * 返回状态码
     * @return code
     */
    Integer getcode();

    /**
     * 返回信息
     * @return message
     */
    String getMessage();
}
