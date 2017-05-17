package com.github.liyue2008.spider.core.entity;

/**
 * Created by liyue on 2017/5/12.
 */
public class Proxy {
    private  boolean isGood;

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    private String ip;
    private  int port;
    private int type;
    public final static int TYPE_HTTP =0;
    public final static int TYPE_HTTPS =1;
    public String toString(){
        return (type==TYPE_HTTP?"http://":"https://") + ip + ":" + String.valueOf(port);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Proxy(String ip,int port,int type){
        this.ip = ip;
        this.port = port;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other == this) return true;
        if(! (other instanceof Proxy)) return  false;
        Proxy otherProxy = (Proxy) other;
        return ( ip != null && ip.equals(otherProxy.getIp()) && port == otherProxy.getPort() && type == otherProxy.getType());
    }
}
