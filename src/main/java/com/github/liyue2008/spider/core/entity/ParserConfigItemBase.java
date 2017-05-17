package com.github.liyue2008.spider.core.entity;

public class ParserConfigItemBase {
    private Integer id;

    private Integer parserConfigId;

    private Integer subConfigId;

    private String columnTitle;

    private String selector;

    private Integer domIndex;

    private String attribute;

    private String separator;

    private String pattern;

    private Integer patternIndex;

    private Integer itemType;

    private Integer itemIndex;

    private String remark;

    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParserConfigId() {
        return parserConfigId;
    }

    public void setParserConfigId(Integer parserConfigId) {
        this.parserConfigId = parserConfigId;
    }

    public Integer getSubConfigId() {
        return subConfigId;
    }

    public void setSubConfigId(Integer subConfigId) {
        this.subConfigId = subConfigId;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle == null ? null : columnTitle.trim();
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector == null ? null : selector.trim();
    }

    public Integer getDomIndex() {
        return domIndex;
    }

    public void setDomIndex(Integer domIndex) {
        this.domIndex = domIndex;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator == null ? null : separator.trim();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }

    public Integer getPatternIndex() {
        return patternIndex;
    }

    public void setPatternIndex(Integer patternIndex) {
        this.patternIndex = patternIndex;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Integer itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}