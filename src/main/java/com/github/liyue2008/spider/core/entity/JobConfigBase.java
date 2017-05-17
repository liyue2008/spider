package com.github.liyue2008.spider.core.entity;

public class JobConfigBase {
    private Integer id;

    private String jobLabel;

    private String previewUrl;

    private String jobType;

    private Integer parserId;

    private String parserClass;

    private Integer sortResultIndex;

    private String remark;

    private Integer delFlag;

    private String jobParams;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobLabel() {
        return jobLabel;
    }

    public void setJobLabel(String jobLabel) {
        this.jobLabel = jobLabel == null ? null : jobLabel.trim();
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl == null ? null : previewUrl.trim();
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public Integer getParserId() {
        return parserId;
    }

    public void setParserId(Integer parserId) {
        this.parserId = parserId;
    }

    public String getParserClass() {
        return parserClass;
    }

    public void setParserClass(String parserClass) {
        this.parserClass = parserClass == null ? null : parserClass.trim();
    }

    public Integer getSortResultIndex() {
        return sortResultIndex;
    }

    public void setSortResultIndex(Integer sortResultIndex) {
        this.sortResultIndex = sortResultIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getJobParams() {
        return jobParams;
    }

    public void setJobParams(String jobParams) {
        this.jobParams = jobParams == null ? null : jobParams.trim();
    }
}