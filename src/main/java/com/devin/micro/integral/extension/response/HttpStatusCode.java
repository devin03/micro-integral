package com.devin.micro.integral.extension.response;

/**
 * 统一的公告的状态码处理
 * 如果有需要自定义的，请继承该类
 * @author wangdongming
 * @date 2020/08/15
 */
public class HttpStatusCode {

    /*** 公共状态码 ***/
    /**
     * 处理成功
     */
    public static final int SUCCESS = 200;
    /**
     * 参数错误
     */
    public static final int PARAMS_ERROR = 401;
    /**
     * 重复执行
     */
    public static final int REPEAT_EXECUTE = 402;
    /**
     * 服务异常
     */
    public static final int SERVICE_ERROR = 500;
    /**
     * 处理中
     */
    public static final int SERVICE_PROCESSING = 502;
}
