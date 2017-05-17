package com.github.liyue2008.spider.core.entity;

import com.github.liyue2008.spider.core.parser.IParser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JobConfig extends JobConfigBase{
    @JsonIgnore
    private IParser parser ;
    public String getJobName(){
        return "job_" + getId();
    }


    @JsonIgnore
    private String csvFilePath;

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public String [] getJobParamArray(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getJobParams(),new TypeReference<String []>(){});
        } catch (IOException e) {
            return null;
        }
    }

    public void setJobParamArray(String [] jobParamArray){
        ObjectMapper mapper = new ObjectMapper();
        try {
            setJobParams(mapper.writeValueAsString(jobParamArray));
        } catch (JsonProcessingException e) {
            //nothing to do...
        }
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }
}