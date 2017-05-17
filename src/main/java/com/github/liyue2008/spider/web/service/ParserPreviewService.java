package com.github.liyue2008.spider.web.service;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.entity.ParserConfigItem;
import com.github.liyue2008.spider.core.fetch.FetchWorker;
import com.github.liyue2008.spider.core.parser.ConfigurableParser;
import com.github.liyue2008.spider.core.parser.ConfigurableParserFactory;
import com.github.liyue2008.spider.core.service.ConfigService;
import com.github.liyue2008.spider.web.vo.ParserPreviewVo;
//import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by liyue on 2017/4/17.
 */
@Service
public class ParserPreviewService {
    private final static Logger logger = LoggerFactory.getLogger(ParserPreviewService.class);
//    @Value("${spider.preview.user-agent:@null}")
//    private String defaultUserAgent;
//
//    @Value("${spider.preview.timeout:5}")
//    private int defaultTimeOut;

    @Value(("${spider.preview.expire-sec:600}"))
    private int expireSec;
    @Resource
    private FetchWorker fetchWorker;
    @Resource
    private ConfigurableParserFactory configurableParserFactory;
    @Resource
    private ConfigService configService;
    private Map<Integer,ParserPreviewVo> previewVoMap = new HashMap<>();
    private int nextSerial = 0;


    private int getNextSerial(){
        if(nextSerial==Integer.MAX_VALUE) return 0;
        else return nextSerial++;
    }


    public ParserPreviewVo testUrl(String url,int timeout,String referrer,String userAgent,int serial){
        try {
            Document document = fetchWorker.getDocument(url,referrer,userAgent,timeout,false);
            if(null!=document){
                if(serial<0) {
                    serial = getNextSerial();
                }
                ParserPreviewVo vo = new ParserPreviewVo();
                vo.setSerial(serial);
                vo.setDocument(document);
                vo.setUrl(url);
                vo.setTouchDate(new Date());
                previewVoMap.put(serial,vo);
                return vo;
            }else{
                return null;
            }
        } catch (Exception e) {
            logger.warn("Exception:",e);
            return null;
        }
    }


    public ParserPreviewVo testListSelector(int serial,int parserConfigType,String listSelector){
        ParserPreviewVo vo = previewVoMap.get(serial);
        if(null==vo) return  null;
        ParserConfig config = vo.getParserConfig();
        if(null== config){
            config = new ParserConfig();
            config.setType(parserConfigType);
            vo.setParserConfig(config);
        }

        config.setListItemSelector(listSelector);

        ConfigurableParser parser = null;

        if(ParserConfig.PARSER_CONFIG_TYPE_HTML == parserConfigType){
            parser = configurableParserFactory.getConfigurableParser();
            parser.setConfig(config);
            List<Element> elementList = parser.getList(vo.getDocument());
            vo.setParserConfig(config);
            vo.setElementList(elementList);
        }



        vo.setTouchDate(new Date());
        return  vo;

    }


    public ParserPreviewVo testParserConfigItems(int serial, List<ParserConfigItem> items){
        ParserPreviewVo vo = previewVoMap.get(serial);
        if(null==vo) return  null;
        vo.setTouchDate(new Date());

        ParserConfig config = vo.getParserConfig();
        ConfigurableParser parser;

        if(null!= config && ParserConfig.PARSER_CONFIG_TYPE_HTML == config.getType()){
            List<Element> elementList = vo.getElementList();
            config.setItemList(items);

            for(ParserConfigItem item:items){
                if(null!=item.getSubConfigId() && null== item.getSubConfig()){
                    item.setSubConfig(configService.getParserConfigFullById(item.getSubConfigId()));
                }
            }

            vo.setColumnTitles(config.getColumnTitles());
            if(null!=elementList){
                String [][] results = new String[elementList.size()][config.getColumnCount()];
                parser = configurableParserFactory.getConfigurableParser();
                parser.setConfig(config);
                for(Element element:elementList){
                    int index = elementList.indexOf(element);
                    results[index] = parser.parserElement(element);
                }
                vo.setResults(results);
                return vo;
            }

        }

        return null;
    }


    @Scheduled(fixedRate = 10 * 60 * 1000)
    private void deleteExpiredVo(){
        if(null==previewVoMap) return;
        Iterator<Map.Entry<Integer,ParserPreviewVo>> iter = previewVoMap.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<Integer,ParserPreviewVo> entry = iter.next();
            ParserPreviewVo vo =  entry.getValue();
            if(null!=vo){
                if(vo.getTouchDate() != null){
                    if(vo.getTouchDate().getTime() + expireSec * 1000 < new Date().getTime()){
                        logger.info("Remove expired preview vo.");
                        iter.remove();
                    }
                }
            }else{
                iter.remove();
            }
        }
    }

    public List<String> testParserConfigUrlItems(int serial,int parserConfigType, List<ParserConfigItem> items) {
        ParserPreviewVo vo = previewVoMap.get(serial);
        if(null==vo) return  null;
        vo.setTouchDate(new Date());

        ParserConfig config = vo.getParserConfig();
        if(null== config){
            config = new ParserConfig();
            config.setType(parserConfigType);
            vo.setParserConfig(config);
        }

        ConfigurableParser parser ;

        if(ParserConfig.PARSER_CONFIG_TYPE_HTML == config.getType()){
            config.setMoreUrlItemList(items);
            Document document = vo.getDocument();
            if(null!=document){
                parser = configurableParserFactory.getConfigurableParser();
                parser.setConfig(config);
                List<String> urls = parser.getMoreUrls(document);
                return urls;
            }

        }

        return null;
    }



    public String testParserConfigPageItem(int serial,int parserConfigType, ParserConfigItem pageItem) {
        ParserPreviewVo vo = previewVoMap.get(serial);
        if(null==vo) return  null;
        vo.setTouchDate(new Date());

        ParserConfig config = vo.getParserConfig();
        if(null== config){
            config = new ParserConfig();
            config.setType(parserConfigType);
            vo.setParserConfig(config);

        }
        ConfigurableParser parser ;

        if( ParserConfig.PARSER_CONFIG_TYPE_HTML == config.getType()){
            config.setPageNumItem(pageItem);
            Document document = vo.getDocument();
            String url = vo.getUrl();
            if(null!=document && null!=url){
                parser = configurableParserFactory.getConfigurableParser();
                parser.setConfig(config);
                return  parser.getPageNum(url,document);
            }

        }

        return null;
    }



}
