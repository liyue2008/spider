package com.github.liyue2008.spider.core.service;

import com.github.liyue2008.spider.core.entity.JobConfig;
import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.entity.Proxy;
import com.github.liyue2008.spider.core.fetch.FetchWorker;
import com.github.liyue2008.spider.core.parser.ConfigurableParser;
import com.github.liyue2008.spider.core.parser.ConfigurableParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liyue on 2017/5/13.
 */
@Component
public class ProxyFetchService {
    private final static Logger logger = LoggerFactory.getLogger(ProxyFetchService.class);
    @Resource
    private ConfigService configService;
    @Resource
    private FetchWorker fetchWorker;
    @Resource
    private ConfigurableParserFactory configurableParserFactory;

    @Resource
    private  ProxyService proxyService;

    @Resource
    private TestProxyService testProxyService;

    @Scheduled(fixedDelay = 600000)
    private void fetchProxies(){
//        if(null== proxyJobConfigIds) return ;
        List<JobConfig> proxyJobConfigs = configService.getAllProxyJobConfigList();
        if(null==proxyJobConfigs) return;
        for(JobConfig jobConfig:proxyJobConfigs){
            if(null!=jobConfig){
                try {

                    ParserConfig parserConfig = configService.getParserConfigFullById(jobConfig.getParserId());
                    if(null!=parserConfig){
                        ConfigurableParser configurableParser = configurableParserFactory.getConfigurableParser();
                        configurableParser.setConfig(parserConfig);
                        jobConfig.setParser(configurableParser);



                        for(String url:jobConfig.getJobParamArray()){
                            List<String []> proxyResult = fetchWorker.fetch(url,parserConfig);

                            if(null!=proxyResult){
                                for(String [] row:proxyResult){
                                    try {
                                        proxyService.putProxyToQueue(testProxyService.testAsync(new Proxy(row[0], Integer.parseInt(row[1]),Proxy.TYPE_HTTP)));
                                        proxyService.putProxyToQueue(testProxyService.testAsync(new Proxy(row[0], Integer.parseInt(row[1]),Proxy.TYPE_HTTPS)));

                                    }catch (Exception e){
                                        logger.warn("Invalid proxy row: {}",row);
                                    }
                                }
                            }
                        }



                    }
                } catch (Exception e) {
                    logger.warn("Exception:",e);
                }
            }
        }

    }
}
