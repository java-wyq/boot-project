package com.lw.dao;

import com.lw.config.datasource.DataSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataSourceMapper {
    List<DataSource> get();
}