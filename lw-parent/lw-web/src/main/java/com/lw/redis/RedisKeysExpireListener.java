package com.lw.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author wangyanqiang
 * @title: RedisKeysExpireListener 实现自己的key监听过期后的业务逻辑，需要在RedisLicsenConfig中注册自己的bean
 * @date 2020/4/2911:20
 */
public class RedisKeysExpireListener extends KeyExpirationEventMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisKeysExpireListener.class);

    public RedisKeysExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        logger.info("redis expired key : "+key);
        //TODO your proceess

    }
}
