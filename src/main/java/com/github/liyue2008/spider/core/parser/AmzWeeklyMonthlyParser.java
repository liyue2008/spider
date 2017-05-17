package com.github.liyue2008.spider.core.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liyue on 2017/4/1.
 */
public class AmzWeeklyMonthlyParser implements IParser {
    @Override
    public Elements getList(Document document) {
        return document.select("div.a-fixed-left-grid-col.a-col-right");
    }

    private Pattern patternPrice = Pattern.compile("(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?");
    private Pattern patternId = Pattern.compile("/dp/(\\w+)/");
    @Override
    public String [] parserElement(Element element) {
        String [] result = new String [4];
        Matcher m;

//        Media media = new Media();
        Element urlElement = element.select("a.s-access-detail-page").first();
        if(null==urlElement) return  null;
        String url = urlElement.attr("href");
        m = patternId.matcher(url);
        if (m.find( )) {
//            media.setId(m.group(1));
            result[0] = m.group(1);
        }else{
            return null;
        }

        Element titleElement = urlElement;
//        media.setTitle(titleElement.attr("title"));
        result[1] = titleElement.attr("title");
        Elements authorElements = element.select("div.a-row.a-spacing-small div.a-row.a-spacing-none:last-child span.a-size-small.a-color-secondary");
        String author = "";
        for(Element authorElement:authorElements) {

            if (null != authorElement) {
                author+=authorElement.text();
            }
        }
//        media.setAuthor(author);
        result[3] = author;
        Element priceElement = element.select("span.s-price").last();
        if(null!=priceElement){
            m = patternPrice.matcher(priceElement.text());
            if (m.find( )) {
//                media.setPrice(m.group(0));
                result[2] = m.group(0);
            }
        }
        return result;
    }

    @Override
    public List<String> getMoreUrls(Document document) {
        Element element = document.select("a#pagnNextLink").first();
        List<String> urls = new ArrayList<>();
        if(null!=element){
            urls.add(element.attr("abs:href"));
        }
        return urls;
    }
    @Override
    public String[] getColumnTitles() {
        return new String[] {"ASIN","书名","价格","作者"};
    }

    @Override
    public String getPageNum(String url, Document document) {
        Element element = document.select("div#pagn span.pagnCur").first();
        if(null!=element) return element.text();
        return null;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public String getUserAgent() {
        return null;
    }
    @Override
    public String getReferrer() {
        return null;
    }
}
