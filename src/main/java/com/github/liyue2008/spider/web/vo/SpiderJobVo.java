package com.github.liyue2008.spider.web.vo;

import java.util.List;

/**
 * Created by liyue on 2017/4/7.
 */
public class SpiderJobVo {

    public final static int STATUS_RUNNING = 1;
    public final static int STATUS_SUCCESS = 2;
    public final static int STATUS_IDLE = 0;
    public final static int STATUS_FAILED = -1;

    private String jobName;
    private String label;
    private String previewUrl;

    private int progress;
    private int status;
    private List<String> fileList;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
