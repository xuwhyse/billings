package com.social4j.model;

import java.util.ArrayList;
import java.util.List;

public class DiscoverCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscoverCriteria() {
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

        public Criteria andDiscoverIdIsNull() {
            addCriterion("discover_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdIsNotNull() {
            addCriterion("discover_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdEqualTo(Integer value) {
            addCriterion("discover_id =", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdNotEqualTo(Integer value) {
            addCriterion("discover_id <>", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdGreaterThan(Integer value) {
            addCriterion("discover_id >", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("discover_id >=", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdLessThan(Integer value) {
            addCriterion("discover_id <", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdLessThanOrEqualTo(Integer value) {
            addCriterion("discover_id <=", value, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdIn(List<Integer> values) {
            addCriterion("discover_id in", values, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdNotIn(List<Integer> values) {
            addCriterion("discover_id not in", values, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdBetween(Integer value1, Integer value2) {
            addCriterion("discover_id between", value1, value2, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("discover_id not between", value1, value2, "discoverId");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeIsNull() {
            addCriterion("discover_code is null");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeIsNotNull() {
            addCriterion("discover_code is not null");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeEqualTo(String value) {
            addCriterion("discover_code =", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeNotEqualTo(String value) {
            addCriterion("discover_code <>", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeGreaterThan(String value) {
            addCriterion("discover_code >", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeGreaterThanOrEqualTo(String value) {
            addCriterion("discover_code >=", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeLessThan(String value) {
            addCriterion("discover_code <", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeLessThanOrEqualTo(String value) {
            addCriterion("discover_code <=", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeLike(String value) {
            addCriterion("discover_code like", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeNotLike(String value) {
            addCriterion("discover_code not like", value, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeIn(List<String> values) {
            addCriterion("discover_code in", values, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeNotIn(List<String> values) {
            addCriterion("discover_code not in", values, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeBetween(String value1, String value2) {
            addCriterion("discover_code between", value1, value2, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeNotBetween(String value1, String value2) {
            addCriterion("discover_code not between", value1, value2, "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionIsNull() {
            addCriterion("discover_version is null");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionIsNotNull() {
            addCriterion("discover_version is not null");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionEqualTo(String value) {
            addCriterion("discover_version =", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionNotEqualTo(String value) {
            addCriterion("discover_version <>", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionGreaterThan(String value) {
            addCriterion("discover_version >", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionGreaterThanOrEqualTo(String value) {
            addCriterion("discover_version >=", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionLessThan(String value) {
            addCriterion("discover_version <", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionLessThanOrEqualTo(String value) {
            addCriterion("discover_version <=", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionLike(String value) {
            addCriterion("discover_version like", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionNotLike(String value) {
            addCriterion("discover_version not like", value, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionIn(List<String> values) {
            addCriterion("discover_version in", values, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionNotIn(List<String> values) {
            addCriterion("discover_version not in", values, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionBetween(String value1, String value2) {
            addCriterion("discover_version between", value1, value2, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionNotBetween(String value1, String value2) {
            addCriterion("discover_version not between", value1, value2, "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeIsNull() {
            addCriterion("discover_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeIsNotNull() {
            addCriterion("discover_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeEqualTo(Integer value) {
            addCriterion("discover_type =", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeNotEqualTo(Integer value) {
            addCriterion("discover_type <>", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeGreaterThan(Integer value) {
            addCriterion("discover_type >", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("discover_type >=", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeLessThan(Integer value) {
            addCriterion("discover_type <", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeLessThanOrEqualTo(Integer value) {
            addCriterion("discover_type <=", value, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeIn(List<Integer> values) {
            addCriterion("discover_type in", values, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeNotIn(List<Integer> values) {
            addCriterion("discover_type not in", values, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeBetween(Integer value1, Integer value2) {
            addCriterion("discover_type between", value1, value2, "discoverType");
            return (Criteria) this;
        }

        public Criteria andDiscoverTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("discover_type not between", value1, value2, "discoverType");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Long value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Long value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Long value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Long value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Long value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Long> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Long> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Long value1, Long value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Long value1, Long value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Long value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Long value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Long value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Long value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Long value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Long> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Long> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Long value1, Long value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Long value1, Long value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andDiscoverCodeLikeInsensitive(String value) {
            addCriterion("upper(discover_code) like", value.toUpperCase(), "discoverCode");
            return (Criteria) this;
        }

        public Criteria andDiscoverVersionLikeInsensitive(String value) {
            addCriterion("upper(discover_version) like", value.toUpperCase(), "discoverVersion");
            return (Criteria) this;
        }

        public Criteria andLanguageLikeInsensitive(String value) {
            addCriterion("upper(language) like", value.toUpperCase(), "language");
            return (Criteria) this;
        }

        public Criteria andMarkLikeInsensitive(String value) {
            addCriterion("upper(mark) like", value.toUpperCase(), "mark");
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