package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.core.mapper.JobConfigMapper;
import com.github.liyue2008.spider.web.service.JobScheduleService;
import com.github.liyue2008.spider.web.utils.RestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liyue on 2017/4/10.
 */
@RestController
@RequestMapping("/job")
public class JobRestController {

    @Resource
    private JobScheduleService jobScheduleService;
    @Resource
    private JobConfigMapper jobConfigMapper;

    @RequestMapping("/{jobName}/start")
    public Object startJob(@PathVariable String jobName) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        String errMessage = jobScheduleService.startJob(jobName);
        if(null==errMessage){
            return RestUtils.successResponse();
        }else{
            return RestUtils.errorResponse(errMessage);
        }
    }

    @RequestMapping(value = "/{jobName}/file/{fileName}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("fileName") String fileName,
            @PathVariable("jobName") String jobName,
            HttpServletResponse response) throws IOException {
            String filePath = jobScheduleService.getFilePath(jobName,fileName);
            if(filePath.endsWith(".csv"))
                response.setContentType("text/csv");
            else
                response.setContentType("text/plain");
            // get your file as InputStream
            InputStream is = new FileInputStream(filePath);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();


    }

    @RequestMapping("/test")
    public @ResponseBody  Object test(){
        return jobConfigMapper.selectByExampleWithBLOBs(null);
    }


}
