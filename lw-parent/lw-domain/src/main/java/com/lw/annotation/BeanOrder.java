package com.lw.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @author wangyanqiang
 * @title: BeanOrder
 * @date 2020/5/2117:11
 */
@Target({METHOD,FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanOrder {
    int order() default -1;
}
