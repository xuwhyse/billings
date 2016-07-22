package com.billings.billingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class UserDatumBeanCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public UserDatumBeanCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
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

        public Criteria andDatumIdIsNull() {
            addCriterion("datum_id is null");
            return (Criteria) this;
        }

        public Criteria andDatumIdIsNotNull() {
            addCriterion("datum_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatumIdEqualTo(String value) {
            addCriterion("datum_id =", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdNotEqualTo(String value) {
            addCriterion("datum_id <>", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdGreaterThan(String value) {
            addCriterion("datum_id >", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdGreaterThanOrEqualTo(String value) {
            addCriterion("datum_id >=", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdLessThan(String value) {
            addCriterion("datum_id <", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdLessThanOrEqualTo(String value) {
            addCriterion("datum_id <=", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdLike(String value) {
            addCriterion("datum_id like", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdNotLike(String value) {
            addCriterion("datum_id not like", value, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdIn(List<String> values) {
            addCriterion("datum_id in", values, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdNotIn(List<String> values) {
            addCriterion("datum_id not in", values, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdBetween(String value1, String value2) {
            addCriterion("datum_id between", value1, value2, "datumId");
            return (Criteria) this;
        }

        public Criteria andDatumIdNotBetween(String value1, String value2) {
            addCriterion("datum_id not between", value1, value2, "datumId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumIsNull() {
            addCriterion("cur_score_num is null");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumIsNotNull() {
            addCriterion("cur_score_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumEqualTo(Integer value) {
            addCriterion("cur_score_num =", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumNotEqualTo(Integer value) {
            addCriterion("cur_score_num <>", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumGreaterThan(Integer value) {
            addCriterion("cur_score_num >", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("cur_score_num >=", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumLessThan(Integer value) {
            addCriterion("cur_score_num <", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumLessThanOrEqualTo(Integer value) {
            addCriterion("cur_score_num <=", value, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumIn(List<Integer> values) {
            addCriterion("cur_score_num in", values, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumNotIn(List<Integer> values) {
            addCriterion("cur_score_num not in", values, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumBetween(Integer value1, Integer value2) {
            addCriterion("cur_score_num between", value1, value2, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurScoreNumNotBetween(Integer value1, Integer value2) {
            addCriterion("cur_score_num not between", value1, value2, "curScoreNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumIsNull() {
            addCriterion("cur_good_luck_coin_num is null");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumIsNotNull() {
            addCriterion("cur_good_luck_coin_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumEqualTo(Integer value) {
            addCriterion("cur_good_luck_coin_num =", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumNotEqualTo(Integer value) {
            addCriterion("cur_good_luck_coin_num <>", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumGreaterThan(Integer value) {
            addCriterion("cur_good_luck_coin_num >", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("cur_good_luck_coin_num >=", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumLessThan(Integer value) {
            addCriterion("cur_good_luck_coin_num <", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumLessThanOrEqualTo(Integer value) {
            addCriterion("cur_good_luck_coin_num <=", value, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumIn(List<Integer> values) {
            addCriterion("cur_good_luck_coin_num in", values, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumNotIn(List<Integer> values) {
            addCriterion("cur_good_luck_coin_num not in", values, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumBetween(Integer value1, Integer value2) {
            addCriterion("cur_good_luck_coin_num between", value1, value2, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andCurGoodLuckCoinNumNotBetween(Integer value1, Integer value2) {
            addCriterion("cur_good_luck_coin_num not between", value1, value2, "curGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumIsNull() {
            addCriterion("ret_score_num is null");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumIsNotNull() {
            addCriterion("ret_score_num is not null");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumEqualTo(Integer value) {
            addCriterion("ret_score_num =", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumNotEqualTo(Integer value) {
            addCriterion("ret_score_num <>", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumGreaterThan(Integer value) {
            addCriterion("ret_score_num >", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ret_score_num >=", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumLessThan(Integer value) {
            addCriterion("ret_score_num <", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumLessThanOrEqualTo(Integer value) {
            addCriterion("ret_score_num <=", value, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumIn(List<Integer> values) {
            addCriterion("ret_score_num in", values, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumNotIn(List<Integer> values) {
            addCriterion("ret_score_num not in", values, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumBetween(Integer value1, Integer value2) {
            addCriterion("ret_score_num between", value1, value2, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetScoreNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ret_score_num not between", value1, value2, "retScoreNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumIsNull() {
            addCriterion("ret_good_luck_coin_num is null");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumIsNotNull() {
            addCriterion("ret_good_luck_coin_num is not null");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumEqualTo(Integer value) {
            addCriterion("ret_good_luck_coin_num =", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumNotEqualTo(Integer value) {
            addCriterion("ret_good_luck_coin_num <>", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumGreaterThan(Integer value) {
            addCriterion("ret_good_luck_coin_num >", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ret_good_luck_coin_num >=", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumLessThan(Integer value) {
            addCriterion("ret_good_luck_coin_num <", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumLessThanOrEqualTo(Integer value) {
            addCriterion("ret_good_luck_coin_num <=", value, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumIn(List<Integer> values) {
            addCriterion("ret_good_luck_coin_num in", values, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumNotIn(List<Integer> values) {
            addCriterion("ret_good_luck_coin_num not in", values, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumBetween(Integer value1, Integer value2) {
            addCriterion("ret_good_luck_coin_num between", value1, value2, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andRetGoodLuckCoinNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ret_good_luck_coin_num not between", value1, value2, "retGoodLuckCoinNum");
            return (Criteria) this;
        }

        public Criteria andDatumIdLikeInsensitive(String value) {
            addCriterion("upper(datum_id) like", value.toUpperCase(), "datumId");
            return (Criteria) this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(user_id) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_user_datum
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_user_datum
     *
     * @mbggenerated
     */
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