package com.github.liyue2008.spider.core.service;

import com.github.liyue2008.spider.core.entity.Proxy;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by liyue on 2017/5/13.
 */
@Component
public class TestProxyService {
    private String httpTestUrl = "http://www.baidu.com";
    private String httpsTestUrl = "https://www.amazon.cn";
    private final static Logger logger = LoggerFactory.getLogger(TestProxyService.class);

    private String httpTestTitle = "百度一下，你就知道";
    private String httpsTestTitle = "亚马逊-网上购物商城：要网购, 就来Z.cn!";
    @Async("proxyExecutor")
    public Future<Proxy> testAsync(Proxy proxy) {
        String url = proxy.getType()==Proxy.TYPE_HTTP?httpTestUrl:httpsTestUrl;
        String titleExpect = proxy.getType()==Proxy.TYPE_HTTP?httpTestTitle:httpsTestTitle;
        try {
            String title = Jsoup.connect(url).proxy(proxy.getIp(),proxy.getPort()).timeout(2000).get().title();
            proxy.setGood(titleExpect.equals(title));
            //logger.info("{} proxy [{}]! title: {}",(proxy.isGood()?"GOOD":"BAD"),proxy.toString(),title);



        } catch (IOException e) {
            proxy.setGood(false);

            //logger.info("Bad proxy [{}]!",proxy.toString());

        }
        return new AsyncResult<>(proxy);
    }



}
