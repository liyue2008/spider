package com.github.liyue2008.spider.core.entity;

import java.util.ArrayList;
import java.util.List;

public class JobConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobConfigExample() {
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

        public Criteria andJobLabelIsNull() {
            addCriterion("JOB_LABEL is null");
            return (Criteria) this;
        }

        public Criteria andJobLabelIsNotNull() {
            addCriterion("JOB_LABEL is not null");
            return (Criteria) this;
        }

        public Criteria andJobLabelEqualTo(String value) {
            addCriterion("JOB_LABEL =", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelNotEqualTo(String value) {
            addCriterion("JOB_LABEL <>", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelGreaterThan(String value) {
            addCriterion("JOB_LABEL >", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_LABEL >=", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelLessThan(String value) {
            addCriterion("JOB_LABEL <", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelLessThanOrEqualTo(String value) {
            addCriterion("JOB_LABEL <=", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelLike(String value) {
            addCriterion("JOB_LABEL like", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelNotLike(String value) {
            addCriterion("JOB_LABEL not like", value, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelIn(List<String> values) {
            addCriterion("JOB_LABEL in", values, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelNotIn(List<String> values) {
            addCriterion("JOB_LABEL not in", values, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelBetween(String value1, String value2) {
            addCriterion("JOB_LABEL between", value1, value2, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andJobLabelNotBetween(String value1, String value2) {
            addCriterion("JOB_LABEL not between", value1, value2, "jobLabel");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlIsNull() {
            addCriterion("PREVIEW_URL is null");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlIsNotNull() {
            addCriterion("PREVIEW_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlEqualTo(String value) {
            addCriterion("PREVIEW_URL =", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlNotEqualTo(String value) {
            addCriterion("PREVIEW_URL <>", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlGreaterThan(String value) {
            addCriterion("PREVIEW_URL >", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PREVIEW_URL >=", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlLessThan(String value) {
            addCriterion("PREVIEW_URL <", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlLessThanOrEqualTo(String value) {
            addCriterion("PREVIEW_URL <=", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlLike(String value) {
            addCriterion("PREVIEW_URL like", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlNotLike(String value) {
            addCriterion("PREVIEW_URL not like", value, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlIn(List<String> values) {
            addCriterion("PREVIEW_URL in", values, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlNotIn(List<String> values) {
            addCriterion("PREVIEW_URL not in", values, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlBetween(String value1, String value2) {
            addCriterion("PREVIEW_URL between", value1, value2, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andPreviewUrlNotBetween(String value1, String value2) {
            addCriterion("PREVIEW_URL not between", value1, value2, "previewUrl");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNull() {
            addCriterion("JOB_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andJobTypeIsNotNull() {
            addCriterion("JOB_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andJobTypeEqualTo(String value) {
            addCriterion("JOB_TYPE =", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotEqualTo(String value) {
            addCriterion("JOB_TYPE <>", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThan(String value) {
            addCriterion("JOB_TYPE >", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_TYPE >=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThan(String value) {
            addCriterion("JOB_TYPE <", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLessThanOrEqualTo(String value) {
            addCriterion("JOB_TYPE <=", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeLike(String value) {
            addCriterion("JOB_TYPE like", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotLike(String value) {
            addCriterion("JOB_TYPE not like", value, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeIn(List<String> values) {
            addCriterion("JOB_TYPE in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotIn(List<String> values) {
            addCriterion("JOB_TYPE not in", values, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeBetween(String value1, String value2) {
            addCriterion("JOB_TYPE between", value1, value2, "jobType");
            return (Criteria) this;
        }

        public Criteria andJobTypeNotBetween(String value1, String value2) {
            addCriterion("JOB_TYPE not between", value1, value2, "jobType");
            return (Criteria) this;
        }

        public Criteria andParserIdIsNull() {
            addCriterion("PARSER_ID is null");
            return (Criteria) this;
        }

        public Criteria andParserIdIsNotNull() {
            addCriterion("PARSER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParserIdEqualTo(Integer value) {
            addCriterion("PARSER_ID =", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdNotEqualTo(Integer value) {
            addCriterion("PARSER_ID <>", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdGreaterThan(Integer value) {
            addCriterion("PARSER_ID >", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARSER_ID >=", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdLessThan(Integer value) {
            addCriterion("PARSER_ID <", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARSER_ID <=", value, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdIn(List<Integer> values) {
            addCriterion("PARSER_ID in", values, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdNotIn(List<Integer> values) {
            addCriterion("PARSER_ID not in", values, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdBetween(Integer value1, Integer value2) {
            addCriterion("PARSER_ID between", value1, value2, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARSER_ID not between", value1, value2, "parserId");
            return (Criteria) this;
        }

        public Criteria andParserClassIsNull() {
            addCriterion("PARSER_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andParserClassIsNotNull() {
            addCriterion("PARSER_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andParserClassEqualTo(String value) {
            addCriterion("PARSER_CLASS =", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassNotEqualTo(String value) {
            addCriterion("PARSER_CLASS <>", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassGreaterThan(String value) {
            addCriterion("PARSER_CLASS >", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassGreaterThanOrEqualTo(String value) {
            addCriterion("PARSER_CLASS >=", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassLessThan(String value) {
            addCriterion("PARSER_CLASS <", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassLessThanOrEqualTo(String value) {
            addCriterion("PARSER_CLASS <=", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassLike(String value) {
            addCriterion("PARSER_CLASS like", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassNotLike(String value) {
            addCriterion("PARSER_CLASS not like", value, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassIn(List<String> values) {
            addCriterion("PARSER_CLASS in", values, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassNotIn(List<String> values) {
            addCriterion("PARSER_CLASS not in", values, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassBetween(String value1, String value2) {
            addCriterion("PARSER_CLASS between", value1, value2, "parserClass");
            return (Criteria) this;
        }

        public Criteria andParserClassNotBetween(String value1, String value2) {
            addCriterion("PARSER_CLASS not between", value1, value2, "parserClass");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexIsNull() {
            addCriterion("SORT_RESULT_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexIsNotNull() {
            addCriterion("SORT_RESULT_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexEqualTo(Integer value) {
            addCriterion("SORT_RESULT_INDEX =", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexNotEqualTo(Integer value) {
            addCriterion("SORT_RESULT_INDEX <>", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexGreaterThan(Integer value) {
            addCriterion("SORT_RESULT_INDEX >", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_RESULT_INDEX >=", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexLessThan(Integer value) {
            addCriterion("SORT_RESULT_INDEX <", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_RESULT_INDEX <=", value, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexIn(List<Integer> values) {
            addCriterion("SORT_RESULT_INDEX in", values, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexNotIn(List<Integer> values) {
            addCriterion("SORT_RESULT_INDEX not in", values, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexBetween(Integer value1, Integer value2) {
            addCriterion("SORT_RESULT_INDEX between", value1, value2, "sortResultIndex");
            return (Criteria) this;
        }

        public Criteria andSortResultIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_RESULT_INDEX not between", value1, value2, "sortResultIndex");
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