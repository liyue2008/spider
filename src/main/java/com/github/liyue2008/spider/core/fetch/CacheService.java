package com.github.liyue2008.spider.core.fetch;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyue on 2017/5/15.
 */
@Component
@Aspect
public class CacheService {
    private final Logger logger = LoggerFactory.getLogger(CacheService.class);
    private Cache<String,Connection.Response> cache = CacheBuilder.newBuilder()
            .maximumSize(100000)

            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();


    @Around("execution(public * com.github.liyue2008.spider.core.fetch.HttpConnectionService.getResponse(..))")
    @Order(10)
    public Object processCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object [] args = proceedingJoinPoint.getArgs();
        if(args.length == 1 && args[0] instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) args[0];
            if(request.getUrl()!=null){
                Connection.Response response = cache.getIfPresent(request.getUrl());
                if(null==response){
                    response = (Connection.Response) proceedingJoinPoint.proceed();
                    if(null!= response && response.statusCode() == 200){
//                        logger.info("存入缓存！！！！");
                        cache.put(request.getUrl(),response);
                    }
                }else{
//                    logger.info("命中缓存！！！！！");
                }

                return response;
            }
        }

        return proceedingJoinPoint.proceed();
    }


    public void  removeCache(String url){
        cache.invalidate(url);
    }
}
