package com.github.liyue2008.spider.core.service;

import com.github.liyue2008.spider.core.entity.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liyue on 2017/5/12.
 */
@Component
public class ProxyService {
    private final static Logger logger = LoggerFactory.getLogger(ProxyService.class);


    @Resource
    private TestProxyService testProxyService;

    private BlockingQueue<Future<Proxy>> proxyQueue= new LinkedBlockingQueue<>();

    private List<Proxy> httpProxies = new ArrayList<>();
    private List<Proxy> httpsProxies = new ArrayList<>();




    @Scheduled(fixedDelay = 60000)
    private void checkProxies(){
        //检查旧的
        List<Proxy> allProxies = getAllProxies();
        if(null!=allProxies){
            for(Proxy proxy:allProxies){
                try {
                    proxyQueue.put(testProxyService.testAsync(proxy));
                } catch (InterruptedException ignored) {

                }
            }
        }



    }


    @Scheduled(fixedDelay = 200)
    private void processTestResult(){
        //补充新的
        try {
            Future<Proxy> proxyFuture = proxyQueue.poll(1, TimeUnit.SECONDS);
            if(null!=proxyFuture){
                if(proxyFuture.isDone()){
                    Proxy proxy = proxyFuture.get();
                    if(null!=proxy){
                        if(proxy.isGood()) {
                            if (proxy.getType() == Proxy.TYPE_HTTP) {
                                if(!httpProxies.contains(proxy)) {
                                    httpProxies.add(proxy);
                                }
                            } else {
                                if(!httpsProxies.contains(proxy)) {
                                    httpsProxies.add(proxy);
                                }
                            }
                        }else{
                            if (proxy.getType() == Proxy.TYPE_HTTP) {
                                if(httpProxies.contains(proxy)) {
                                    httpProxies.remove(proxy);
                                }
                            } else {
                                if(httpsProxies.contains(proxy)) {
                                    httpsProxies.remove(proxy);
                                }
                            }

                        }
                    }
                }else{
                    proxyQueue.put(proxyFuture);
                }
            }
        } catch (InterruptedException | ExecutionException ignored) {}
    }

    public Proxy getARandomProxy(int proxyType){
        List<Proxy> proxyList = proxyType == Proxy.TYPE_HTTP ? httpProxies:httpsProxies;

        if(proxyList.size()>0){
            return proxyList.get(new Random().nextInt(proxyList.size()));
        }
        return null;
    }

    public List<Proxy> getAllProxies(){
        List<Proxy> proxyList = new ArrayList<>();
        proxyList.addAll(httpProxies);
        proxyList.addAll(httpsProxies);
        return proxyList;
    }


    void putProxyToQueue(Future<Proxy> proxy) throws InterruptedException {
        proxyQueue.put(proxy);
    }
}
