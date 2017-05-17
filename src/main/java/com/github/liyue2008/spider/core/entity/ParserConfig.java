package com.github.liyue2008.spider.core.entity;


import java.util.ArrayList;
import java.util.List;

public class ParserConfig extends ParserConfigBase{

    public final static int PARSER_CONFIG_TYPE_HTML = 1;
    public final static int PARSER_CONFIG_TYPE_JSON = 2;

    private List<ParserConfigItem> itemList;
    private List<ParserConfigItem> moreUrlItemList;



    public String getParserConfigTypeName(){
        switch (getType()){
            case PARSER_CONFIG_TYPE_HTML : return "HTML";
            case PARSER_CONFIG_TYPE_JSON : return "JSON";
            default: return String.valueOf(getType());
        }
    }
    private ParserConfigItem pageNumItem;


    public List<ParserConfigItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ParserConfigItem> itemList) {
        this.itemList = itemList;
    }



    public ParserConfigItem getPageNumItem() {
        return pageNumItem;
    }

    public void setPageNumItem(ParserConfigItem pageNumItem) {
        this.pageNumItem = pageNumItem;
    }

    public List<ParserConfigItem> getMoreUrlItemList() {
        return moreUrlItemList;
    }

    public void setMoreUrlItemList(List<ParserConfigItem> moreUrlItemList) {
        this.moreUrlItemList = moreUrlItemList;
    }

    public List<ParserConfigItem> getAllItemList() {
        List<ParserConfigItem> allItemList = new ArrayList<>();
        if(null!=getItemList() && getItemList().size() > 0) {
            allItemList.addAll(getItemList());
        }
        if(getPageNumItem() != null) {
            allItemList.add(getPageNumItem());
        }
        if(null!=getMoreUrlItemList() && getMoreUrlItemList().size() > 0) {
            allItemList.addAll(getMoreUrlItemList());
        }
        return allItemList;
    }

    public int getColumnCount(){
        int count = 0;
        for(ParserConfigItem i:getItemList()){
            if(i.getSubConfigId() == null) count ++;
            else{
                count+=i.getSubConfig().getColumnCount();
            }
        }
        return count;
    }


    public String[] getColumnTitles() {
        List<ParserConfigItem> configItems = getItemList();
        if(null==configItems) return new String[0];
        String [] retArray = new String[getColumnCount()];
        int index = 0;
        for(ParserConfigItem item:configItems){
            if(item.getSubConfigId() == null) {
                retArray[index++] = item.getColumnTitle();
            }else{
                ParserConfig subConfig = item.getSubConfig();
                String [] subColumnTitles = subConfig.getColumnTitles();
                for(String c:subColumnTitles){
                    retArray[index++] = c;
                }
            }
        }

        return retArray;
    }
}