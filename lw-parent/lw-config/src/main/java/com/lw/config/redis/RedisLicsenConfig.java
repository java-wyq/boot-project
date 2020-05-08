package com.lw.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author wangyanqiang
 * @title: RedisLicsenConfig
 * @date 2020/4/229:27
 */
@Configuration
public class RedisLicsenConfig {
    /**
     * 此配置适用于单机配置，集群需要采用分布式锁来实现过期事件监听
     * 可参考https://www.cnblogs.com/xiaobingblog/p/11490726.html
     * 可参考https://blog.csdn.net/XinhuaShuDiao/article/details/103272358
     */
    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
//    @Bean
//    public RedisKeysExpireListener keysExpireListener(RedisMessageListenerContainer listenerContainer){
//        return new RedisKeysExpireListener(listenerContainer);
//    }
}
