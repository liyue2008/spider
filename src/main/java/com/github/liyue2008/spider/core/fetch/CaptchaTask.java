package com.github.liyue2008.spider.core.fetch;

import org.jsoup.Connection;

/**
 * Created by liyue on 2017/5/15.
 */
public class CaptchaTask {
    private HttpRequest request;
    private Connection.Response response;

    private String actionUrl;
    private String captcha;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public Connection.Response getResponse() {
        return response;
    }

    public void setResponse(Connection.Response response) {
        this.response = response;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
