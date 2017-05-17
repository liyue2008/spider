package com.github.liyue2008.spider.core.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by liyue on 2017/3/30.
 */
public interface IParser {
    Elements getList(Document document);
    String [] parserElement(Element element);

    List<String> getMoreUrls(Document document);

    String [] getColumnTitles();
    String getPageNum(String url,Document document);
    int getTimeout();
    String getUserAgent();
    String getReferrer();
}
