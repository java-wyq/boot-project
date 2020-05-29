package com.lw.service;

import com.lw.config.datasource.DataSource;

import java.util.List;
public interface IDBChangeService {
 
    List<DataSource> getDataSourceList();
 
    boolean changeDb(String datasourceId) throws Exception;
 
}