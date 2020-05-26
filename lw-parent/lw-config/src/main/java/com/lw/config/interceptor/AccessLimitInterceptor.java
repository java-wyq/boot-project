package com.lw.config.interceptor;

import com.lw.config.annotation.AccessLimit;
import com.lw.config.exception.AccessLimitException;
import com.lw.config.exception.GlobalExceptionHandler;
import com.lw.config.exception.GlobalExpStatus;
import com.lw.utils.JwtTokenUtils;
import com.lw.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyanqiang
 * @title: AccessLimitInterceptor
 * @date 2020/5/2611:48
 */
public class AccessLimitInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AccessLimitInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("accessToken");
        String userId = null;
        if (token == null) {
            throw new AccessLimitException("accessToken should not be null ...", GlobalExpStatus.AccessLimitForNull.value());
        }
        Object user = JwtTokenUtils.verify(token).get("userId");
        if(!StringUtils.isEmpty(user)){
            userId = String.valueOf(user);
        }
        if(StringUtils.isEmpty(userId)){
            throw new AccessLimitException("userId should not be null ...", GlobalExpStatus.AccessLimitForNull.value());
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AccessLimit accessLimit = method.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                // 不需要限流验证
                return true;
            } else {
                int limit = accessLimit.limit();
                int timeScope = accessLimit.timeScope();
                String uri = request.getRequestURI();
                final String redisKey = userId + ":" + uri;
                Object o = RedisUtils.get(redisKey);
                if (!StringUtils.isEmpty(o)) {
                    int count = Integer.valueOf(o.toString());
                    if (count < limit) {
                        RedisUtils.set(redisKey, 1);
                    } else {
                        throw new AccessLimitException("request frequently ...", GlobalExpStatus.AccessLimit.value());
                    }
                } else {
                    RedisUtils.set(redisKey, 1, timeScope);
                }
            }
        }else {
            throw new AccessLimitException("request frequently ...", GlobalExpStatus.AccessLimitUnknown.value());
        }
        return true;
    }

}
