package com.ocean.web.exception;

import com.ocean.common.enums.CommonResCode;
import com.ocean.common.exceptions.*;
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
    @ExceptionHandler(value = RefreshTokenExpireException.class)
    public RestfulResponse<String> refreshTokenExpiresTimeHandler(Exception e){
        log.error("当前发生了刷新令牌过期的异常",e);
        return RestfulResponse.fail(CommonResCode.REFRESH_TOKEN_EXPIRE.description,CommonResCode.REFRESH_TOKEN_EXPIRE.code,CommonResCode.REFRESH_TOKEN_EXPIRE.description);
    }
    @ExceptionHandler(value = RefreshTokenNotExistException.class)
    public RestfulResponse<String> refreshTokenDeletedHandler(Exception e){
        log.error("当前发生了刷新令牌过期的异常",e);
        return RestfulResponse.fail(CommonResCode.REFRESH_TOKEN_DELETED.description,CommonResCode.REFRESH_TOKEN_DELETED.code,CommonResCode.REFRESH_TOKEN_DELETED.description);
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
                writer.write(result);
            }
            if(e instanceof  AccessTokenDeletedException){
                RestfulResponse<String> restfulResponse = accessTokenDeletedHandler(e);
                String result = JsonUtils.writeToString(restfulResponse);
                writer.write(result);
            }
            if(e instanceof RefreshTokenExpireException){
                RestfulResponse<String> stringRestfulResponse = refreshTokenExpiresTimeHandler(e);
                String result = JsonUtils.writeToString(stringRestfulResponse);
                writer.write(result);
            }
            if(e instanceof RefreshTokenNotExistException){
                RestfulResponse<String> stringRestfulResponse = refreshTokenDeletedHandler(e);
                String result = JsonUtils.writeToString(stringRestfulResponse);
                writer.write(result);
            }
            writer.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
