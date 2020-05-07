package com.lw.config.async;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangyanqiang
 * @title: AsyncService
 * @date 2020/4/2818:09
 */
@Service
public interface AsyncService<T extends Object,E extends Object> {

    /**
     * 异步执行接口
     * @param e
     */
    void  execueAsync(E e);

    /**
     * 异步执行接口
     * @param map
     */
    void execueAsync(Map<T,E> map);

    /**
     * 异步执行接口
     * @param list
     */
    void execueAsync(List<E> list);
}
