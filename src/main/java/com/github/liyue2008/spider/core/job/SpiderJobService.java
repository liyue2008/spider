package com.github.liyue2008.spider.core.job;

import com.github.liyue2008.spider.core.entity.JobConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by liyue on 2017/4/7.
 */
@Service
public class SpiderJobService {
    @Autowired
    private ApplicationContext applicationContext;

    private final static Logger logger = LoggerFactory.getLogger(SpiderJobService.class);
    public void runJob(JobConfig jobConfig) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ISpiderJob spiderJob = runJobAsync(jobConfig);
        while(true){
            int progress = spiderJob.getProgress();
            if(progress==100){
                break;
            }else{
                logger.info("Progress:{}%",progress);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                logger.warn("Exception:",e);
            }
        }
    }


    public ISpiderJob runJobAsync(JobConfig jobConfig)throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        ISpiderJob spiderJob = applicationContext.getBean(jobConfig.getJobType(),ISpiderJob.class);
        spiderJob.setParameters(jobConfig.getJobParamArray());
        spiderJob.setCsvFilePath(jobConfig.getCsvFilePath());

        spiderJob.setParser(jobConfig.getParser());
        spiderJob.setSortResultIndex(jobConfig.getSortResultIndex());
//        spiderJob.setUserAgent(jobConfig.getUserAgent());
//        spiderJob.setTimeout(jobConfig.getTimeout());
        spiderJob.runAsync();
        return  spiderJob;
    }
}
