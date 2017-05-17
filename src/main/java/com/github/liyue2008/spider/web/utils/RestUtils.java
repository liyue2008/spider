package com.github.liyue2008.spider.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyue on 2015/8/25.
 */
public class RestUtils {
    public static Map<String,Object> successResponse(){
        Map<String,Object> retMap = new HashMap<String, Object>();
        retMap.put("success",true);
        return retMap;
    }

    public static Map<String,Object> errorResponse(String error){
        Map<String,Object> retMap = new HashMap<String, Object>();
        retMap.put("success",false);
        if(null!=error)
            retMap.put("message",error);
        return retMap;
    }
}
