package com.ocean.web.exception;

import com.ocean.common.enums.CommonResCode;
import com.ocean.common.exceptions.AccessTokenDeletedException;
import com.ocean.common.exceptions.AccessTokenExpiresTimeException;
import com.ocean.common.exceptions.AdminUserNotExistException;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author ltx
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public RestfulResponse<?> defaultExceptionHandler(HttpServletRequest req,Exception e){
        log.error("systemError",e);
        return RestfulResponse.fail("出现异常请联系管理员",e.getMessage());
    }
    @ExceptionHandler(value = AccessTokenExpiresTimeException.class)
    public RestfulResponse<String> accessTokenExpiresTimeExceptionHandler(Exception e){
        log.error("当前发生了访问令牌过期异常",e);
        return RestfulResponse.fail(CommonResCode.ACCESS_TOKEN_EXPIRE.description,CommonResCode.ACCESS_TOKEN_EXPIRE.code,CommonResCode.ACCESS_TOKEN_EXPIRE.description);
    }
    @ExceptionHandler(value = AccessTokenDeletedException.class)
    public RestfulResponse<String> accessTokenDeletedHandler(Exception e){
        log.error("当前发生了访问令牌已被删除异常",e);
        return RestfulResponse.fail(CommonResCode.ACCESS_TOKEN_DELETED.description, CommonResCode.ACCESS_TOKEN_DELETED.code,CommonResCode.ACCESS_TOKEN_DELETED.description);
    }

    @ExceptionHandler(value = AdminUserNotExistException.class)
    public RestfulResponse<String> adminUserNotExistExceptionHandler(Exception e) {
        log.error("当前发生了认证用户不存在的异常", e);
        return RestfulResponse.fail(CommonResCode.ADMIN_USER_NOT_EXIST.description, CommonResCode.ADMIN_USER_NOT_EXIST.code, CommonResCode.ADMIN_USER_NOT_EXIST.description);
    }
    public void handlerAccessTokenException(HttpServletResponse response, Exception e) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        Writer writer = null;
        try {
            writer = response.getWriter();
            if(e instanceof AccessTokenExpiresTimeException){
                RestfulResponse<String> stringRestfulResponse = accessTokenExpiresTimeExceptionHandler(e);
                String result = JsonUtils.writeToString(stringRestfulResponse);
                response.setStatus(stringRestfulResponse.getCode());
                writer.write(result);
                writer.flush();
            }
            if(e instanceof  AccessTokenDeletedException){
                RestfulResponse<String> restfulResponse = accessTokenDeletedHandler(e);
                String result = JsonUtils.writeToString(restfulResponse);
                response.setStatus(restfulResponse.getCode());
                writer.write(result);
                writer.flush();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
