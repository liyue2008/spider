package com.github.liyue2008.spider.core.job;

import com.github.liyue2008.spider.core.entity.FetchTask;
import com.github.liyue2008.spider.core.fetch.FetchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by liyue on 2017/3/31.
 */
@Component("pageJob")
@Scope("prototype")
public class PageJob extends SpiderJobTemplate  {
    private final Logger logger = LoggerFactory.getLogger(PageJob.class);
//    @Resource
//    private CsvFileService csvFileService;
    private String urlPattern;

    private int pageCount;



    @Override
//    @Async
    public void runAsync(){
        //1-6001
        List<Future<FetchResult>> resultList = new ArrayList<>();
        int page = 0;
        while(++page<=pageCount){
            String url = String.format(urlPattern,page);
            FetchTask task = new FetchTask(url,page,csvFilePath, parser);
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
        urlPattern = parameters[0];
        pageCount = Integer.parseInt(parameters[1]);
    }


}
