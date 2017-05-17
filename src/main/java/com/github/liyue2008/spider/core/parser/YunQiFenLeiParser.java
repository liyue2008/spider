package com.github.liyue2008.spider.core.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liyue on 2017/3/30.
 */

public class YunQiFenLeiParser implements IParser {
    @Override
    public Elements getList(Document document) {
        return document.select(".bookdetail");
    }
    Pattern r = Pattern.compile("http://yunqi.qq.com/bk/\\w+/(\\d+).html");
    @Override
    public String[] parserElement(Element element) {

//        Media media = new Media();
        String [] result = new String [3];

        Element titleAElement = element.select("h4 a").first();
        if(null == titleAElement) return null;
        String url = titleAElement.attr("href");
        Matcher m = r.matcher(url);
        if (m.find( )) {
//            media.setId(m.group(1));
            result[0] = m.group(1);
        }
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
