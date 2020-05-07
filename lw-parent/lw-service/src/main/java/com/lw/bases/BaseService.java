package com.lw.bases;

import com.lw.bases.BaseEntity;
import com.lw.bases.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangyanqiang
 * @title: BaseService
 * @date 2020/4/3014:41
 */
@Transactional(readOnly = true)
@Service
public class BaseService<M extends BaseMapper<T>,T extends BaseEntity>{

    @Autowired
    private M mapper;

    public T selectByPrimaryKey(Long primaryKey) {
        return mapper.selectByPrimaryKey(primaryKey);
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long primaryKey) {
        return mapper.deleteByPrimaryKey(primaryKey);
    }

    @Transactional(readOnly = false)
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Transactional(readOnly = false)
    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Long primaryKey) {
        return mapper.updateByPrimaryKey(primaryKey);
    }

    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Long primaryKey) {
        return mapper.updateByPrimaryKeySelective(primaryKey);
    }

    public List getList(T t) {
        return mapper.getList(t);
    }
}
