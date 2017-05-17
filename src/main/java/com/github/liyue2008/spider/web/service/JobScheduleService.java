package com.github.liyue2008.spider.web.service;

import com.github.liyue2008.spider.core.entity.JobConfig;
import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.job.ISpiderJob;
import com.github.liyue2008.spider.core.job.SpiderJobService;
import com.github.liyue2008.spider.core.parser.ConfigurableParser;
import com.github.liyue2008.spider.core.parser.ConfigurableParserFactory;
import com.github.liyue2008.spider.core.parser.IParser;
import com.github.liyue2008.spider.core.service.ConfigService;
import com.github.liyue2008.spider.core.service.CsvFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.github.liyue2008.spider.web.vo.SpiderJobVo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by liyue on 2017/4/7.
 */
@Service
public class JobScheduleService {
    @Value("${spider.result.path:.}")
    private String resultPath;
    @Value("${spider.result.max:20}")
    private int resultMaxCount;


    private final static Logger logger = LoggerFactory.getLogger(JobScheduleService.class);
    @Resource
    private SpiderJobService spiderJobService;
    @Resource
    private ConfigService jobConfigService;
    @Resource
    private ConfigurableParserFactory configurableParserFactory;
    @Resource
    private CsvFileService csvFileService;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    private Map<String,ISpiderJob> spiderJobMap = new HashMap<>();

    private List<SpiderJobVo> spiderJobVos = new ArrayList<>();
    private List<JobConfig> jobConfigList;
    public List<SpiderJobVo> getSpiderJobs(){



        return spiderJobVos;
    }


    private boolean isJobFailed(String csvFilePath){
        String failedPath = csvFilePath + ".failed";
        File failedUrlFile = new File(failedPath);
        return failedUrlFile.exists();
    }

    private void sortResult(String csvFilePath,Integer sortCol){
        if(sortCol!=null && sortCol >=0){
            try {
                csvFileService.sortCsv(csvFilePath,sortCol);
            } catch (IOException e) {
                logger.warn("Exception:",e);
            }
        }
    }

    private void moveFinishedJobFile(String csvFilePath,String jobName,String jobLabel){
        File resultFile = new File(csvFilePath);
        String fileName = jobLabel + "_" + sdf.format( new Date()) + ".csv";
        if(resultFile.exists()){
            resultFile.renameTo(new File(resultPath + "/" + jobName + "/" + fileName));
        }

        File failedUrlFile = new File(csvFilePath + ".failed");
        if(failedUrlFile.exists()){
            failedUrlFile.renameTo(new File(resultPath + "/" + jobName + "/" + fileName + ".failed.txt"));
        }
    }

    @Scheduled(fixedRate = 1000)
    private void updateJobStatus(){
        Iterator<Map.Entry<String,ISpiderJob>> iter = spiderJobMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String,ISpiderJob> entry = iter.next();
            String jobName = entry.getKey();

            ISpiderJob spiderJob = entry.getValue();
            if(null!=spiderJob){

                for(SpiderJobVo vo:spiderJobVos){
                    if(jobName.equals(vo.getJobName())){
                        vo.setProgress(spiderJob.getProgress());
                        if(spiderJob.getProgress() == 100){
                            sortResult(spiderJob.getCsvFilePath(),spiderJob.getSortResultIndex());
                            vo.setStatus(isJobFailed(spiderJob.getCsvFilePath())?SpiderJobVo.STATUS_FAILED:SpiderJobVo.STATUS_SUCCESS);
                            moveFinishedJobFile(spiderJob.getCsvFilePath(),jobName,vo.getLabel());
                            vo.setFileList(getDownloadFiles(resultPath + "/" + jobName));
                            iter.remove();
                        }else{
                            vo.setStatus(SpiderJobVo.STATUS_RUNNING);
                        }

                        break;
                    }
                }


            }
        }



    }

    @Scheduled(fixedRate = 60*1000)
    private void updateFileLists(){
        for(SpiderJobVo vo:spiderJobVos){
            vo.setFileList(getDownloadFiles(resultPath + "/" + vo.getJobName()));
        }
    }

    public String startJob(String jobName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        updateJobStatus();
        ISpiderJob spiderJob = spiderJobMap.get(jobName);
        if(null!=spiderJob){
            if(spiderJob.getProgress()<100){
                return "该项目正在抓取中！";
            }
        }

        for(JobConfig conf:jobConfigList){
            if(jobName.equals(conf.getJobName())){

                conf.setCsvFilePath(resultPath + "/active/" + jobName + "_" + new Date().getTime() + ".csv");

                initParser(conf);
                spiderJob = spiderJobService.runJobAsync(conf);
                spiderJobMap.put(jobName,spiderJob);
                updateJobStatus();
                return null;
            }
        }
        return "没有找到对应的配置信息！";

    }
    public void initParser(JobConfig jobConfig){
        if(null==jobConfig) return;
            IParser parser = null;
            if(null!=jobConfig.getParserId()){
                ParserConfig parserConfig = jobConfigService.getParserConfigFullById(jobConfig.getParserId());
                if(null!=parserConfig){
                    ConfigurableParser configurableParser = configurableParserFactory.getConfigurableParser();
                    configurableParser.setConfig(parserConfig);
                    parser = configurableParser;
                }
            }else if(null!=jobConfig.getParserClass()){
                try {
                    parser = (IParser) Class.forName(jobConfig.getParserClass()).newInstance();
                } catch (Exception e) {
                    logger.warn("Exception:",e);
                }
            }

            jobConfig.setParser(parser);
    }

    public String getFilePath(String jobName,String fileName){
        return resultPath + "/" + jobName + "/" + fileName;
    }


    @PostConstruct
    public void init(){
        createDirIfNotExists(resultPath + "/active");
        reloadJobConfig();
    }


    public void reloadJobConfig() {
        jobConfigList = jobConfigService.getAllJobConfigList();
        List<SpiderJobVo> newSpiderJobVos = new ArrayList<>(jobConfigList.size());
        for(JobConfig c:jobConfigList){
            SpiderJobVo v = null;
            String path = resultPath + "/" + c.getJobName();
            createDirIfNotExists(path);

            for(SpiderJobVo oldVo:spiderJobVos){
                if(oldVo.getJobName().equals(c.getJobName())){
                    v=oldVo;
                    v.setLabel(c.getJobLabel());
                    v.setPreviewUrl(c.getPreviewUrl());
                    break;
                }
            }
            if(v==null) {
                v = new SpiderJobVo();
                v.setFileList(getDownloadFiles(path));
                v.setJobName(c.getJobName());
                v.setLabel(c.getJobLabel());
                v.setPreviewUrl(c.getPreviewUrl());
//            v.setPreviewUrl(c.getPreviewUrl());
                v.setProgress(0);
                v.setStatus(SpiderJobVo.STATUS_IDLE);
            }
            newSpiderJobVos.add(v);

        }
        spiderJobVos = newSpiderJobVos;
        updateJobStatus();
    }

    private List<String> getDownloadFiles(String path){
        File pathFile = new File(path);
        List<String> retList = new ArrayList<>();
        if(pathFile.isDirectory()){
            File [] files = pathFile.listFiles();
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if(o1.lastModified()==o2.lastModified()) return 0;
                    return o1.lastModified()>o2.lastModified()?-1:1;
                }
            });
            for (File d:files){
                retList.add(d.getName());
                if(retList.size()>=resultMaxCount) break;
            }

        }

        return retList;
    }




    private void createDirIfNotExists (String path) throws SecurityException{
        File theDir = new File(path);
        if (!theDir.exists()) {
                theDir.mkdirs();
        }
    }
}
