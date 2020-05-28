package com.lw.service;

import com.lw.config.datasource.DataSource;

import java.util.List;
public interface IDBChangeService {
 
    List<DataSource> get();
 
    boolean changeDb(String datasourceId) throws Exception;
 
}