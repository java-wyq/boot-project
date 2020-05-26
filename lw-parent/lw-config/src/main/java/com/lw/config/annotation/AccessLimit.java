package com.lw.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangyanqiang
 * @title: AccessLimit
 * @date 2020/5/2611:44
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 请求限制数
     */
    int limit() default 10;


    /**
     * 时间范围(秒)
     */
    int timeScope() default 60*10;
}
