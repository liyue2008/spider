package com.github.liyue2008.spider.core.job;

import com.github.liyue2008.spider.core.entity.FetchTask;
import com.github.liyue2008.spider.core.fetch.FetchResult;
import com.github.liyue2008.spider.core.fetch.FetchWorker;
import com.github.liyue2008.spider.core.parser.IParser;
import com.github.liyue2008.spider.core.utils.FileAppendUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyue on 2017/3/31.
 */

public abstract class SpiderJobTemplate implements ISpiderJob {
    private final Logger logger = LoggerFactory.getLogger(SpiderJobTemplate.class);

    protected IParser parser;

    protected Integer sortResultIndex;

    protected String csvFilePath;

    protected int timeout = -1;
    protected String userAgent = null;
    protected String referrer = null;
    @Resource
    protected FetchWorker fetchWorker;

    private int totalTaskCount = 0;
    private List<Future<FetchResult>> resultList;


    @Override
    public int getProgress(){
        if(totalTaskCount<=0 || resultList == null) return 0;
        int notFinishedCount = resultList.size();
        if(notFinishedCount > totalTaskCount) notFinishedCount = totalTaskCount;
        return 100 * (totalTaskCount - notFinishedCount) / totalTaskCount;
    }
    protected void handleAsyncResult(List<Future<FetchResult>> resultList){
        totalTaskCount = resultList.size();
        this.resultList = resultList;
        while(resultList.size()>0) {
            List<Future<FetchResult>> toBeAdded = new ArrayList<>();
            Iterator<Future<FetchResult>> iterator = resultList.iterator();
            while (iterator.hasNext()) {
                Future<FetchResult> fetchResultFuture = iterator.next();
                if (fetchResultFuture.isDone()) {
                    try {
                        FetchResult fetchResult = fetchResultFuture.get(10, TimeUnit.MILLISECONDS);
                        if (FetchResult.STATUS_RETRY == fetchResult.getStatus()) {
                            Future<FetchResult> future = fetchWorker.fetch(fetchResult.getFetchTask());
                            toBeAdded.add(future);
                        }else if(FetchResult.STATUS_FAILED == fetchResult.getStatus()){
                            logger.info("Task failed! url:{}",fetchResult.getFetchTask().getUrl());
                            FileAppendUtil.appendFailedUrl(fetchResult.getFetchTask().getUrl(),fetchResult.getFetchTask().getCsvFilePath() + ".failed");
                        }else if(FetchResult.STATUS_SUCCESS == fetchResult.getStatus()){
                            logger.info("Task finished. url:{}",fetchResult.getFetchTask().getUrl());
                            if(null!=fetchResult.getMoreTasks()){
                                for(FetchTask task:fetchResult.getMoreTasks()){
                                    Future<FetchResult> future = fetchWorker.fetch(task);
                                    toBeAdded.add(future);

                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.warn("Exception:", e);
                    }
                    iterator.remove();
                }
            }

            totalTaskCount+=toBeAdded.size();
            resultList.addAll(toBeAdded);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                //logger.warn("Exception:", e);
            }

        }


    }


    @Override
    public IParser getParser() {
        return parser;
    }

    @Override
    public void setParser(IParser parser) {
        this.parser = parser;
    }


    @Override
    public String getCsvFilePath() {
        return csvFilePath;
    }

    @Override
    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public void setParameters(String[] parameters) {

    }

    public void setSortResultIndex(Integer index){
        this.sortResultIndex = index;
    }
    public Integer getSortResultIndex(){
        return sortResultIndex;
    }
//
//    @Override
//    public int getTimeout() {
//        return timeout;
//    }
//
//    @Override
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    @Override
//    public String getUserAgent() {
//        return userAgent;
//    }
//
//    @Override
//    public void setUserAgent(String userAgent) {
//        this.userAgent = userAgent;
//    }
//
//    @Override
//    public String getReferrer() {
//        return referrer;
//    }
//
//    @Override
//    public void setReferrer(String referrer) {
//        this.referrer = referrer;
//    }
}
