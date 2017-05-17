package com.github.liyue2008.spider.core.fetch;

import com.github.liyue2008.spider.core.entity.Proxy;
import com.github.liyue2008.spider.core.service.ProxyService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;


/**
 * Created by liyue on 2017/5/15.
 */
@Component
public class HttpConnectionService {
    private final Logger logger = LoggerFactory.getLogger(HttpConnectionService.class);
    @Resource
    private ProxyService proxyService;
    public Connection.Response getResponse(HttpRequest request) throws IOException,URISyntaxException{
        int retry = request.getRetryCount();
        while (true) {
            try {

                Connection connection = Jsoup.connect(request.getUrl());
                if (null != request.getReferrer() && !request.getReferrer().isEmpty()) {
                    connection = connection.referrer(request.getReferrer());
                }
                if (null != request.getUserAgent() && !request.getUserAgent().isEmpty()) {
                    connection = connection.userAgent(request.getUserAgent());
                }
                if (request.getTimeout()!=null && request.getTimeout() >= 0) {
                    connection = connection.timeout(request.getTimeout() * 1000);
                }

                Proxy proxy = null;
                if(request.isEnableProxy()){
                    proxy = proxyService.getARandomProxy(request.getUrl().trim().toLowerCase().startsWith("https://")?Proxy.TYPE_HTTPS:Proxy.TYPE_HTTP);
                    if(null!=proxy){
                        connection.proxy(proxy.getIp(),proxy.getPort());
                    }
                }
//                connection.proxy("1.192.240.133",8118);
                logger.info("Requesting [{}], proxy is [{}]",request.getUrl(),proxy);
                Connection.Response response = connection.execute();


                return response;
            } catch (SocketTimeoutException te) {

                if (retry-- <= 0) throw te;
            }
        }
    }


}
