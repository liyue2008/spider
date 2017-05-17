package com.github.liyue2008.spider.core.fetch;

import java.util.Map;

/**
 * Created by liyue on 2017/5/15.
 */
public class HttpRequest {
    private String url;
    private Integer timeout;
    private String referrer;
    private String userAgent;
    private Map<String,String> cookies;
    private boolean enableProxy;
    private int retryCount;

    public HttpRequest(String url,Integer timeout,String referrer,String userAgent){
        this.url = url;
        this.timeout = timeout;
        this.referrer = referrer;
        this.userAgent = userAgent;
        this.enableProxy = false;
        this.retryCount = 0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public boolean isEnableProxy() {
        return enableProxy;
    }

    public void setEnableProxy(boolean enableProxy) {
        this.enableProxy = enableProxy;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
