package com.github.liyue2008.spider.core.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by liyue on 2017/4/1.
 */
public class SimplePrintHtmlParser implements IParser {
    private final Logger logger = LoggerFactory.getLogger(SimplePrintHtmlParser.class);
    @Override
    public Elements getList(Document document) {
        logger.info(document.html());
        return document.getAllElements();
    }

    @Override
    public String [] parserElement(Element element) {
        return null;
    }

    @Override
    public List<String> getMoreUrls(Document document) {
        return null;
    }

    @Override
    public String[] getColumnTitles() {
        return new String[0];
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
