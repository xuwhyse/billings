package com.hundsun.internet.billingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class BaoyuePPBeanCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaoyuePPBeanCriteria() {
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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNull() {
            addCriterion("CreateBy is null");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNotNull() {
            addCriterion("CreateBy is not null");
            return (Criteria) this;
        }

        public Criteria andCreatebyEqualTo(Byte value) {
            addCriterion("CreateBy =", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotEqualTo(Byte value) {
            addCriterion("CreateBy <>", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThan(Byte value) {
            addCriterion("CreateBy >", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThanOrEqualTo(Byte value) {
            addCriterion("CreateBy >=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThan(Byte value) {
            addCriterion("CreateBy <", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThanOrEqualTo(Byte value) {
            addCriterion("CreateBy <=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyIn(List<Byte> values) {
            addCriterion("CreateBy in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotIn(List<Byte> values) {
            addCriterion("CreateBy not in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyBetween(Byte value1, Byte value2) {
            addCriterion("CreateBy between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotBetween(Byte value1, Byte value2) {
            addCriterion("CreateBy not between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendIsNull() {
            addCriterion("EditorRecommend is null");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendIsNotNull() {
            addCriterion("EditorRecommend is not null");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendEqualTo(String value) {
            addCriterion("EditorRecommend =", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendNotEqualTo(String value) {
            addCriterion("EditorRecommend <>", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendGreaterThan(String value) {
            addCriterion("EditorRecommend >", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendGreaterThanOrEqualTo(String value) {
            addCriterion("EditorRecommend >=", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendLessThan(String value) {
            addCriterion("EditorRecommend <", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendLessThanOrEqualTo(String value) {
            addCriterion("EditorRecommend <=", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendLike(String value) {
            addCriterion("EditorRecommend like", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendNotLike(String value) {
            addCriterion("EditorRecommend not like", value, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendIn(List<String> values) {
            addCriterion("EditorRecommend in", values, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendNotIn(List<String> values) {
            addCriterion("EditorRecommend not in", values, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendBetween(String value1, String value2) {
            addCriterion("EditorRecommend between", value1, value2, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendNotBetween(String value1, String value2) {
            addCriterion("EditorRecommend not between", value1, value2, "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNull() {
            addCriterion("BookNum is null");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNotNull() {
            addCriterion("BookNum is not null");
            return (Criteria) this;
        }

        public Criteria andBooknumEqualTo(Integer value) {
            addCriterion("BookNum =", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotEqualTo(Integer value) {
            addCriterion("BookNum <>", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThan(Integer value) {
            addCriterion("BookNum >", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThanOrEqualTo(Integer value) {
            addCriterion("BookNum >=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThan(Integer value) {
            addCriterion("BookNum <", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThanOrEqualTo(Integer value) {
            addCriterion("BookNum <=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumIn(List<Integer> values) {
            addCriterion("BookNum in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotIn(List<Integer> values) {
            addCriterion("BookNum not in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumBetween(Integer value1, Integer value2) {
            addCriterion("BookNum between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotBetween(Integer value1, Integer value2) {
            addCriterion("BookNum not between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andBuynumIsNull() {
            addCriterion("BuyNum is null");
            return (Criteria) this;
        }

        public Criteria andBuynumIsNotNull() {
            addCriterion("BuyNum is not null");
            return (Criteria) this;
        }

        public Criteria andBuynumEqualTo(Integer value) {
            addCriterion("BuyNum =", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumNotEqualTo(Integer value) {
            addCriterion("BuyNum <>", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumGreaterThan(Integer value) {
            addCriterion("BuyNum >", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumGreaterThanOrEqualTo(Integer value) {
            addCriterion("BuyNum >=", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumLessThan(Integer value) {
            addCriterion("BuyNum <", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumLessThanOrEqualTo(Integer value) {
            addCriterion("BuyNum <=", value, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumIn(List<Integer> values) {
            addCriterion("BuyNum in", values, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumNotIn(List<Integer> values) {
            addCriterion("BuyNum not in", values, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumBetween(Integer value1, Integer value2) {
            addCriterion("BuyNum between", value1, value2, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumNotBetween(Integer value1, Integer value2) {
            addCriterion("BuyNum not between", value1, value2, "buynum");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityIsNull() {
            addCriterion("BuyNumPriority is null");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityIsNotNull() {
            addCriterion("BuyNumPriority is not null");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityEqualTo(Integer value) {
            addCriterion("BuyNumPriority =", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityNotEqualTo(Integer value) {
            addCriterion("BuyNumPriority <>", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityGreaterThan(Integer value) {
            addCriterion("BuyNumPriority >", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("BuyNumPriority >=", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityLessThan(Integer value) {
            addCriterion("BuyNumPriority <", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityLessThanOrEqualTo(Integer value) {
            addCriterion("BuyNumPriority <=", value, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityIn(List<Integer> values) {
            addCriterion("BuyNumPriority in", values, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityNotIn(List<Integer> values) {
            addCriterion("BuyNumPriority not in", values, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityBetween(Integer value1, Integer value2) {
            addCriterion("BuyNumPriority between", value1, value2, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andBuynumpriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("BuyNumPriority not between", value1, value2, "buynumpriority");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Long value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Long value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Long value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Long value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Long value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Long> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Long> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Long value1, Long value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Long value1, Long value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("UpdateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("UpdateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Long value) {
            addCriterion("UpdateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Long value) {
            addCriterion("UpdateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Long value) {
            addCriterion("UpdateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("UpdateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Long value) {
            addCriterion("UpdateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Long value) {
            addCriterion("UpdateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Long> values) {
            addCriterion("UpdateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Long> values) {
            addCriterion("UpdateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Long value1, Long value2) {
            addCriterion("UpdateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Long value1, Long value2) {
            addCriterion("UpdateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCoverIsNull() {
            addCriterion("Cover is null");
            return (Criteria) this;
        }

        public Criteria andCoverIsNotNull() {
            addCriterion("Cover is not null");
            return (Criteria) this;
        }

        public Criteria andCoverEqualTo(String value) {
            addCriterion("Cover =", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotEqualTo(String value) {
            addCriterion("Cover <>", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThan(String value) {
            addCriterion("Cover >", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThanOrEqualTo(String value) {
            addCriterion("Cover >=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThan(String value) {
            addCriterion("Cover <", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThanOrEqualTo(String value) {
            addCriterion("Cover <=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLike(String value) {
            addCriterion("Cover like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotLike(String value) {
            addCriterion("Cover not like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverIn(List<String> values) {
            addCriterion("Cover in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotIn(List<String> values) {
            addCriterion("Cover not in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverBetween(String value1, String value2) {
            addCriterion("Cover between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotBetween(String value1, String value2) {
            addCriterion("Cover not between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andTotalvalueIsNull() {
            addCriterion("totalValue is null");
            return (Criteria) this;
        }

        public Criteria andTotalvalueIsNotNull() {
            addCriterion("totalValue is not null");
            return (Criteria) this;
        }

        public Criteria andTotalvalueEqualTo(Integer value) {
            addCriterion("totalValue =", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueNotEqualTo(Integer value) {
            addCriterion("totalValue <>", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueGreaterThan(Integer value) {
            addCriterion("totalValue >", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalValue >=", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueLessThan(Integer value) {
            addCriterion("totalValue <", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueLessThanOrEqualTo(Integer value) {
            addCriterion("totalValue <=", value, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueIn(List<Integer> values) {
            addCriterion("totalValue in", values, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueNotIn(List<Integer> values) {
            addCriterion("totalValue not in", values, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueBetween(Integer value1, Integer value2) {
            addCriterion("totalValue between", value1, value2, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andTotalvalueNotBetween(Integer value1, Integer value2) {
            addCriterion("totalValue not between", value1, value2, "totalvalue");
            return (Criteria) this;
        }

        public Criteria andOpendescIsNull() {
            addCriterion("OpenDesc is null");
            return (Criteria) this;
        }

        public Criteria andOpendescIsNotNull() {
            addCriterion("OpenDesc is not null");
            return (Criteria) this;
        }

        public Criteria andOpendescEqualTo(String value) {
            addCriterion("OpenDesc =", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescNotEqualTo(String value) {
            addCriterion("OpenDesc <>", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescGreaterThan(String value) {
            addCriterion("OpenDesc >", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescGreaterThanOrEqualTo(String value) {
            addCriterion("OpenDesc >=", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescLessThan(String value) {
            addCriterion("OpenDesc <", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescLessThanOrEqualTo(String value) {
            addCriterion("OpenDesc <=", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescLike(String value) {
            addCriterion("OpenDesc like", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescNotLike(String value) {
            addCriterion("OpenDesc not like", value, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescIn(List<String> values) {
            addCriterion("OpenDesc in", values, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescNotIn(List<String> values) {
            addCriterion("OpenDesc not in", values, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescBetween(String value1, String value2) {
            addCriterion("OpenDesc between", value1, value2, "opendesc");
            return (Criteria) this;
        }

        public Criteria andOpendescNotBetween(String value1, String value2) {
            addCriterion("OpenDesc not between", value1, value2, "opendesc");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("StartTime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("StartTime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(Long value) {
            addCriterion("StartTime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(Long value) {
            addCriterion("StartTime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(Long value) {
            addCriterion("StartTime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(Long value) {
            addCriterion("StartTime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(Long value) {
            addCriterion("StartTime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(Long value) {
            addCriterion("StartTime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<Long> values) {
            addCriterion("StartTime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<Long> values) {
            addCriterion("StartTime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(Long value1, Long value2) {
            addCriterion("StartTime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(Long value1, Long value2) {
            addCriterion("StartTime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("EndTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("EndTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Long value) {
            addCriterion("EndTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Long value) {
            addCriterion("EndTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Long value) {
            addCriterion("EndTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("EndTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Long value) {
            addCriterion("EndTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Long value) {
            addCriterion("EndTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Long> values) {
            addCriterion("EndTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Long> values) {
            addCriterion("EndTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Long value1, Long value2) {
            addCriterion("EndTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Long value1, Long value2) {
            addCriterion("EndTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(Name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andEditorrecommendLikeInsensitive(String value) {
            addCriterion("upper(EditorRecommend) like", value.toUpperCase(), "editorrecommend");
            return (Criteria) this;
        }

        public Criteria andCoverLikeInsensitive(String value) {
            addCriterion("upper(Cover) like", value.toUpperCase(), "cover");
            return (Criteria) this;
        }

        public Criteria andOpendescLikeInsensitive(String value) {
            addCriterion("upper(OpenDesc) like", value.toUpperCase(), "opendesc");
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