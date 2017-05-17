package com.github.liyue2008.spider.core.job;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by liyue on 2017/3/31.
 */
@Component("failedUrlJob")
@Scope("prototype")
public class FailedUrlJob extends UrlListJob {
    private final Logger logger = LoggerFactory.getLogger(FailedUrlJob.class);



    private String failedUrlFilePath;
    @Override
//    @Async
    public void runAsync(){

        try {
            List<String> urls = FileUtils.readLines(new File(failedUrlFilePath));
            setUrls(urls);
            super.runAsync();
        } catch (IOException e) {
            logger.warn("Exception:",e);
        }


    }




    @Override
    public void setParameters(String[] parameters) {
        failedUrlFilePath = parameters[0];
    }

}
