package com.github.liyue2008.spider.core.job;

import com.github.liyue2008.spider.core.parser.IParser;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by liyue on 2017/3/31.
 */
public interface ISpiderJob {
    @Async()
    void runAsync();

    public int getProgress();

    IParser getParser();
    void setParser(IParser parser);

    String getCsvFilePath();

    void setCsvFilePath(String csvFilePath);
    void setParameters(String [] parameters);

//    void setReferrer(String referrer);
//    void setTimeout(int timeout);
//    void setUserAgent(String userAgent);
//
//    String getReferrer();
//    int getTimeout();
//    String getUserAgent();

    void setSortResultIndex(Integer index);
    Integer getSortResultIndex();
}
