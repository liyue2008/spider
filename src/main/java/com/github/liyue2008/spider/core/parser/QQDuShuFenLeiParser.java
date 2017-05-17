package com.github.liyue2008.spider.core.parser;

import com.github.liyue2008.spider.core.utils.UrlAnayseUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by liyue on 2017/3/30.
 */

public class QQDuShuFenLeiParser implements IParser {
    @Override
    public Elements getList(Document document) {
        return document.select(".bookdetail");
    }

    @Override
    public String [] parserElement(Element element) {

        String [] result = new String [3];
//        Media media = new Media();
        Element titleAElement = element.select("h4 a").first();
        if(null == titleAElement) return null;
        String url = titleAElement.attr("href");
//        media.setId(UrlAnayseUtil.getQueryParams(url).get("bid").get(0));
        result[0] = UrlAnayseUtil.getQueryParams(url).get("bid").get(0);
//        media.setTitle(titleAElement.text());
        result[1] = titleAElement.text();
        Element authorSpanElement = element.select("span.author a").first();
//        media.setAuthor(authorSpanElement.text());
        result[2] = authorSpanElement.text();
        return result;
    }

    @Override
    public List<String> getMoreUrls(Document document) {
        return null;
    }

    @Override
    public String[] getColumnTitles() {
        return new String[] {"ID","书名","作者"};
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
