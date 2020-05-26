package com.lw.config.exception;

import com.lw.dto.AjaxResult;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyanqiang
 * @title: GlobalExceptionHandler
 * @date 2020/5/2616:25
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler
    public AjaxResult defaultExceptionHandler(HttpServletRequest request,Exception ex){
        if(ex instanceof AccessLimitException){
            return new AjaxResult(((AccessLimitException) ex).getStatus(),ex.getMessage());
        }
        if(ex instanceof JwtException){
            return new AjaxResult(AjaxResult.Type.ERROR,"accessToken should not be trusted ...");
        }
        return new AjaxResult(AjaxResult.Type.WARN,ex.getMessage());
    }

}
