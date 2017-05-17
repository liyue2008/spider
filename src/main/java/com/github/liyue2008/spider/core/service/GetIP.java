package com.github.liyue2008.spider.core.service;

import com.github.liyue2008.spider.core.entity.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyue on 2017/5/15.
 */
@Component
public class GetIP {

    private final static String order = "cc5c72d6c4b617592d3f8b816e9f6211";
    private final static Logger logger = LoggerFactory.getLogger(GetIP.class);
    @Resource
    private TestProxyService testProxyService;
    @Resource
    private ProxyService proxyService;

    @Scheduled(fixedDelay = 10000)
    public void getDynamicProxy(){
        try {
            java.net.URL url = new java.net.URL("http://dynamic.goubanjia.com/dynamic/get/" + order + ".html?ttl");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3000);
            connection = (HttpURLConnection)url.openConnection();

            InputStream raw = connection.getInputStream();
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[in.available()];
            int bytesRead = 0;
            int offset = 0;
            while(offset < data.length) {
                bytesRead = in.read(data, offset, data.length - offset);
                if(bytesRead == -1) {
                    break;
                }
                offset += bytesRead;
            }
            in.close();
            raw.close();
            String[] res = new String(data, "UTF-8").split("\n");
            List<String> ipList = new ArrayList<>();
            for (String ip : res) {
                try {
                    String[] parts = ip.split(",");
                    if (Integer.parseInt(parts[1]) > 0) {
                        ipList.add(parts[0]);
                    }
                } catch (Exception e) {
                }
            }
            if (ipList.size() > 0) {
                for(String ipport:ipList){
                    Proxy proxy  = new Proxy(ipport.split(":")[0],Integer.parseInt(ipport.split(":")[1]),Proxy.TYPE_HTTP);
                    proxyService.putProxyToQueue(testProxyService.testAsync(proxy));

                    proxy  = new Proxy(ipport.split(":")[0],Integer.parseInt(ipport.split(":")[1]),Proxy.TYPE_HTTPS);
                    proxyService.putProxyToQueue(testProxyService.testAsync(proxy));
                }

            }
        } catch (Exception e) {
            logger.warn("Exception:",e);
        }
    }


}

