package com.github.liyue2008.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Created by liyue on 2017/3/30.
 */
@SpringBootApplication

public class AppMain {



    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AppMain.class, args);

//        ConfigurableApplicationContext context = new SpringApplicationBuilder()
//                .sources(AppMain.class)
//                .bannerMode(Banner.Mode.OFF)
//                .run(args);
//        AvailableJobConfigs availableJobConfigs = context.getBean(AvailableJobConfigs.class);
//        SpiderJobService spiderJobService = context.getBean(SpiderJobService.class);
//        for(String job:args) {
//            for (JobConfig jobConfig : availableJobConfigs.getJobConfigList()) {
//                try {
//                    if(job.equals(jobConfig.getJobName())) {
//                        spiderJobService.runJob(jobConfig);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }


//        for(String job:args){
//            if("QQDuShu".equals(job)){
//
//                ISpiderJob spiderJob = context.getBean("pageJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[] {"http://dushu.qq.com/store/index/sortkey/2/ps/50/p/%d","2059"});
//                spiderJob.setCsvFilePath("QQDuShu.csv");
//                spiderJob.setParser(new QQDuShuFenLeiParser());
//                spiderJob.runAsync();
//            }
//            if("QQDuShu.failed".equals(job)){
//                ISpiderJob spiderJob = context.getBean("failedUrlJob",ISpiderJob.class);
//                spiderJob.setCsvFilePath("QQDuShu.csv");
//                spiderJob.setParser(new QQDuShuFenLeiParser());
//                spiderJob.setParameters(new String [] {"QQDuShu.csv.failed"});
//                spiderJob.runAsync();
//            }
//
//            if("ChuangShi".equals(job)){
//
//                ISpiderJob spiderJob = context.getBean("pageJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[] {"http://chuangshi.qq.com/bk/p/%d.html","1367"});
//                spiderJob.setCsvFilePath("ChuangShi.csv");
//                spiderJob.setParser(new ChuangShiFenLeiParser());
//                spiderJob.runAsync();
//
//
//
//            }
//
//            if("ChuangShi.failed".equals(job)){
//                ISpiderJob spiderJob = context.getBean("failedUrlJob",ISpiderJob.class);
//                spiderJob.setCsvFilePath("ChuangShi.csv");
//                spiderJob.setParser(new ChuangShiFenLeiParser());
//                spiderJob.setParameters(new String [] {"ChuangShi.csv.failed"});
//                spiderJob.runAsync();
//            }
//            if("YunQi".equals(job)){
//
//                ISpiderJob spiderJob = context.getBean("pageJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[] {"http://yunqi.qq.com/bk/so2/n30p%d","6001"});
//                spiderJob.setCsvFilePath("YunQi.csv");
//                spiderJob.setParser(new ChuangShiFenLeiParser());
//                spiderJob.runAsync();
//
//
//            }
//
//            if("YunQi.failed".equals(job)){
//                ISpiderJob spiderJob = context.getBean("failedUrlJob",ISpiderJob.class);
//                spiderJob.setCsvFilePath("YunQi.csv");
//                spiderJob.setParser(new YunQiFenLeiParser());
//                spiderJob.setParameters(new String [] {"YunQi.csv.failed"});
//                spiderJob.runAsync();
//            }
//
//            if("amz.bestsellers".equals(job)){
//                ISpiderJob spiderJob = context.getBean("urlListJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[]
//                        {
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_1?ie=UTF8&pg=1&ajax=1",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_1?ie=UTF8&pg=1&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_2?ie=UTF8&pg=2&ajax=1",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_2?ie=UTF8&pg=2&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_3?ie=UTF8&pg=3&ajax=1",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_3?ie=UTF8&pg=3&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_4?ie=UTF8&pg=4&ajax=1",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_4?ie=UTF8&pg=4&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_5?ie=UTF8&pg=5&ajax=1",
//                                "https://www.amazon.cn/gp/bestsellers/digital-text/116169071/ref=zg_bs_116169071_pg_5?ie=UTF8&pg=5&ajax=1&isAboveTheFold=0",
//                        });
//                spiderJob.setCsvFilePath("amz.bestsellers.csv");
//                spiderJob.setParser(new AmzBestSellersParser());
//                spiderJob.runAsync();
//                while(true){
//                    int progress = spiderJob.getProgress();
//                    if(progress==100){
//                        break;
//                    }else{
//                        logger.info("Progress:{}%",progress);
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        logger.warn("Exception:",e);
//                    }
//                }
//
//            }
//            if("amz.newreleases".equals(job)){
//                ISpiderJob spiderJob = context.getBean("urlListJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[]
//                        {
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_1?ie=UTF8&pg=1&ajax=1",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_1?ie=UTF8&pg=1&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_2?ie=UTF8&pg=2&ajax=1",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_2?ie=UTF8&pg=2&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_3?ie=UTF8&pg=3&ajax=1",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_3?ie=UTF8&pg=3&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_4?ie=UTF8&pg=4&ajax=1",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_4?ie=UTF8&pg=4&ajax=1&isAboveTheFold=0",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_5?ie=UTF8&pg=5&ajax=1",
//                                "https://www.amazon.cn/gp/new-releases/digital-text/116169071/ref=zg_bsnr_116169071_pg_5?ie=UTF8&pg=5&ajax=1&isAboveTheFold=0",
//                        });
//                spiderJob.setCsvFilePath("amz.newreleases.csv");
//                spiderJob.setParser(new AmzNewReleasesParser());
//                spiderJob.runAsync();
//                while(true){
//                    int progress = spiderJob.getProgress();
//                    if(progress==100){
//                        break;
//                    }else{
//                        logger.info("Progress:{}%",progress);
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        logger.warn("Exception:",e);
//                    }
//                }
//
//            }
//            if("amz.today".equals(job)){
//                ISpiderJob spiderJob = context.getBean("urlListJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[]
//                        {
//                                "https://www.amazon.cn/b/ref=s9_acss_bw_cg_NEWKXD_2a1_w?ie=UTF8&node=1875254071"
//                        });
//                spiderJob.setCsvFilePath("amz.today.csv");
//                spiderJob.setParser(new AmzTodayParser());
//                spiderJob.runAsync();
//                while(true){
//                    int progress = spiderJob.getProgress();
//                    if(progress==100){
//                        break;
//                    }else{
//                        logger.info("Progress:{}%",progress);
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        logger.warn("Exception:",e);
//                    }
//                }
//
//            }
//            if("amz.weekly".equals(job)){
//                ISpiderJob spiderJob = context.getBean("urlListJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[]
//                        {
//                                "https://www.amazon.cn/s/ref=sr_pg_2?rh=n%3A1852543071&sort=price-asc-rank&ie=UTF8"
//                        });
//                spiderJob.setCsvFilePath("amz.weekly.csv");
//                spiderJob.setParser(new AmzWeeklyMonthlyParser());
//                spiderJob.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
//                spiderJob.setTimeout(5);
//                spiderJob.runAsync();
//                while(true){
//                    int progress = spiderJob.getProgress();
//                    if(progress==100){
//                        break;
//                    }else{
//                        logger.info("Progress:{}%",progress);
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        logger.warn("Exception:",e);
//                    }
//                }
//            }
//            if("amz.monthly".equals(job)){
//                ISpiderJob spiderJob = context.getBean("urlListJob",ISpiderJob.class);
//                spiderJob.setParameters(new String[]
//                        {
//                                "https://www.amazon.cn/s?rh=n%3A1852542071&sort=price-asc-rank"
//                        });
//                spiderJob.setCsvFilePath("amz.monthly.csv");
//                spiderJob.setParser(new AmzWeeklyMonthlyParser());
//                spiderJob.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
//                spiderJob.setTimeout(5);
//                spiderJob.runAsync();
//                while(true){
//                    int progress = spiderJob.getProgress();
//                    if(progress==100){
//                        break;
//                    }else{
//                        logger.info("Progress:{}%",progress);
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        logger.warn("Exception:",e);
//                    }
//                }
//
//
//            }
//        }


//        context.close();

    }






}
