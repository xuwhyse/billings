package com.hundsun.internet.billingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class BaoyueBeanCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaoyueBeanCriteria() {
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

        public Criteria andPackageidIsNull() {
            addCriterion("PackageId is null");
            return (Criteria) this;
        }

        public Criteria andPackageidIsNotNull() {
            addCriterion("PackageId is not null");
            return (Criteria) this;
        }

        public Criteria andPackageidEqualTo(Long value) {
            addCriterion("PackageId =", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotEqualTo(Long value) {
            addCriterion("PackageId <>", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidGreaterThan(Long value) {
            addCriterion("PackageId >", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidGreaterThanOrEqualTo(Long value) {
            addCriterion("PackageId >=", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidLessThan(Long value) {
            addCriterion("PackageId <", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidLessThanOrEqualTo(Long value) {
            addCriterion("PackageId <=", value, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidIn(List<Long> values) {
            addCriterion("PackageId in", values, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotIn(List<Long> values) {
            addCriterion("PackageId not in", values, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidBetween(Long value1, Long value2) {
            addCriterion("PackageId between", value1, value2, "packageid");
            return (Criteria) this;
        }

        public Criteria andPackageidNotBetween(Long value1, Long value2) {
            addCriterion("PackageId not between", value1, value2, "packageid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidIsNull() {
            addCriterion("SourceUuid is null");
            return (Criteria) this;
        }

        public Criteria andSourceuuidIsNotNull() {
            addCriterion("SourceUuid is not null");
            return (Criteria) this;
        }

        public Criteria andSourceuuidEqualTo(String value) {
            addCriterion("SourceUuid =", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidNotEqualTo(String value) {
            addCriterion("SourceUuid <>", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidGreaterThan(String value) {
            addCriterion("SourceUuid >", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidGreaterThanOrEqualTo(String value) {
            addCriterion("SourceUuid >=", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidLessThan(String value) {
            addCriterion("SourceUuid <", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidLessThanOrEqualTo(String value) {
            addCriterion("SourceUuid <=", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidLike(String value) {
            addCriterion("SourceUuid like", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidNotLike(String value) {
            addCriterion("SourceUuid not like", value, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidIn(List<String> values) {
            addCriterion("SourceUuid in", values, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidNotIn(List<String> values) {
            addCriterion("SourceUuid not in", values, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidBetween(String value1, String value2) {
            addCriterion("SourceUuid between", value1, value2, "sourceuuid");
            return (Criteria) this;
        }

        public Criteria andSourceuuidNotBetween(String value1, String value2) {
            addCriterion("SourceUuid not between", value1, value2, "sourceuuid");
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

        public Criteria andRemovetimeIsNull() {
            addCriterion("RemoveTime is null");
            return (Criteria) this;
        }

        public Criteria andRemovetimeIsNotNull() {
            addCriterion("RemoveTime is not null");
            return (Criteria) this;
        }

        public Criteria andRemovetimeEqualTo(Long value) {
            addCriterion("RemoveTime =", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeNotEqualTo(Long value) {
            addCriterion("RemoveTime <>", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeGreaterThan(Long value) {
            addCriterion("RemoveTime >", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("RemoveTime >=", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeLessThan(Long value) {
            addCriterion("RemoveTime <", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeLessThanOrEqualTo(Long value) {
            addCriterion("RemoveTime <=", value, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeIn(List<Long> values) {
            addCriterion("RemoveTime in", values, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeNotIn(List<Long> values) {
            addCriterion("RemoveTime not in", values, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeBetween(Long value1, Long value2) {
            addCriterion("RemoveTime between", value1, value2, "removetime");
            return (Criteria) this;
        }

        public Criteria andRemovetimeNotBetween(Long value1, Long value2) {
            addCriterion("RemoveTime not between", value1, value2, "removetime");
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

        public Criteria andSortIsNull() {
            addCriterion("Sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("Sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("Sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("Sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("Sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("Sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("Sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("Sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("Sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("Sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("Sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNull() {
            addCriterion("Finished is null");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNotNull() {
            addCriterion("Finished is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualTo(Byte value) {
            addCriterion("Finished =", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Byte value) {
            addCriterion("Finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Byte value) {
            addCriterion("Finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Byte value) {
            addCriterion("Finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Byte value) {
            addCriterion("Finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Byte value) {
            addCriterion("Finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedIn(List<Byte> values) {
            addCriterion("Finished in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotIn(List<Byte> values) {
            addCriterion("Finished not in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedBetween(Byte value1, Byte value2) {
            addCriterion("Finished between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotBetween(Byte value1, Byte value2) {
            addCriterion("Finished not between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeIsNull() {
            addCriterion("latestPublishTime is null");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeIsNotNull() {
            addCriterion("latestPublishTime is not null");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeEqualTo(Long value) {
            addCriterion("latestPublishTime =", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeNotEqualTo(Long value) {
            addCriterion("latestPublishTime <>", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeGreaterThan(Long value) {
            addCriterion("latestPublishTime >", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("latestPublishTime >=", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeLessThan(Long value) {
            addCriterion("latestPublishTime <", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeLessThanOrEqualTo(Long value) {
            addCriterion("latestPublishTime <=", value, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeIn(List<Long> values) {
            addCriterion("latestPublishTime in", values, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeNotIn(List<Long> values) {
            addCriterion("latestPublishTime not in", values, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeBetween(Long value1, Long value2) {
            addCriterion("latestPublishTime between", value1, value2, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andLatestpublishtimeNotBetween(Long value1, Long value2) {
            addCriterion("latestPublishTime not between", value1, value2, "latestpublishtime");
            return (Criteria) this;
        }

        public Criteria andSourceuuidLikeInsensitive(String value) {
            addCriterion("upper(SourceUuid) like", value.toUpperCase(), "sourceuuid");
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