package com.github.liyue2008.spider.web.vo;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.parser.IParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2017/4/17.
 */
public class ParserPreviewVo {
    private String url;
    private IParser parser;
    private ParserConfig parserConfig;
    private Date touchDate;
    private Integer serial;
    private Document document;
    private List<Element> elementList;
    private String [][] results;

    public String[] getColumnTitles() {
        return columnTitles;
    }

    public void setColumnTitles(String[] columnTitles) {
        this.columnTitles = columnTitles;
    }

    private String [] columnTitles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

    public ParserConfig getParserConfig() {
        return parserConfig;
    }

    public void setParserConfig(ParserConfig parserConfig) {
        this.parserConfig = parserConfig;
    }

    public Date getTouchDate() {
        return touchDate;
    }

    public void setTouchDate(Date touchDate) {
        this.touchDate = touchDate;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public String[][] getResults() {
        return results;
    }

    public void setResults(String[][] results) {
        this.results = results;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }
}
