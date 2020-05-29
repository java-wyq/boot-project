package com.lw.service.impl;

import com.lw.config.datasource.DBContextHolder;
import com.lw.config.datasource.DataSource;
import com.lw.config.datasource.DynamicDataSource;
import com.lw.dao.DataSourceMapper;
import com.lw.service.IDBChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/
@Service
public class DBChangeServiceImpl implements IDBChangeService {
    private static final Logger logger = LoggerFactory.getLogger(DBChangeServiceImpl.class);
    @Autowired
    DataSourceMapper dataSourceMapper;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Override
    public List<DataSource> getDataSourceList() {
        return dataSourceMapper.getDataSourceList();
    }
 
    @Override
    public boolean changeDb(String datasourceId) throws Exception {
        //默认切换到主数据源,进行整体资源的查找
        DBContextHolder.clearDataSource();
        List<DataSource> dataSourcesList = dataSourceMapper.getDataSourceList();
        for (DataSource dataSource : dataSourcesList) {
            if (dataSource.getDatasourceId().equals(datasourceId)) {
                logger.info("找到数据源--->{}",  dataSource.getDatasourceId());
                //创建数据源连接&检查 若存在则不需重新创建
                dynamicDataSource.createDataSourceWithCheck(dataSource);
                //切换到该数据源
                DBContextHolder.setDataSource(dataSource.getDatasourceId());
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean changeDb(String datasourceId,String tenantId) throws Exception {
        //默认切换到主数据源,进行整体资源的查找
        DBContextHolder.clearDataSource();
        DataSource dataSource = dataSourceMapper.get(datasourceId,tenantId);
        if (dataSource.getDatasourceId().equals(datasourceId)) {
            logger.info("找到数据源--->{}",  dataSource.getDatasourceId());
            //创建数据源连接&检查 若存在则不需重新创建
            dynamicDataSource.createDataSourceWithCheck(dataSource);
            //切换到该数据源
            DBContextHolder.setDataSource(dataSource.getDatasourceId());
            return true;
        }
        return false;
    }


}