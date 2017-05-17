package com.github.liyue2008.spider.core.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by liyue on 2017/3/31.
 */
public class FileAppendUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileAppendUtil.class);
    public static void appendFailedUrl(String url,String failedFilePath){
        try {

            FileUtils.writeStringToFile(new File(failedFilePath), url + "\n","GBK",true);
        } catch (IOException e) {
            logger.warn("Exception:",e);
        }
    }
}
