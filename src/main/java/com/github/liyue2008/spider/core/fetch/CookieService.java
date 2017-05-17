package com.github.liyue2008.spider.core.fetch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Connection;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyue on 2017/5/15.
 */
@Component
@Aspect
public class CookieService {

    private Map<String,Map<String,String>> cookiesMap  = new HashMap<>();

    @Around("execution(public * com.github.liyue2008.spider.core.fetch.HttpConnectionService.getResponse(..))")
    @Order(20)
    public Object processCookies(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object [] args = proceedingJoinPoint.getArgs();
        String domainName = null;
        if(args.length == 1 && args[0] instanceof HttpRequest){
            HttpRequest request = (HttpRequest ) args[0];
            domainName = getDomainName(request.getUrl());
            if(null==request.getCookies()) { //只有未设置过cookies 才会自动设置cookies


                if (null != domainName && !domainName.isEmpty()) {
                    Map<String, String> cookies = cookiesMap.get(domainName);
                    if (null != cookies) {
                        request.setCookies(cookies);
                    }
                }
            }
        }

        Object ret = proceedingJoinPoint.proceed();

        if(ret instanceof Connection.Response){
            Connection.Response response = (Connection.Response) ret;

            if(null!=domainName && !domainName.isEmpty() && response.statusCode() == 200){
                    cookiesMap.put(domainName,response.cookies());
            }
        }

        return ret;

    }

    private String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
