package com.github.liyue2008.spider.core.entity;

import java.util.ArrayList;
import java.util.List;

public class ParserConfigItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ParserConfigItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdIsNull() {
            addCriterion("PARSER_CONFIG_ID is null");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdIsNotNull() {
            addCriterion("PARSER_CONFIG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdEqualTo(Integer value) {
            addCriterion("PARSER_CONFIG_ID =", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdNotEqualTo(Integer value) {
            addCriterion("PARSER_CONFIG_ID <>", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdGreaterThan(Integer value) {
            addCriterion("PARSER_CONFIG_ID >", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARSER_CONFIG_ID >=", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdLessThan(Integer value) {
            addCriterion("PARSER_CONFIG_ID <", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARSER_CONFIG_ID <=", value, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdIn(List<Integer> values) {
            addCriterion("PARSER_CONFIG_ID in", values, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdNotIn(List<Integer> values) {
            addCriterion("PARSER_CONFIG_ID not in", values, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("PARSER_CONFIG_ID between", value1, value2, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andParserConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARSER_CONFIG_ID not between", value1, value2, "parserConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdIsNull() {
            addCriterion("SUB_CONFIG_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdIsNotNull() {
            addCriterion("SUB_CONFIG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdEqualTo(Integer value) {
            addCriterion("SUB_CONFIG_ID =", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdNotEqualTo(Integer value) {
            addCriterion("SUB_CONFIG_ID <>", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdGreaterThan(Integer value) {
            addCriterion("SUB_CONFIG_ID >", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SUB_CONFIG_ID >=", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdLessThan(Integer value) {
            addCriterion("SUB_CONFIG_ID <", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("SUB_CONFIG_ID <=", value, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdIn(List<Integer> values) {
            addCriterion("SUB_CONFIG_ID in", values, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdNotIn(List<Integer> values) {
            addCriterion("SUB_CONFIG_ID not in", values, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("SUB_CONFIG_ID between", value1, value2, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andSubConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SUB_CONFIG_ID not between", value1, value2, "subConfigId");
            return (Criteria) this;
        }

        public Criteria andColumnTitleIsNull() {
            addCriterion("COLUMN_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andColumnTitleIsNotNull() {
            addCriterion("COLUMN_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andColumnTitleEqualTo(String value) {
            addCriterion("COLUMN_TITLE =", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleNotEqualTo(String value) {
            addCriterion("COLUMN_TITLE <>", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleGreaterThan(String value) {
            addCriterion("COLUMN_TITLE >", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_TITLE >=", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleLessThan(String value) {
            addCriterion("COLUMN_TITLE <", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_TITLE <=", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleLike(String value) {
            addCriterion("COLUMN_TITLE like", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleNotLike(String value) {
            addCriterion("COLUMN_TITLE not like", value, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleIn(List<String> values) {
            addCriterion("COLUMN_TITLE in", values, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleNotIn(List<String> values) {
            addCriterion("COLUMN_TITLE not in", values, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleBetween(String value1, String value2) {
            addCriterion("COLUMN_TITLE between", value1, value2, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andColumnTitleNotBetween(String value1, String value2) {
            addCriterion("COLUMN_TITLE not between", value1, value2, "columnTitle");
            return (Criteria) this;
        }

        public Criteria andSelectorIsNull() {
            addCriterion("SELECTOR is null");
            return (Criteria) this;
        }

        public Criteria andSelectorIsNotNull() {
            addCriterion("SELECTOR is not null");
            return (Criteria) this;
        }

        public Criteria andSelectorEqualTo(String value) {
            addCriterion("SELECTOR =", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorNotEqualTo(String value) {
            addCriterion("SELECTOR <>", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorGreaterThan(String value) {
            addCriterion("SELECTOR >", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorGreaterThanOrEqualTo(String value) {
            addCriterion("SELECTOR >=", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorLessThan(String value) {
            addCriterion("SELECTOR <", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorLessThanOrEqualTo(String value) {
            addCriterion("SELECTOR <=", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorLike(String value) {
            addCriterion("SELECTOR like", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorNotLike(String value) {
            addCriterion("SELECTOR not like", value, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorIn(List<String> values) {
            addCriterion("SELECTOR in", values, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorNotIn(List<String> values) {
            addCriterion("SELECTOR not in", values, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorBetween(String value1, String value2) {
            addCriterion("SELECTOR between", value1, value2, "selector");
            return (Criteria) this;
        }

        public Criteria andSelectorNotBetween(String value1, String value2) {
            addCriterion("SELECTOR not between", value1, value2, "selector");
            return (Criteria) this;
        }

        public Criteria andDomIndexIsNull() {
            addCriterion("DOM_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andDomIndexIsNotNull() {
            addCriterion("DOM_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andDomIndexEqualTo(Integer value) {
            addCriterion("DOM_INDEX =", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexNotEqualTo(Integer value) {
            addCriterion("DOM_INDEX <>", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexGreaterThan(Integer value) {
            addCriterion("DOM_INDEX >", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("DOM_INDEX >=", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexLessThan(Integer value) {
            addCriterion("DOM_INDEX <", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexLessThanOrEqualTo(Integer value) {
            addCriterion("DOM_INDEX <=", value, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexIn(List<Integer> values) {
            addCriterion("DOM_INDEX in", values, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexNotIn(List<Integer> values) {
            addCriterion("DOM_INDEX not in", values, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexBetween(Integer value1, Integer value2) {
            addCriterion("DOM_INDEX between", value1, value2, "domIndex");
            return (Criteria) this;
        }

        public Criteria andDomIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("DOM_INDEX not between", value1, value2, "domIndex");
            return (Criteria) this;
        }

        public Criteria andAttributeIsNull() {
            addCriterion("ATTRIBUTE is null");
            return (Criteria) this;
        }

        public Criteria andAttributeIsNotNull() {
            addCriterion("ATTRIBUTE is not null");
            return (Criteria) this;
        }

        public Criteria andAttributeEqualTo(String value) {
            addCriterion("ATTRIBUTE =", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotEqualTo(String value) {
            addCriterion("ATTRIBUTE <>", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThan(String value) {
            addCriterion("ATTRIBUTE >", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE >=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThan(String value) {
            addCriterion("ATTRIBUTE <", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE <=", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeLike(String value) {
            addCriterion("ATTRIBUTE like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotLike(String value) {
            addCriterion("ATTRIBUTE not like", value, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeIn(List<String> values) {
            addCriterion("ATTRIBUTE in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotIn(List<String> values) {
            addCriterion("ATTRIBUTE not in", values, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE between", value1, value2, "attribute");
            return (Criteria) this;
        }

        public Criteria andAttributeNotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE not between", value1, value2, "attribute");
            return (Criteria) this;
        }

        public Criteria andSeparatorIsNull() {
            addCriterion("SEPARATOR is null");
            return (Criteria) this;
        }

        public Criteria andSeparatorIsNotNull() {
            addCriterion("SEPARATOR is not null");
            return (Criteria) this;
        }

        public Criteria andSeparatorEqualTo(String value) {
            addCriterion("SEPARATOR =", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorNotEqualTo(String value) {
            addCriterion("SEPARATOR <>", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorGreaterThan(String value) {
            addCriterion("SEPARATOR >", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorGreaterThanOrEqualTo(String value) {
            addCriterion("SEPARATOR >=", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorLessThan(String value) {
            addCriterion("SEPARATOR <", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorLessThanOrEqualTo(String value) {
            addCriterion("SEPARATOR <=", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorLike(String value) {
            addCriterion("SEPARATOR like", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorNotLike(String value) {
            addCriterion("SEPARATOR not like", value, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorIn(List<String> values) {
            addCriterion("SEPARATOR in", values, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorNotIn(List<String> values) {
            addCriterion("SEPARATOR not in", values, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorBetween(String value1, String value2) {
            addCriterion("SEPARATOR between", value1, value2, "separator");
            return (Criteria) this;
        }

        public Criteria andSeparatorNotBetween(String value1, String value2) {
            addCriterion("SEPARATOR not between", value1, value2, "separator");
            return (Criteria) this;
        }

        public Criteria andPatternIsNull() {
            addCriterion("PATTERN is null");
            return (Criteria) this;
        }

        public Criteria andPatternIsNotNull() {
            addCriterion("PATTERN is not null");
            return (Criteria) this;
        }

        public Criteria andPatternEqualTo(String value) {
            addCriterion("PATTERN =", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotEqualTo(String value) {
            addCriterion("PATTERN <>", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThan(String value) {
            addCriterion("PATTERN >", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternGreaterThanOrEqualTo(String value) {
            addCriterion("PATTERN >=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThan(String value) {
            addCriterion("PATTERN <", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLessThanOrEqualTo(String value) {
            addCriterion("PATTERN <=", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternLike(String value) {
            addCriterion("PATTERN like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotLike(String value) {
            addCriterion("PATTERN not like", value, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternIn(List<String> values) {
            addCriterion("PATTERN in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotIn(List<String> values) {
            addCriterion("PATTERN not in", values, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternBetween(String value1, String value2) {
            addCriterion("PATTERN between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternNotBetween(String value1, String value2) {
            addCriterion("PATTERN not between", value1, value2, "pattern");
            return (Criteria) this;
        }

        public Criteria andPatternIndexIsNull() {
            addCriterion("PATTERN_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andPatternIndexIsNotNull() {
            addCriterion("PATTERN_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andPatternIndexEqualTo(Integer value) {
            addCriterion("PATTERN_INDEX =", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexNotEqualTo(Integer value) {
            addCriterion("PATTERN_INDEX <>", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexGreaterThan(Integer value) {
            addCriterion("PATTERN_INDEX >", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("PATTERN_INDEX >=", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexLessThan(Integer value) {
            addCriterion("PATTERN_INDEX <", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexLessThanOrEqualTo(Integer value) {
            addCriterion("PATTERN_INDEX <=", value, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexIn(List<Integer> values) {
            addCriterion("PATTERN_INDEX in", values, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexNotIn(List<Integer> values) {
            addCriterion("PATTERN_INDEX not in", values, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexBetween(Integer value1, Integer value2) {
            addCriterion("PATTERN_INDEX between", value1, value2, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andPatternIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("PATTERN_INDEX not between", value1, value2, "patternIndex");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNull() {
            addCriterion("ITEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIsNotNull() {
            addCriterion("ITEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeEqualTo(Integer value) {
            addCriterion("ITEM_TYPE =", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotEqualTo(Integer value) {
            addCriterion("ITEM_TYPE <>", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThan(Integer value) {
            addCriterion("ITEM_TYPE >", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_TYPE >=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThan(Integer value) {
            addCriterion("ITEM_TYPE <", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_TYPE <=", value, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeIn(List<Integer> values) {
            addCriterion("ITEM_TYPE in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotIn(List<Integer> values) {
            addCriterion("ITEM_TYPE not in", values, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_TYPE between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_TYPE not between", value1, value2, "itemType");
            return (Criteria) this;
        }

        public Criteria andItemIndexIsNull() {
            addCriterion("ITEM_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andItemIndexIsNotNull() {
            addCriterion("ITEM_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andItemIndexEqualTo(Integer value) {
            addCriterion("ITEM_INDEX =", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexNotEqualTo(Integer value) {
            addCriterion("ITEM_INDEX <>", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexGreaterThan(Integer value) {
            addCriterion("ITEM_INDEX >", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_INDEX >=", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexLessThan(Integer value) {
            addCriterion("ITEM_INDEX <", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_INDEX <=", value, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexIn(List<Integer> values) {
            addCriterion("ITEM_INDEX in", values, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexNotIn(List<Integer> values) {
            addCriterion("ITEM_INDEX not in", values, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_INDEX between", value1, value2, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andItemIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_INDEX not between", value1, value2, "itemIndex");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}