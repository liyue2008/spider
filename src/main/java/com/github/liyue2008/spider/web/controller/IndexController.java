package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.web.service.JobScheduleService;
import com.github.liyue2008.spider.web.utils.RestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by liyue on 2017/4/7.
 */
@Controller
public class IndexController {

    @Value("${spring.application.name:}")
    private String applicationName;
    @Resource
    private JobScheduleService jobScheduleService;

    @RequestMapping(value={"/","/index.html"})
    public String hello(Model model){
        model.addAttribute("app",applicationName);
        model.addAttribute("jobs", jobScheduleService.getSpiderJobs());
        model.addAttribute("classActiveIndex","active");
        model.addAttribute("title","首页");
        return "index";
    }
    @RequestMapping("/jobs")
    @ResponseBody
    public Object getJobs(){
        Map<String,Object> retMap = RestUtils.successResponse();
        retMap.put("jobs",jobScheduleService.getSpiderJobs());
        return retMap;
    }
}
