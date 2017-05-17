package com.github.liyue2008.spider.core.fetch;

import com.github.liyue2008.spider.core.entity.FetchTask;

import java.util.List;

/**
 * Created by liyue on 2017/4/6.
 */
public class FetchResult {
    private FetchTask fetchTask;
    public static final int STATUS_RETRY = 2;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = -1;
    private List<FetchTask> moreTasks;
    private String message;
    private int status;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FetchTask getFetchTask() {
        return fetchTask;
    }

    public void setFetchTask(FetchTask fetchTask) {
        this.fetchTask = fetchTask;
    }

    public FetchResult(){}

    public FetchResult(int status,FetchTask fetchTask){
        this.status = status;
        this.fetchTask = fetchTask;
    }

    public FetchResult(int status,FetchTask fetchTask,List<FetchTask> moreTasks){
        this(status,fetchTask);
        this.moreTasks = moreTasks;
    }

    public FetchResult(int status,FetchTask fetchTask,String message){
        this(status,fetchTask);
        this.message = message;
    }

    public List<FetchTask> getMoreTasks() {
        return moreTasks;
    }

    public void setMoreTasks(List<FetchTask> moreTasks) {
        this.moreTasks = moreTasks;
    }
}
