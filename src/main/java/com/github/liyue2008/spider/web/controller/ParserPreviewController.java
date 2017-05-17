package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.core.entity.ParserConfigItem;
import com.github.liyue2008.spider.web.service.ParserPreviewService;
import com.github.liyue2008.spider.web.utils.RestUtils;
import com.github.liyue2008.spider.web.vo.ParserPreviewVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue on 2017/4/17.
 */
@RestController
@RequestMapping("/preview")
public class ParserPreviewController {
    @Resource
    private ParserPreviewService parserPreviewService;

    @RequestMapping(value = "/test-url",method = RequestMethod.POST)
    public Object testUrl(
            @RequestParam String url,
            @RequestParam(required = false,defaultValue = "-1") int timeout,
            @RequestParam(required = false,defaultValue = "")String referrer,
            @RequestParam(required = false,defaultValue = "")String userAgent,
            @RequestParam(required = false,defaultValue = "-1") int serial){
        ParserPreviewVo vo = parserPreviewService.testUrl(url,timeout,referrer,userAgent,serial);
        if(vo==null){
            return RestUtils.errorResponse(null);
        }else{
            Map<String,Object> retMap = RestUtils.successResponse();
            retMap.put("serial",vo.getSerial());
            return retMap;
        }
    }

    @RequestMapping(value = "/test-list-selector",method = RequestMethod.POST)
    public Object testListSelector(@RequestParam int serial,@RequestParam int parserConfigType,@RequestParam String listSelector){
        ParserPreviewVo vo = parserPreviewService.testListSelector(serial,parserConfigType,listSelector);
        if(vo==null || vo.getElementList() ==null || vo.getElementList().size() == 0 ){
            return RestUtils.errorResponse(null);
        }else{
            Map<String,Object> retMap = RestUtils.successResponse();
            retMap.put("serial",vo.getSerial());
            List<Element> elementList = vo.getElementList();

            List<String> elementHtmlList = new ArrayList<>(elementList.size());
            for(Element e:elementList){
                String html = StringEscapeUtils.unescapeHtml4(e.html());
                elementHtmlList.add(html);
            }
            retMap.put("elements",elementHtmlList);
            return retMap;
        }

    }
    @RequestMapping(value = "/test-parser-config-items/{serial:\\d+}",method = RequestMethod.POST)
    public Object testParserConfigItems(@PathVariable int serial, @RequestBody List<ParserConfigItem> items){
        ParserPreviewVo vo =  parserPreviewService.testParserConfigItems(serial,items);
        if(vo==null || vo.getElementList() ==null || vo.getElementList().size() == 0 ){
            return RestUtils.errorResponse(null);
        }else{
            Map<String,Object> retMap = RestUtils.successResponse();
            retMap.put("serial",vo.getSerial());
            retMap.put("results",vo.getResults());
            String [] columnTitles = vo.getColumnTitles();
            retMap.put("columnTitles",columnTitles);
            return retMap;
        }

    }
    @RequestMapping(value = "/test-parser-config-url-items/{serial:\\d+}/{parserConfigType:[1-2]}",method = RequestMethod.POST)
    public Object testParserConfigUrlItems(@PathVariable int serial, @RequestBody List<ParserConfigItem> items,@PathVariable int parserConfigType){
        List<String> urlList =  parserPreviewService.testParserConfigUrlItems(serial,parserConfigType,items);
        if(urlList==null ){
            return RestUtils.errorResponse(null);
        }else{
            Map<String,Object> retMap = RestUtils.successResponse();
            retMap.put("urls",urlList);

            return retMap;
        }

    }
    @RequestMapping(value = "/test-parser-config-page-item/{serial:\\d+}/{parserConfigType:[1-2]}",method = RequestMethod.POST)
    public Object testParserConfigPageItem(@PathVariable int serial, @RequestBody ParserConfigItem pageItem,@PathVariable int parserConfigType){
        String pageNum =  parserPreviewService.testParserConfigPageItem(serial,parserConfigType,pageItem);
        if(pageNum==null ){
            return RestUtils.errorResponse(null);
        }else{
            Map<String,Object> retMap = RestUtils.successResponse();
            retMap.put("page",pageNum);

            return retMap;
        }

    }
}
