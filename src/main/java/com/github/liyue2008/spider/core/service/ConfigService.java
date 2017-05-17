package com.github.liyue2008.spider.core.service;

import com.dangdang.e.spider.core.entity.*;
import com.github.liyue2008.spider.core.entity.*;
import com.github.liyue2008.spider.core.mapper.JobConfigMapper;
import com.github.liyue2008.spider.core.mapper.ParserConfigItemMapper;
import com.github.liyue2008.spider.core.mapper.ParserConfigMapper;
import com.github.liyue2008.spider.core.parser.ConfigurableParserFactory;
import com.github.liyue2008.spider.web.service.JobScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyue on 2017/4/13.
 */
@Service
public class ConfigService {
    private final static Logger logger = LoggerFactory.getLogger(ConfigService.class);
    @Resource
    private JobConfigMapper jobConfigMapper;
    @Resource
    private ParserConfigMapper parserConfigMapper;
    @Resource
    private ParserConfigItemMapper parserConfigItemMapper;
    @Resource
    private ConfigurableParserFactory configurableParserFactory;
    @Resource
    private JobScheduleService jobScheduleService;

    public List<JobConfig> getAllJobConfigList(){
        JobConfigExample example = new JobConfigExample();
        example.createCriteria().andDelFlagNotEqualTo(1);
        example.setOrderByClause("job_label");
        List<JobConfig> jobConfigList = jobConfigMapper.selectByExampleWithBLOBs(example);
        return jobConfigList;
    }

    public List<JobConfig> getAllProxyJobConfigList(){
        JobConfigExample example = new JobConfigExample();
        example.createCriteria().andDelFlagNotEqualTo(1).andJobLabelLike("[proxy]%");
        example.setOrderByClause("job_label");
        List<JobConfig> jobConfigList = jobConfigMapper.selectByExampleWithBLOBs(example);
        return jobConfigList;
    }

    public JobConfig getJobConfigById(Integer id){
        if(null== id) return null;
        return jobConfigMapper.selectByPrimaryKey(id);
    }




    public ParserConfig getParserConfigFullById(Integer parserId) {
        ParserConfig parserConfig = parserConfigMapper.selectByPrimaryKey(parserId);
        queryConfigItems(parserConfig);
        return parserConfig;
    }


    private void queryConfigItems(ParserConfig parserConfig) {
        if(null== parserConfig) return ;
        ParserConfigItemExample example = new ParserConfigItemExample();
        example.createCriteria().andDelFlagNotEqualTo(1)
                .andParserConfigIdEqualTo(parserConfig.getId());
        example.setOrderByClause("item_index");
        List<ParserConfigItem> parserConfigItems = parserConfigItemMapper.selectByExample(example);

        if(null!=parserConfigItems){
            List<ParserConfigItem> domItemList = new ArrayList<>();
            List<ParserConfigItem> urlItemList = new ArrayList<>();
            for(ParserConfigItem i:parserConfigItems){
                switch (i.getItemType()){
                    case ParserConfigItem.ITEM_TYPE_DOM:
                        domItemList.add(i);
                        if(i.getSubConfigId()!=null){
                            i.setSubConfig(getParserConfigFullById(i.getSubConfigId()));
                        }
                        break;
                    case ParserConfigItem.ITEM_TYPE_URL:
                        urlItemList.add(i);
                        break;
                    case ParserConfigItem.ITEM_TYPE_PAGE_NUM:
                        parserConfig.setPageNumItem(i);
                        break;
                }
            }
            parserConfig.setItemList(domItemList);
            parserConfig.setMoreUrlItemList(urlItemList);
        }
    }


    public List<ParserConfig> getAllPaserConfigListWithItems(){

         List<ParserConfig> retList = getAllParserConfigList();
         if(null!=retList){
             for(ParserConfig config:retList){
                 queryConfigItems(config);
             }
         }
         return retList;
    }
    public List<ParserConfig> getAllParserConfigList(){
         ParserConfigExample example = new ParserConfigExample();
         example.createCriteria().andDelFlagNotEqualTo(1);
         example.setOrderByClause("name");

         return parserConfigMapper.selectByExample(example);
    }

    @Transactional
    public Integer saveParserConfig(ParserConfig parserConfig){
        if(null==parserConfig) return null;

        if(parserConfig.getId() == null){
            //insert
            parserConfigMapper.insertSelective(parserConfig);
            List<ParserConfigItem> itemList = parserConfig.getAllItemList();
            if(null!=itemList){
                for(ParserConfigItem item:itemList){
                    item.setParserConfigId(parserConfig.getId());
                    parserConfigItemMapper.insertSelective(item);
                }
            }

        }else{
            ParserConfigItemExample delExample = new ParserConfigItemExample();
            delExample.createCriteria().andParserConfigIdEqualTo(parserConfig.getId());
            parserConfigItemMapper.deleteByExample(delExample);
            List<ParserConfigItem> itemList = parserConfig.getAllItemList();
            if(null!=itemList){
                for(ParserConfigItem item:itemList){
                    item.setParserConfigId(parserConfig.getId());

                    parserConfigItemMapper.insertSelective(item);
                }
            }
            if(parserConfig.getDelFlag() == null) parserConfig.setDelFlag(0);
            parserConfigMapper.updateByPrimaryKey(parserConfig);

            //update
        }

        return parserConfig.getId();
    }

    public int deleteJobConfig(Integer id){

        int count = jobConfigMapper.deleteByPrimaryKey(id);
        jobScheduleService.reloadJobConfig();
        return count;
    }

    public int insertJobConfig(JobConfig jobConfig){

        int count =  jobConfigMapper.insertSelective(jobConfig);
        jobScheduleService.reloadJobConfig();
        return count;
    }

    public int updateJobConfig(JobConfig jobConfig){
        if(jobConfig.getDelFlag()==null) jobConfig.setDelFlag(0);
        int count =  jobConfigMapper.updateByPrimaryKeyWithBLOBs(jobConfig);

        jobScheduleService.reloadJobConfig();
        return count;
    }


    @Transactional
    public int deleteParserConfig(Integer id){
        deleteParserConfigItemsByParserConfigId(id);
        return parserConfigMapper.deleteByPrimaryKey(id);

    }




    private int deleteParserConfigItemsByParserConfigId(Integer parserConfigId){
        ParserConfigItemExample example = new ParserConfigItemExample();
        example.createCriteria().andParserConfigIdEqualTo(parserConfigId);
        return parserConfigItemMapper.deleteByExample(example);
    }
}
