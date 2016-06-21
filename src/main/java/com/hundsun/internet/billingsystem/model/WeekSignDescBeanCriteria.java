package com.hundsun.internet.billingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class WeekSignDescBeanCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekSignDescBeanCriteria() {
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

        public Criteria andWeekidIsNull() {
            addCriterion("WeekId is null");
            return (Criteria) this;
        }

        public Criteria andWeekidIsNotNull() {
            addCriterion("WeekId is not null");
            return (Criteria) this;
        }

        public Criteria andWeekidEqualTo(Integer value) {
            addCriterion("WeekId =", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidNotEqualTo(Integer value) {
            addCriterion("WeekId <>", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidGreaterThan(Integer value) {
            addCriterion("WeekId >", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidGreaterThanOrEqualTo(Integer value) {
            addCriterion("WeekId >=", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidLessThan(Integer value) {
            addCriterion("WeekId <", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidLessThanOrEqualTo(Integer value) {
            addCriterion("WeekId <=", value, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidIn(List<Integer> values) {
            addCriterion("WeekId in", values, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidNotIn(List<Integer> values) {
            addCriterion("WeekId not in", values, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidBetween(Integer value1, Integer value2) {
            addCriterion("WeekId between", value1, value2, "weekid");
            return (Criteria) this;
        }

        public Criteria andWeekidNotBetween(Integer value1, Integer value2) {
            addCriterion("WeekId not between", value1, value2, "weekid");
            return (Criteria) this;
        }

        public Criteria andSignnummonIsNull() {
            addCriterion("SignNumMon is null");
            return (Criteria) this;
        }

        public Criteria andSignnummonIsNotNull() {
            addCriterion("SignNumMon is not null");
            return (Criteria) this;
        }

        public Criteria andSignnummonEqualTo(Integer value) {
            addCriterion("SignNumMon =", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonNotEqualTo(Integer value) {
            addCriterion("SignNumMon <>", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonGreaterThan(Integer value) {
            addCriterion("SignNumMon >", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumMon >=", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonLessThan(Integer value) {
            addCriterion("SignNumMon <", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumMon <=", value, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonIn(List<Integer> values) {
            addCriterion("SignNumMon in", values, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonNotIn(List<Integer> values) {
            addCriterion("SignNumMon not in", values, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonBetween(Integer value1, Integer value2) {
            addCriterion("SignNumMon between", value1, value2, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnummonNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumMon not between", value1, value2, "signnummon");
            return (Criteria) this;
        }

        public Criteria andSignnumtueIsNull() {
            addCriterion("SignNumTue is null");
            return (Criteria) this;
        }

        public Criteria andSignnumtueIsNotNull() {
            addCriterion("SignNumTue is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumtueEqualTo(Integer value) {
            addCriterion("SignNumTue =", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueNotEqualTo(Integer value) {
            addCriterion("SignNumTue <>", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueGreaterThan(Integer value) {
            addCriterion("SignNumTue >", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumTue >=", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueLessThan(Integer value) {
            addCriterion("SignNumTue <", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumTue <=", value, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueIn(List<Integer> values) {
            addCriterion("SignNumTue in", values, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueNotIn(List<Integer> values) {
            addCriterion("SignNumTue not in", values, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueBetween(Integer value1, Integer value2) {
            addCriterion("SignNumTue between", value1, value2, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumtueNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumTue not between", value1, value2, "signnumtue");
            return (Criteria) this;
        }

        public Criteria andSignnumwedIsNull() {
            addCriterion("SignNumWed is null");
            return (Criteria) this;
        }

        public Criteria andSignnumwedIsNotNull() {
            addCriterion("SignNumWed is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumwedEqualTo(Integer value) {
            addCriterion("SignNumWed =", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedNotEqualTo(Integer value) {
            addCriterion("SignNumWed <>", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedGreaterThan(Integer value) {
            addCriterion("SignNumWed >", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumWed >=", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedLessThan(Integer value) {
            addCriterion("SignNumWed <", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumWed <=", value, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedIn(List<Integer> values) {
            addCriterion("SignNumWed in", values, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedNotIn(List<Integer> values) {
            addCriterion("SignNumWed not in", values, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedBetween(Integer value1, Integer value2) {
            addCriterion("SignNumWed between", value1, value2, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumwedNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumWed not between", value1, value2, "signnumwed");
            return (Criteria) this;
        }

        public Criteria andSignnumthurIsNull() {
            addCriterion("SignNumThur is null");
            return (Criteria) this;
        }

        public Criteria andSignnumthurIsNotNull() {
            addCriterion("SignNumThur is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumthurEqualTo(Integer value) {
            addCriterion("SignNumThur =", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurNotEqualTo(Integer value) {
            addCriterion("SignNumThur <>", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurGreaterThan(Integer value) {
            addCriterion("SignNumThur >", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumThur >=", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurLessThan(Integer value) {
            addCriterion("SignNumThur <", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumThur <=", value, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurIn(List<Integer> values) {
            addCriterion("SignNumThur in", values, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurNotIn(List<Integer> values) {
            addCriterion("SignNumThur not in", values, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurBetween(Integer value1, Integer value2) {
            addCriterion("SignNumThur between", value1, value2, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumthurNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumThur not between", value1, value2, "signnumthur");
            return (Criteria) this;
        }

        public Criteria andSignnumfriIsNull() {
            addCriterion("SignNumFri is null");
            return (Criteria) this;
        }

        public Criteria andSignnumfriIsNotNull() {
            addCriterion("SignNumFri is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumfriEqualTo(Integer value) {
            addCriterion("SignNumFri =", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriNotEqualTo(Integer value) {
            addCriterion("SignNumFri <>", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriGreaterThan(Integer value) {
            addCriterion("SignNumFri >", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumFri >=", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriLessThan(Integer value) {
            addCriterion("SignNumFri <", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumFri <=", value, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriIn(List<Integer> values) {
            addCriterion("SignNumFri in", values, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriNotIn(List<Integer> values) {
            addCriterion("SignNumFri not in", values, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriBetween(Integer value1, Integer value2) {
            addCriterion("SignNumFri between", value1, value2, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumfriNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumFri not between", value1, value2, "signnumfri");
            return (Criteria) this;
        }

        public Criteria andSignnumsatIsNull() {
            addCriterion("SignNumSat is null");
            return (Criteria) this;
        }

        public Criteria andSignnumsatIsNotNull() {
            addCriterion("SignNumSat is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumsatEqualTo(Integer value) {
            addCriterion("SignNumSat =", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatNotEqualTo(Integer value) {
            addCriterion("SignNumSat <>", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatGreaterThan(Integer value) {
            addCriterion("SignNumSat >", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumSat >=", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatLessThan(Integer value) {
            addCriterion("SignNumSat <", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumSat <=", value, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatIn(List<Integer> values) {
            addCriterion("SignNumSat in", values, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatNotIn(List<Integer> values) {
            addCriterion("SignNumSat not in", values, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatBetween(Integer value1, Integer value2) {
            addCriterion("SignNumSat between", value1, value2, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsatNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumSat not between", value1, value2, "signnumsat");
            return (Criteria) this;
        }

        public Criteria andSignnumsunIsNull() {
            addCriterion("SignNumSun is null");
            return (Criteria) this;
        }

        public Criteria andSignnumsunIsNotNull() {
            addCriterion("SignNumSun is not null");
            return (Criteria) this;
        }

        public Criteria andSignnumsunEqualTo(Integer value) {
            addCriterion("SignNumSun =", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunNotEqualTo(Integer value) {
            addCriterion("SignNumSun <>", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunGreaterThan(Integer value) {
            addCriterion("SignNumSun >", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunGreaterThanOrEqualTo(Integer value) {
            addCriterion("SignNumSun >=", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunLessThan(Integer value) {
            addCriterion("SignNumSun <", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunLessThanOrEqualTo(Integer value) {
            addCriterion("SignNumSun <=", value, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunIn(List<Integer> values) {
            addCriterion("SignNumSun in", values, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunNotIn(List<Integer> values) {
            addCriterion("SignNumSun not in", values, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunBetween(Integer value1, Integer value2) {
            addCriterion("SignNumSun between", value1, value2, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andSignnumsunNotBetween(Integer value1, Integer value2) {
            addCriterion("SignNumSun not between", value1, value2, "signnumsun");
            return (Criteria) this;
        }

        public Criteria andLotterynumIsNull() {
            addCriterion("LotteryNum is null");
            return (Criteria) this;
        }

        public Criteria andLotterynumIsNotNull() {
            addCriterion("LotteryNum is not null");
            return (Criteria) this;
        }

        public Criteria andLotterynumEqualTo(Integer value) {
            addCriterion("LotteryNum =", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotEqualTo(Integer value) {
            addCriterion("LotteryNum <>", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumGreaterThan(Integer value) {
            addCriterion("LotteryNum >", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LotteryNum >=", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumLessThan(Integer value) {
            addCriterion("LotteryNum <", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumLessThanOrEqualTo(Integer value) {
            addCriterion("LotteryNum <=", value, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumIn(List<Integer> values) {
            addCriterion("LotteryNum in", values, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotIn(List<Integer> values) {
            addCriterion("LotteryNum not in", values, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumBetween(Integer value1, Integer value2) {
            addCriterion("LotteryNum between", value1, value2, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterynumNotBetween(Integer value1, Integer value2) {
            addCriterion("LotteryNum not between", value1, value2, "lotterynum");
            return (Criteria) this;
        }

        public Criteria andLotterystateIsNull() {
            addCriterion("LotteryState is null");
            return (Criteria) this;
        }

        public Criteria andLotterystateIsNotNull() {
            addCriterion("LotteryState is not null");
            return (Criteria) this;
        }

        public Criteria andLotterystateEqualTo(Short value) {
            addCriterion("LotteryState =", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateNotEqualTo(Short value) {
            addCriterion("LotteryState <>", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateGreaterThan(Short value) {
            addCriterion("LotteryState >", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateGreaterThanOrEqualTo(Short value) {
            addCriterion("LotteryState >=", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateLessThan(Short value) {
            addCriterion("LotteryState <", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateLessThanOrEqualTo(Short value) {
            addCriterion("LotteryState <=", value, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateIn(List<Short> values) {
            addCriterion("LotteryState in", values, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateNotIn(List<Short> values) {
            addCriterion("LotteryState not in", values, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateBetween(Short value1, Short value2) {
            addCriterion("LotteryState between", value1, value2, "lotterystate");
            return (Criteria) this;
        }

        public Criteria andLotterystateNotBetween(Short value1, Short value2) {
            addCriterion("LotteryState not between", value1, value2, "lotterystate");
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