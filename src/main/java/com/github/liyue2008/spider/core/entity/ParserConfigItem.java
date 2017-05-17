package com.github.liyue2008.spider.core.entity;

public class ParserConfigItem extends ParserConfigItemBase{
    public final static int DOM_INDEX_FIRST = -1;
    public final static int DOM_INDEX_LAST = -2;
    public final static int DOM_INDEX_ALL = -3;

    public final static String ATTR_HREF = "_HREF";
    public final static String ATTR_ID = "_ID";
    public final static String ATTR_HTML = "_HTML";
    public final static String ATTR_TEXT = "_TEXT";

    public final static String PAGE_NUM_URL="_PAGE_NUM_URL";

    public final static int ITEM_TYPE_DOM = 1;
    public final static int ITEM_TYPE_URL = 2;
    public final static int ITEM_TYPE_PAGE_NUM = 3;


    private ParserConfig subConfig;

    public ParserConfig getSubConfig() {
        return subConfig;
    }

    public void setSubConfig(ParserConfig subConfig) {
        this.subConfig = subConfig;
    }
}