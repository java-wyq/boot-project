package com.lw.service.impl;

import com.lw.bases.BaseService;
import com.lw.config.async.AsyncService;
import com.lw.dao.LwMapper;
import com.lw.domain.LwEntity;
import com.lw.service.ILwService;
import com.lw.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangyanqiang
 * @title: LwServiceImpl
 * @date 2020/4/3015:08
 */
@Service
public class LwServiceImpl extends BaseService<LwMapper, LwEntity> implements ILwService, AsyncService<String,Object> {

private static final Logger logger = LoggerFactory.getLogger(LwServiceImpl.class);
    @Override
    @Async("asyncServiceExecutorWithMonitorExecutor")
    public void execueAsync(Object o) {

    }

    @Override
    @Async("asyncServiceExecutorWithMonitorExecutor")
    public void execueAsync(Map<String,Object> map) {
        logger.info("async start ...");
        if(null != map){
            String key = map.get("key").toString();
            RedisUtils.set("key",key);
        }
        logger.info("async end ...");
    }

    @Override
    @Async("asyncServiceExecutorWithMonitorExecutor")
    public void execueAsync(List<Object> list) {

    }
}
