package com.github.liyue2008.spider.core.entity;

import com.github.liyue2008.spider.core.parser.IParser;

/**
 * Created by liyue on 2017/3/31.
 */
public class FetchTask {
    private int retry=0;

    public boolean isRetryTask(){
        return retry > 0;
    }
//    private BlockingQueue<FetchTask> fetchTaskQueue;
//    private BlockingQueue<PersistTask> persistTaskQueue;
    public  void addRetry(){
        retry ++;
    }

    public boolean hasMoreRetry(){
        return retry < 5;
    }

    private String url ;
    private int page;
    private String csvFilePath="spider.csv";
    private IParser parser;
//    private int timeout = -1;
//    private String userAgent = null;
//    private String referrer = null;


    public FetchTask(){}

    public FetchTask(String url,int page,String csvFilePath,IParser parser){
        this.url = url;
        this.page = page;
        this.csvFilePath = csvFilePath;
        this.parser = parser;
//        this.fetchTaskQueue = fetchTaskQueue;
//        this.persistTaskQueue = persistTaskQueue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

//    public int getTimeout() {
//        return timeout;
//    }
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    public String getUserAgent() {
//        return userAgent;
//    }
//
//    public void setUserAgent(String userAgent) {
//        this.userAgent = userAgent;
//    }
//
//    public String getReferrer() {
//        return referrer;
//    }
//
//    public void setReferrer(String referrer) {
//        this.referrer = referrer;
//    }
}
