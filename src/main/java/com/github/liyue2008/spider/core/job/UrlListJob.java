package com.github.liyue2008.spider.core.job;

import com.github.liyue2008.spider.core.entity.FetchTask;
import com.github.liyue2008.spider.core.fetch.FetchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by liyue on 2017/3/31.
 */
@Component("urlListJob")
@Scope("prototype")
public class UrlListJob extends SpiderJobTemplate {
    private final Logger logger = LoggerFactory.getLogger(UrlListJob.class);
//    @Resource
//    private CsvFileService csvFileService;

    private List<String> urls;



    @Override
//    @Async
    public void runAsync(){
        List<Future<FetchResult>> resultList = new ArrayList<>();
        for(String url:urls){
            FetchTask task = new FetchTask(url,1,csvFilePath,parser);
//            if(null!=userAgent) task.setUserAgent(userAgent);
//            if(null!=referrer) task.setReferrer(referrer);
//            if(timeout>=0) task.setTimeout(timeout);
            logger.info("Task created:{}",url);
            Future<FetchResult> fetchResultFuture = fetchWorker.fetch(task);
            resultList.add(fetchResultFuture);
        }
        handleAsyncResult(resultList);

//        if(getSortResultIndex()!=null && getSortResultIndex() >=0){
//            try {
//                csvFileService.sortCsv(csvFilePath,getSortResultIndex());
//            } catch (IOException e) {
//                logger.warn("Exception:",e);
//            }
//        }
    }


    @Override
    public void setParameters(String[] parameters) {

        urls = Arrays.asList(parameters);
    }



    public void setUrls(List<String> urls) {
        this.urls = urls;
    }


}
