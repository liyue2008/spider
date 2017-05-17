package com.github.liyue2008.spider.core.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liyue on 2017/4/1.
 */
public class AmzTodayParser implements IParser {
    @Override
    public Elements getList(Document document) {
        return document.select("div.gridProductContainer");
    }

    private Pattern patternPrice = Pattern.compile("(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?");
    private Pattern patternId = Pattern.compile("/gp/product/(\\w+)");
    @Override
    public String [] parserElement(Element element) {
        String [] result = new String [4];
        Matcher m;

//        Media media = new Media();
        Element urlElement = element.select("div.productTitle a").first();
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
//        media.setTitle(titleElement.text());
        result[1] = titleElement.text();

        Element authorElement = element.select("divproductByLine").first();
        if(null!=authorElement){
//            media.setAuthor(authorElement.text());
            result[3] = authorElement.text();
        }

        Element priceElement = element.select("div.a-row span.a-size-base.a-color-price span.p13n-sc-price").first();
        if(null==priceElement) priceElement = element.select("span.productBuyPrice span.a-color-price").first();
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
        return null;
    }

    @Override
    public String[] getColumnTitles() {
        return new String[] {"ASIN","书名","价格","作者"};
    }

    @Override
    public String getPageNum(String url, Document document) {
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
