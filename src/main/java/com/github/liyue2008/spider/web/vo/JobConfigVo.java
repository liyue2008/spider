package com.github.liyue2008.spider.web.vo;

import com.github.liyue2008.spider.core.entity.JobConfig;

/**
 * Created by liyue on 2017/5/12.
 */
public class JobConfigVo extends JobConfig {

    public JobConfigVo(JobConfig c){
        setId(c.getId());
        setJobLabel(c.getJobLabel());
        setPreviewUrl(c.getPreviewUrl());
        setJobParams(c.getJobParams());
        setJobType(c.getJobType());
        setParserId(c.getParserId());
        setParserClass(c.getParserClass());
        setSortResultIndex(c.getSortResultIndex());
        setRemark(c.getRemark());
        setDelFlag(c.getDelFlag());
        setCsvFilePath(c.getCsvFilePath());
    }

    private String parserConfigName;

    public String getParserConfigName() {
        return parserConfigName;
    }

    public void setParserConfigName(String parserConfigName) {
        this.parserConfigName = parserConfigName;
    }
}
