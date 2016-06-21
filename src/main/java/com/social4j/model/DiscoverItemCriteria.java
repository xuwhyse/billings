package com.social4j.model;

import java.util.ArrayList;
import java.util.List;

public class DiscoverItemCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscoverItemCriteria() {
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

        public Criteria andDiscoverItemIdIsNull() {
            addCriterion("discover_item_id is null");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdIsNotNull() {
            addCriterion("discover_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdEqualTo(Integer value) {
            addCriterion("discover_item_id =", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdNotEqualTo(Integer value) {
            addCriterion("discover_item_id <>", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdGreaterThan(Integer value) {
            addCriterion("discover_item_id >", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("discover_item_id >=", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdLessThan(Integer value) {
            addCriterion("discover_item_id <", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("discover_item_id <=", value, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdIn(List<Integer> values) {
            addCriterion("discover_item_id in", values, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdNotIn(List<Integer> values) {
            addCriterion("discover_item_id not in", values, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdBetween(Integer value1, Integer value2) {
            addCriterion("discover_item_id between", value1, value2, "discoverItemId");
            return (Criteria) this;
        }

        public Criteria andDiscoverItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("discover_item_id not between", value1, value2, "discoverItemId");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andDescribedIsNull() {
            addCriterion("described is null");
            return (Criteria) this;
        }

        public Criteria andDescribedIsNotNull() {
            addCriterion("described is not null");
            return (Criteria) this;
        }

        public Criteria andDescribedEqualTo(String value) {
            addCriterion("described =", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedNotEqualTo(String value) {
            addCriterion("described <>", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedGreaterThan(String value) {
            addCriterion("described >", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedGreaterThanOrEqualTo(String value) {
            addCriterion("described >=", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedLessThan(String value) {
            addCriterion("described <", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedLessThanOrEqualTo(String value) {
            addCriterion("described <=", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedLike(String value) {
            addCriterion("described like", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedNotLike(String value) {
            addCriterion("described not like", value, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedIn(List<String> values) {
            addCriterion("described in", values, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedNotIn(List<String> values) {
            addCriterion("described not in", values, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedBetween(String value1, String value2) {
            addCriterion("described between", value1, value2, "described");
            return (Criteria) this;
        }

        public Criteria andDescribedNotBetween(String value1, String value2) {
            addCriterion("described not between", value1, value2, "described");
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

        public Criteria andLinkIsNull() {
            addCriterion("link is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("link is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("link =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("link <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("link >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("link >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("link <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("link <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("link like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("link not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("link in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("link not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("link between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("link not between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
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

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andImageLikeInsensitive(String value) {
            addCriterion("upper(image) like", value.toUpperCase(), "image");
            return (Criteria) this;
        }

        public Criteria andDescribedLikeInsensitive(String value) {
            addCriterion("upper(described) like", value.toUpperCase(), "described");
            return (Criteria) this;
        }

        public Criteria andLinkLikeInsensitive(String value) {
            addCriterion("upper(link) like", value.toUpperCase(), "link");
            return (Criteria) this;
        }

        public Criteria andRegionLikeInsensitive(String value) {
            addCriterion("upper(region) like", value.toUpperCase(), "region");
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