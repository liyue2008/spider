package com.github.liyue2008.spider.core.fetch;

import com.github.liyue2008.spider.core.entity.FetchTask;
import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.parser.ConfigurableParser;
import com.github.liyue2008.spider.core.parser.ConfigurableParserFactory;
import com.github.liyue2008.spider.core.parser.IParser;
import com.github.liyue2008.spider.core.service.CsvFileService;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.Future;

/**
 * Created by liyue on 2017/3/31.
 */
@Component
public class FetchWorker {
    private final Logger logger = LoggerFactory.getLogger(FetchWorker.class);




    @Resource
    private ConfigurableParserFactory configurableParserFactory;

    @Resource
    private CsvFileService csvFileService;

    @Resource
    private HttpConnectionService httpConnectionService;
    @Resource
    private CacheService cacheService;

    @Async("jobExecutor")
    public Future<FetchResult> fetch(FetchTask fetchTask){



        if(null== fetchTask){
            logger.warn("FetchTask is NULL!");
            return new AsyncResult<>(new FetchResult(FetchResult.STATUS_FAILED,fetchTask,"FetchTask is NULL!"));
        }

        try {
//            logger.info("Spider url:{}...",fetchTask.getUrl());
            String [] columnTitles = fetchTask.getParser().getColumnTitles();
//            String [] newColumnTitles = Arrays.copyOf(columnTitles,columnTitles.length + 2);
//            newColumnTitles[newColumnTitles.length-2] = "page";
//            newColumnTitles[newColumnTitles.length-1] = "index";


            IParser parser = fetchTask.getParser();

            Document doc = getDocument(fetchTask.getUrl(),parser.getReferrer(),parser.getUserAgent(),parser.getTimeout(),fetchTask.isRetryTask());
            List<String[]> resultList = spiderPage( fetchTask.getParser(),doc,fetchTask.getUrl());

            if(isResultListEmpty(resultList)){
                logger.info("Got Nothing from url:{}...",fetchTask.getUrl());
                cacheService.removeCache(fetchTask.getUrl());
                return new AsyncResult<>(retryTask(fetchTask));
            }else{
                logger.info("Got [{}] media from url:{},saveParserConfig to {}...",resultList.size(),fetchTask.getUrl(),fetchTask.getCsvFilePath());

                csvFileService.appendToCsv(resultList,fetchTask.getCsvFilePath(), columnTitles);
                logger.info("[{}] media from url:{},saved to {}",resultList.size(),fetchTask.getUrl(),fetchTask.getCsvFilePath());

                List<FetchTask> moreTasks = spiderMore(doc,fetchTask);
                logger.info("finished url:{}...",fetchTask.getUrl());
                return new AsyncResult<>(new FetchResult(FetchResult.STATUS_SUCCESS,fetchTask,moreTasks));
            }
        }catch (Exception e){
            logger.warn("Exception:",e);
            return new AsyncResult<>(retryTask(fetchTask));
        }

    }

    public List<String []> fetch(String url, ParserConfig parserConfig) throws IOException, URISyntaxException {
        if(null== parserConfig){
            logger.warn("ParserConfig is NULL!");
            return null;
        }

        int retry = 10;
        while (retry-->0) {
//            logger.info("Spider url:{}...", url);


            ConfigurableParser parser = configurableParserFactory.getConfigurableParser();
            parser.setConfig(parserConfig);

            Document doc = getDocument(url, parser.getReferrer(), parser.getUserAgent(), parser.getTimeout(),false, 10);
            List<String[]> resultList = spiderPage(parser, doc, url);

            if (isResultListEmpty(resultList)) {
                logger.info("Got Nothing from url:{}...", url);
                cacheService.removeCache(url);
            } else {
                return resultList;
            }
        }
        return null;
    }

    public String [] fetchFirstRow(String url, ParserConfig parserConfig) throws IOException, URISyntaxException {

        List<String[]> resultList = fetch(url,parserConfig);

        if (isResultListEmpty(resultList)) {
            return null;
        } else {
            return resultList.get(0);
        }

    }

    private boolean isResultListEmpty(List<String[]> resultList){
        if (resultList == null || resultList.size() == 0){
            return true;
        }else{
            for(String [] line:resultList){
                for(String c:line){
                    if(c!=null && !c.isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private FetchResult retryTask(FetchTask fetchTask)  {
        fetchTask.addRetry();
        if(fetchTask.hasMoreRetry()){
            return new FetchResult(FetchResult.STATUS_RETRY,fetchTask);
        }
        else{
            logger.warn("Fetch Task FAILED! url: [{}]",fetchTask.getUrl());
            return new FetchResult(FetchResult.STATUS_FAILED,fetchTask);
//            appendFailedUrl(fetchTask.getUrl(),fetchTask.getCsvFilePath() + ".failed");
        }
    }

    public Document getDocument(String url,String referrer,String userAgent,int timeout,boolean enableProxy) throws IOException, URISyntaxException {
        return getDocument(url,referrer,userAgent,timeout,enableProxy,0);
    }

    public Document getDocument(String url,String referrer,String userAgent,int timeout,boolean enableProxy,int retry) throws IOException, URISyntaxException {
        HttpRequest request = new HttpRequest(url,timeout,referrer,userAgent);
        request.setEnableProxy(enableProxy);
        request.setRetryCount(retry);
        Connection.Response response = httpConnectionService.getResponse(request);
        if(null!=response){
            return response.parse();
        }else{
            return null;
        }
    }

    public List<FetchTask> spiderMore(Document doc,FetchTask refTask){
        List<String> urls = refTask.getParser().getMoreUrls(doc);
        if(null!=urls){
            List<FetchTask> taskList = new ArrayList<>();
            for(String url:urls) {
                FetchTask task = new FetchTask(url, refTask.getPage() + 1, refTask.getCsvFilePath(), refTask.getParser());

                taskList.add(task);
            }
            return taskList;
        }else{
            return null;
        }
    }

    public List<String[]> spiderPage(IParser parser, Document doc,String url) {


        Elements bookDetailDivElements = parser.getList(doc);
        List<String[]> resultList = new ArrayList<>();

//        Media media ;
        String [] result ;
        int index = 1;
        for(Element element:bookDetailDivElements){
            result = null;
            try {
                result = parser.parserElement(element);
            }catch (Exception e){
                index ++;
                logger.warn("Exception:",e);
                logger.warn("Element parse failed: {}",element.html());
            }
            if(null!=result){
//                media.setPage(page);
//                media.setIndex(index++);
//                String [] toAdd = Arrays.copyOf(result,result.length + 2);
//                String page = parser.getPageNum(url,doc);
//                if(null==page) page="";
//                toAdd[toAdd.length-2] = page;
//                toAdd[toAdd.length-1] = String.valueOf(index++);
                resultList.add(result);
                logger.info("result:{}", (Object) result);
            }
        }
        return resultList;
    }


}
