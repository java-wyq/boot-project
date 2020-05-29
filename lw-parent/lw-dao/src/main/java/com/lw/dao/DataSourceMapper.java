package com.lw.dao;

import com.lw.config.datasource.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataSourceMapper {
    List<DataSource> getDataSourceList();
    DataSource get(@Param("dataSourceId")String dataSourceId, @Param("tenantId")String tenantId);
}