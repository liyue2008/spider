package com.github.liyue2008.spider.core.fetch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyue on 2017/5/15.
 */
@Aspect
@Component
public class CaptchaService {

    private Map<String,CaptchaTask> captchaMap = new HashMap<>();


//    @Around("execution(public * HttpConnectionService.getResponse(..))")
//    @Order(30)
    public Object processCaptchaPage(ProceedingJoinPoint pjp) throws Throwable {

        Object [] args = pjp.getArgs();
        HttpRequest request = null;
        if(args.length == 1 && args[0] instanceof HttpRequest) {
            request = (HttpRequest) args[0];
        }
        Object ret = pjp.proceed();
        if(ret  instanceof Connection.Response){
            Connection.Response response = (Connection.Response )ret;
            Document document = response.parse();
            if(document.title().equals("Amazon CAPTCHA")){
                request.setEnableProxy(true);
                return pjp.proceed(new Object[] {request});
//                Elements urlElements = document.select("img");
//                if(null!=urlElements && !urlElements.isEmpty()){
//                    String imgUrl = urlElements.get(0).attr("src");
//                    if(null!=imgUrl){
//                        CaptchaTask task = new CaptchaTask();
//                        task.setResponse(response);
//
//                        String actionUrl = document.select("form").first().absUrl("action");
//                        actionUrl +="?";
//                        Elements inputElements = document.select("input[type=hidden]");
//                        List<String> params = new ArrayList<>();
//                        for(Element e:inputElements){
//                            params.add(e.attr("name") + "=" + e.attr("value"));
//                        }
//
//                        actionUrl += String.join("&",params);
//
//                        task.setActionUrl(actionUrl);
//
//                        captchaMap.put(imgUrl,task);
//                        while (task.getCaptcha() == null){
//                            Thread.sleep(1000);
//                        }
//
//                        actionUrl+="&field-keywords=" + task.getCaptcha();
//
//                        if(request!= null){
//                            request.setUrl(actionUrl);
//                            return pjp.proceed();
//
//                        }
//
//
//                    }
//                }
            }

            return ret;
        }else{
            return ret;
        }
    }



//  private   processAmazonCaptcha()
}
