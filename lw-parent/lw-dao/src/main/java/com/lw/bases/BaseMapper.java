package com.lw.bases;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyanqiang
 * @title: BaseMapper
 * @date 2020/4/3014:22
 */
public interface BaseMapper<T> {

    /**
     * select by primaryKey
     * @param primaryKey
     * @return
     */
    T selectByPrimaryKey(Long primaryKey);

    /**
     * del by primaryKey
     * @param primaryKey
     * @return
     */
    int deleteByPrimaryKey(Long primaryKey);

    /**
     * insert T into tables
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * insert T into tables without '' or null  params
     * @param t
     * @return
     */
    int insertSelective(T t);


    /**
     * update tables data by primaryKey
     * @param primaryKey
     * @return
     */
    int updateByPrimaryKey(Long primaryKey);

    /**
     * update tables data without '' or null params by primaryKey
     * @param primaryKey
     * @return
     */
    int updateByPrimaryKeySelective(Long primaryKey);


    /**
     * get data from tables By T params
     * @param t
     * @return
     */
    List<T> getList(T t);
}
