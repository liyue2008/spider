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
public class AmzNewReleasesParser implements IParser {
    @Override
    public Elements getList(Document document) {
        return document.select("div.a-fixed-left-grid-col.a-col-right");
    }
    private Pattern patternPage = Pattern.compile("zg_bsnr_116169071_pg_(\\d+)\\?");

    private Pattern patternIndex = Pattern.compile("(\\d+)\\.");
    private Pattern patternPrice = Pattern.compile("(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?");
    private Pattern patternId = Pattern.compile("/gp/product/(\\w+)");
    @Override
    public String [] parserElement(Element element) {
        String [] result = new String[5];
        Matcher m;
//        Media media = new Media();
        Element indexElement = element.select("div.zg_rankLine span.zg_rankNumber").first();
        if(null != indexElement) {
            m = patternIndex.matcher(indexElement.text());
            if (m.find( )) {
//                media.setRank(m.group(1));
                result[4] = m.group(1);
            }
        }

        Element urlElement = element.select("a.a-link-normal").first();
        if(null==urlElement) return  null;
        String url = urlElement.attr("href");
        m = patternId.matcher(url);
        if (m.find( )) {
//            media.setId(m.group(1));
            result[0] = m.group(1);
        }else{
            return null;
        }

        Element titleElement = urlElement.select("div").first();
        if(null==titleElement) return null;
//        media.setTitle(titleElement.text());
        result[1] = titleElement.text();

        Element authorElement = element.select("div.a-row.a-size-small span.a-size-small.a-color-base").first();
        if(null!=authorElement){
//            media.setAuthor(authorElement.text());
            result[3] = authorElement.text();
        }

        Element priceElement = element.select("div.a-row span.a-size-base.a-color-price span.p13n-sc-price").last();
        if(null==priceElement) priceElement = element.select("div.a-row span.a-size-base.a-color-price").last();
        if(null!=priceElement){
            m = patternPrice.matcher(priceElement.text());
            if (m.find( )) {
//                media.setPrice(m.group(0));
                result[2] = m.group(0);

            }else{
                return null;
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
        return new String[] {"ASIN","书名","价格","作者","排名"};
    }
    @Override
    public String getPageNum(String url, Document document) {
        Matcher m;
        m = patternPage.matcher(url);
        if(m.find()){
            return m.group(1);
        }
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
