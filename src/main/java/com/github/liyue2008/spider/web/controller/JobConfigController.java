package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.core.entity.JobConfig;
import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.job.ISpiderJob;
import com.github.liyue2008.spider.core.service.ConfigService;
import com.github.liyue2008.spider.web.utils.RestUtils;
import com.github.liyue2008.spider.web.vo.JobConfigVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue on 2017/4/14.
 */
@Controller
public class JobConfigController {
    private final static Logger logger = LoggerFactory.getLogger(JobConfigController.class);

    @Resource
    private ConfigService configService;
    @Autowired
    private ApplicationContext applicationContext;
    @RequestMapping("/job-config-list.html")
    public String jobConfigList(Model model){

        List<JobConfig> jobConfigs = configService.getAllJobConfigList();


        List<ParserConfig> parserConfigList = configService.getAllParserConfigList();


        List<JobConfigVo> jobConfigVos = new ArrayList<>(jobConfigs.size());
        for(JobConfig c:jobConfigs){
            JobConfigVo vo = new JobConfigVo(c);
            if(null!=vo.getParserId()) {
                vo.setParserConfigName(String.valueOf(vo.getParserId()));

                for (ParserConfig parserConfig : parserConfigList) {
                    if (vo.getParserId().equals(parserConfig.getId())){
                        vo.setParserConfigName(parserConfig.getName());
                        break;
                    }
                }
            }
            jobConfigVos.add(vo);
        }
        model.addAttribute("jobConfigs",jobConfigVos);

        Map<String,ISpiderJob> spiderJobMap = applicationContext.getBeansOfType(ISpiderJob.class);
        model.addAttribute("jobTypes",spiderJobMap.keySet());
        model.addAttribute("parserConfigs",parserConfigList);
        model.addAttribute("classActiveJobConfigList","active");
        model.addAttribute("title","任务配置");

        return "job-config-list";
    }

    @RequestMapping(value = "/job-config/delete/{id:\\d+}",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteJobConfig(@PathVariable  Integer id){
        int count = configService.deleteJobConfig(id);


        if(count == 1) {
            return RestUtils.successResponse();
        }else{
            return RestUtils.errorResponse("删除失败！");
        }
    }


    @RequestMapping(value = "/job-config/create",method = RequestMethod.POST)
    @ResponseBody
    public Object createJobConfig(@RequestBody JobConfig jobConfig){


        if(1==configService.insertJobConfig(jobConfig)) {
            Map<String,Object> resp =  RestUtils.successResponse();
            resp.put("id",jobConfig.getId());
            return resp;
        }else{
            return RestUtils.errorResponse("保存失败！");
        }

    }
    @RequestMapping(value = "/job-config/update",method = RequestMethod.POST)
    @ResponseBody
    public Object updateJobConfig(@RequestBody JobConfig jobConfig){
        if(1==configService.updateJobConfig(jobConfig)) {
            return RestUtils.successResponse();
        }else{
            return RestUtils.errorResponse("保存失败！");
        }
    }
}
