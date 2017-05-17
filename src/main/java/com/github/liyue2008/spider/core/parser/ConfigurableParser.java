package com.github.liyue2008.spider.core.parser;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.entity.ParserConfigItem;
import com.github.liyue2008.spider.core.fetch.FetchWorker;
import com.github.liyue2008.spider.core.service.ConfigService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liyue on 2017/4/12.
 */
@Component
@Scope("prototype")
public class ConfigurableParser implements IParser {
    private final static Logger logger = LoggerFactory.getLogger(ConfigurableParser.class);
    private ParserConfig config;

    @Resource
    private FetchWorker fetchWorker;
    @Resource
    ConfigService configService;
    private Map<String,Pattern> patternMap;

    public ConfigurableParser(){}
    public void setConfig(ParserConfig config){
        this.config = config;
        buildPatterns();

    }
//    public ConfigurableParser(ParserConfig config){
//    }

    private void buildPatterns() {
        patternMap = new HashMap<>();

        List<ParserConfigItem> allItemList = config.getAllItemList();
        for(ParserConfigItem c:allItemList){
            if(c!=null && c.getPattern() != null && ! (c.getPattern().isEmpty())){
                patternMap.put(c.getPattern(),Pattern.compile(c.getPattern()));
            }
        }
    }



    @Override
    public Elements getList(Document document) {
        String selector = config.getListItemSelector();
        if(null == selector || selector.isEmpty()) return document.children();
        else{
            return document.select(selector);
        }
    }

    @Override
    public String[] parserElement(Element element) {
        try {
            List<ParserConfigItem> configItems = config.getItemList();
            if (null == configItems) return new String[0];

            for (ParserConfigItem item : configItems) {
                if(item.getSubConfigId()!= null){
                    ParserConfig subConfig = item.getSubConfig();
                    if(null==subConfig){
                        subConfig = configService.getParserConfigFullById(item.getSubConfigId());
                        item.setSubConfig(subConfig);
                    }
                }
            }

            String[] retArray = new String[config.getColumnCount()];
            for(int i=0;i<retArray.length;i++){
                retArray[i] = "";
            }
            int index = 0;
            for (ParserConfigItem item : configItems) {
                String value = parseDom(element, item);
                if (item.getSubConfigId() == null) {
                    retArray[index++] = value;
                } else {
                    ParserConfig subConfig = item.getSubConfig();

//                    String subUrl = URLEncoder.encode(value,"UTF-8");
//                    String[] schemes = {"http","https"};
//                    UrlValidator urlValidator = new UrlValidator(schemes);
                    if(!isUrlValid(value)){
                        logger.warn("Not a valid URL: {}",value);
                        index+=subConfig.getColumnCount();
                    }else {
                        String[] subFetchArray = null;
                        try {
                            subFetchArray = fetchWorker.fetchFirstRow(value, subConfig);
                        } catch (Exception e) {
                            logger.warn("Exception:", e);
                        }
                        if (null != subFetchArray) {
                            for (String c : subFetchArray) {
                                retArray[index++] = c;
                            }
                        } else {
                            //跳过空位
                            index += subConfig.getColumnCount();
                        }
                    }
                }


            }

            return retArray;
        }catch (Exception e){
            logger.warn("Exception:",e);
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    private boolean isUrlValid(String url){
        try{
            new URL(url);
            return true;
        }catch (MalformedURLException ignored){
        }
        return  false;
    }


    private String parseDom(Element element, ParserConfigItem item) {
        String value = "";
        try{
            Elements domElements = null;
            String selector = item.getSelector();
            if(null == selector || selector.isEmpty()){
                domElements = new Elements(new Element[]{element});
            }else {
                domElements = element.select(selector);
            }
            if(null!=domElements){
                if (ParserConfigItem.DOM_INDEX_ALL != item.getDomIndex()) {
                    Element domElement;
                    if (ParserConfigItem.DOM_INDEX_FIRST == item.getDomIndex()) {
                        domElement = domElements.first();
                    } else if (ParserConfigItem.DOM_INDEX_LAST == item.getDomIndex()) {
                        domElement = domElements.last();
                    } else {
                        domElement = domElements.get(item.getDomIndex());
                    }
                    if(null!=domElement){
                        String raw = getDomRaw(item, domElement);
                        if(null!=raw) {
                            value = parseRegx(item, raw);
                        }
                    }
                }else{
                    List<String> valueList =new ArrayList<>();
                    for(Element domElement:domElements){
                        String rawOne = getDomRaw(item, domElement);
                        if(null!=rawOne) {
                            String valueOne = parseRegx(item, rawOne);
                            valueList.add(valueOne);
                        }
                    }
                    String seperator = item.getSeparator();
                    if(null==seperator) seperator = "";
                    value = String.join(seperator,valueList);
                }
            }
        }catch (Exception e){
            logger.warn("Exception:",e);
        }
        return value;
    }

    private String parseRegx(ParserConfigItem item, String raw) {
        String value = "";
        String patternString = item.getPattern();
        if(null!=patternString && !patternString.isEmpty()){
            Pattern pattern = patternMap.get(patternString);
            if(null!=pattern){
                Matcher m = pattern.matcher(raw);
                if(m.find() && item.getPatternIndex() <= m.groupCount()){
                    value = m.group(item.getPatternIndex());
                }
            }
        }else{
            value = raw;
        }
        return value;
    }

    private String getDomRaw(ParserConfigItem item, Element domElement) {
        String raw;
        if (ParserConfigItem.ATTR_TEXT.equals(item.getAttribute())){
            raw = domElement.text();
        }else if(ParserConfigItem.ATTR_HREF.equals(item.getAttribute())){
            raw = domElement.attr("abs:href");
        }else if (ParserConfigItem.ATTR_ID.equals(item.getAttribute())){
            raw = domElement.id();
        }else if (ParserConfigItem.ATTR_HTML.equals(item.getAttribute())){
            raw = domElement.html();
        }else{
            raw = domElement.attr(item.getAttribute());
        }
        return raw;
    }

    @Override
    public List<String> getMoreUrls(Document document) {
        List<String> retList = new ArrayList<>();
        List<ParserConfigItem> configItems = config.getMoreUrlItemList();
        if(null!=configItems){

            for(ParserConfigItem item:configItems){
                String url = parseDom(document, item);
                if(null!=url && !url.isEmpty()){
                    retList.add(url);
                }
            }
        }

        return retList;
    }

    @Override
    public String[] getColumnTitles() {


        return config.getColumnTitles();
    }

    @Override
    public String getPageNum(String url, Document document) {
        ParserConfigItem parserConfigItem = config.getPageNumItem();
        String pageNum = null;
        if(null!= parserConfigItem){
            String selector = parserConfigItem.getSelector();

            if(ParserConfigItem.PAGE_NUM_URL.equals(selector)){
                pageNum = parseRegx(parserConfigItem,url);
            }else{
                pageNum = parseDom(document,parserConfigItem);
            }
        }
        if(null!=pageNum && pageNum.isEmpty()) pageNum = null;
        return pageNum;
    }

    @Override
    public int getTimeout() {
        return config.getTimeout()==null?-1:config.getTimeout();
    }

    @Override
    public String getUserAgent() {
        return config.getUserAgent();
    }
    @Override
    public String getReferrer() {
        return config.getReferer();
    }
}
