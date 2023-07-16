package com.ocean.common.restful;

import lombok.Data;

/**
 *  用于restful风格的api接口返回的数据的封装。
 * @author ltx
 */
@Data
public class RestfulResponse<T> {
    T data;

    Integer code;

    String message;

    Boolean success;

    /**
     * 生成统一的restful请求响应对象
     * @param code  请求状态码
     * @param msg   请求结果信息  String
     * @param success   请求是否成功 true/false
     */
    public RestfulResponse(Integer code, String msg, Boolean success){
        this.code = code;
        this.message = msg;
        this.success = success;
    }

    /**
     *
     * @param code
     * @param msg
     * @param data
     */
    public RestfulResponse(Integer code, String msg, Boolean success, T data){
        this.code = code;
        this.message = msg;
        this.success = success;
        this.data = data;
    }
    public static <T> RestfulResponse<T> ok(String msg,T data){
        return new RestfulResponse<>(200,msg,true,data);
    }

    public static <T> RestfulResponse<T> fail(String msg,T data){
        return new RestfulResponse<>(201,msg,true,data);
    }
}
