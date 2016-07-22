package com.billings.billingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class WeekSignBeanCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WeekSignBeanCriteria() {
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

        public Criteria andUseridIsNull() {
            addCriterion("UserId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("UserId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("UserId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("UserId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("UserId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("UserId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("UserId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("UserId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("UserId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("UserId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("UserId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("UserId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andMonIsNull() {
            addCriterion("Mon is null");
            return (Criteria) this;
        }

        public Criteria andMonIsNotNull() {
            addCriterion("Mon is not null");
            return (Criteria) this;
        }

        public Criteria andMonEqualTo(Integer value) {
            addCriterion("Mon =", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonNotEqualTo(Integer value) {
            addCriterion("Mon <>", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonGreaterThan(Integer value) {
            addCriterion("Mon >", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonGreaterThanOrEqualTo(Integer value) {
            addCriterion("Mon >=", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonLessThan(Integer value) {
            addCriterion("Mon <", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonLessThanOrEqualTo(Integer value) {
            addCriterion("Mon <=", value, "mon");
            return (Criteria) this;
        }

        public Criteria andMonIn(List<Integer> values) {
            addCriterion("Mon in", values, "mon");
            return (Criteria) this;
        }

        public Criteria andMonNotIn(List<Integer> values) {
            addCriterion("Mon not in", values, "mon");
            return (Criteria) this;
        }

        public Criteria andMonBetween(Integer value1, Integer value2) {
            addCriterion("Mon between", value1, value2, "mon");
            return (Criteria) this;
        }

        public Criteria andMonNotBetween(Integer value1, Integer value2) {
            addCriterion("Mon not between", value1, value2, "mon");
            return (Criteria) this;
        }

        public Criteria andStatemonIsNull() {
            addCriterion("StateMon is null");
            return (Criteria) this;
        }

        public Criteria andStatemonIsNotNull() {
            addCriterion("StateMon is not null");
            return (Criteria) this;
        }

        public Criteria andStatemonEqualTo(Short value) {
            addCriterion("StateMon =", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonNotEqualTo(Short value) {
            addCriterion("StateMon <>", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonGreaterThan(Short value) {
            addCriterion("StateMon >", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonGreaterThanOrEqualTo(Short value) {
            addCriterion("StateMon >=", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonLessThan(Short value) {
            addCriterion("StateMon <", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonLessThanOrEqualTo(Short value) {
            addCriterion("StateMon <=", value, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonIn(List<Short> values) {
            addCriterion("StateMon in", values, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonNotIn(List<Short> values) {
            addCriterion("StateMon not in", values, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonBetween(Short value1, Short value2) {
            addCriterion("StateMon between", value1, value2, "statemon");
            return (Criteria) this;
        }

        public Criteria andStatemonNotBetween(Short value1, Short value2) {
            addCriterion("StateMon not between", value1, value2, "statemon");
            return (Criteria) this;
        }

        public Criteria andTueIsNull() {
            addCriterion("Tue is null");
            return (Criteria) this;
        }

        public Criteria andTueIsNotNull() {
            addCriterion("Tue is not null");
            return (Criteria) this;
        }

        public Criteria andTueEqualTo(Integer value) {
            addCriterion("Tue =", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueNotEqualTo(Integer value) {
            addCriterion("Tue <>", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueGreaterThan(Integer value) {
            addCriterion("Tue >", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueGreaterThanOrEqualTo(Integer value) {
            addCriterion("Tue >=", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueLessThan(Integer value) {
            addCriterion("Tue <", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueLessThanOrEqualTo(Integer value) {
            addCriterion("Tue <=", value, "tue");
            return (Criteria) this;
        }

        public Criteria andTueIn(List<Integer> values) {
            addCriterion("Tue in", values, "tue");
            return (Criteria) this;
        }

        public Criteria andTueNotIn(List<Integer> values) {
            addCriterion("Tue not in", values, "tue");
            return (Criteria) this;
        }

        public Criteria andTueBetween(Integer value1, Integer value2) {
            addCriterion("Tue between", value1, value2, "tue");
            return (Criteria) this;
        }

        public Criteria andTueNotBetween(Integer value1, Integer value2) {
            addCriterion("Tue not between", value1, value2, "tue");
            return (Criteria) this;
        }

        public Criteria andStatetueIsNull() {
            addCriterion("StateTue is null");
            return (Criteria) this;
        }

        public Criteria andStatetueIsNotNull() {
            addCriterion("StateTue is not null");
            return (Criteria) this;
        }

        public Criteria andStatetueEqualTo(Short value) {
            addCriterion("StateTue =", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueNotEqualTo(Short value) {
            addCriterion("StateTue <>", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueGreaterThan(Short value) {
            addCriterion("StateTue >", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueGreaterThanOrEqualTo(Short value) {
            addCriterion("StateTue >=", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueLessThan(Short value) {
            addCriterion("StateTue <", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueLessThanOrEqualTo(Short value) {
            addCriterion("StateTue <=", value, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueIn(List<Short> values) {
            addCriterion("StateTue in", values, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueNotIn(List<Short> values) {
            addCriterion("StateTue not in", values, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueBetween(Short value1, Short value2) {
            addCriterion("StateTue between", value1, value2, "statetue");
            return (Criteria) this;
        }

        public Criteria andStatetueNotBetween(Short value1, Short value2) {
            addCriterion("StateTue not between", value1, value2, "statetue");
            return (Criteria) this;
        }

        public Criteria andWedIsNull() {
            addCriterion("Wed is null");
            return (Criteria) this;
        }

        public Criteria andWedIsNotNull() {
            addCriterion("Wed is not null");
            return (Criteria) this;
        }

        public Criteria andWedEqualTo(Integer value) {
            addCriterion("Wed =", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedNotEqualTo(Integer value) {
            addCriterion("Wed <>", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedGreaterThan(Integer value) {
            addCriterion("Wed >", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedGreaterThanOrEqualTo(Integer value) {
            addCriterion("Wed >=", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedLessThan(Integer value) {
            addCriterion("Wed <", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedLessThanOrEqualTo(Integer value) {
            addCriterion("Wed <=", value, "wed");
            return (Criteria) this;
        }

        public Criteria andWedIn(List<Integer> values) {
            addCriterion("Wed in", values, "wed");
            return (Criteria) this;
        }

        public Criteria andWedNotIn(List<Integer> values) {
            addCriterion("Wed not in", values, "wed");
            return (Criteria) this;
        }

        public Criteria andWedBetween(Integer value1, Integer value2) {
            addCriterion("Wed between", value1, value2, "wed");
            return (Criteria) this;
        }

        public Criteria andWedNotBetween(Integer value1, Integer value2) {
            addCriterion("Wed not between", value1, value2, "wed");
            return (Criteria) this;
        }

        public Criteria andStatewedIsNull() {
            addCriterion("StateWed is null");
            return (Criteria) this;
        }

        public Criteria andStatewedIsNotNull() {
            addCriterion("StateWed is not null");
            return (Criteria) this;
        }

        public Criteria andStatewedEqualTo(Short value) {
            addCriterion("StateWed =", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedNotEqualTo(Short value) {
            addCriterion("StateWed <>", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedGreaterThan(Short value) {
            addCriterion("StateWed >", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedGreaterThanOrEqualTo(Short value) {
            addCriterion("StateWed >=", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedLessThan(Short value) {
            addCriterion("StateWed <", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedLessThanOrEqualTo(Short value) {
            addCriterion("StateWed <=", value, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedIn(List<Short> values) {
            addCriterion("StateWed in", values, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedNotIn(List<Short> values) {
            addCriterion("StateWed not in", values, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedBetween(Short value1, Short value2) {
            addCriterion("StateWed between", value1, value2, "statewed");
            return (Criteria) this;
        }

        public Criteria andStatewedNotBetween(Short value1, Short value2) {
            addCriterion("StateWed not between", value1, value2, "statewed");
            return (Criteria) this;
        }

        public Criteria andThurIsNull() {
            addCriterion("Thur is null");
            return (Criteria) this;
        }

        public Criteria andThurIsNotNull() {
            addCriterion("Thur is not null");
            return (Criteria) this;
        }

        public Criteria andThurEqualTo(Integer value) {
            addCriterion("Thur =", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurNotEqualTo(Integer value) {
            addCriterion("Thur <>", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurGreaterThan(Integer value) {
            addCriterion("Thur >", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurGreaterThanOrEqualTo(Integer value) {
            addCriterion("Thur >=", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurLessThan(Integer value) {
            addCriterion("Thur <", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurLessThanOrEqualTo(Integer value) {
            addCriterion("Thur <=", value, "thur");
            return (Criteria) this;
        }

        public Criteria andThurIn(List<Integer> values) {
            addCriterion("Thur in", values, "thur");
            return (Criteria) this;
        }

        public Criteria andThurNotIn(List<Integer> values) {
            addCriterion("Thur not in", values, "thur");
            return (Criteria) this;
        }

        public Criteria andThurBetween(Integer value1, Integer value2) {
            addCriterion("Thur between", value1, value2, "thur");
            return (Criteria) this;
        }

        public Criteria andThurNotBetween(Integer value1, Integer value2) {
            addCriterion("Thur not between", value1, value2, "thur");
            return (Criteria) this;
        }

        public Criteria andStatethurIsNull() {
            addCriterion("StateThur is null");
            return (Criteria) this;
        }

        public Criteria andStatethurIsNotNull() {
            addCriterion("StateThur is not null");
            return (Criteria) this;
        }

        public Criteria andStatethurEqualTo(Short value) {
            addCriterion("StateThur =", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurNotEqualTo(Short value) {
            addCriterion("StateThur <>", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurGreaterThan(Short value) {
            addCriterion("StateThur >", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurGreaterThanOrEqualTo(Short value) {
            addCriterion("StateThur >=", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurLessThan(Short value) {
            addCriterion("StateThur <", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurLessThanOrEqualTo(Short value) {
            addCriterion("StateThur <=", value, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurIn(List<Short> values) {
            addCriterion("StateThur in", values, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurNotIn(List<Short> values) {
            addCriterion("StateThur not in", values, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurBetween(Short value1, Short value2) {
            addCriterion("StateThur between", value1, value2, "statethur");
            return (Criteria) this;
        }

        public Criteria andStatethurNotBetween(Short value1, Short value2) {
            addCriterion("StateThur not between", value1, value2, "statethur");
            return (Criteria) this;
        }

        public Criteria andFriIsNull() {
            addCriterion("Fri is null");
            return (Criteria) this;
        }

        public Criteria andFriIsNotNull() {
            addCriterion("Fri is not null");
            return (Criteria) this;
        }

        public Criteria andFriEqualTo(Integer value) {
            addCriterion("Fri =", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriNotEqualTo(Integer value) {
            addCriterion("Fri <>", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriGreaterThan(Integer value) {
            addCriterion("Fri >", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriGreaterThanOrEqualTo(Integer value) {
            addCriterion("Fri >=", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriLessThan(Integer value) {
            addCriterion("Fri <", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriLessThanOrEqualTo(Integer value) {
            addCriterion("Fri <=", value, "fri");
            return (Criteria) this;
        }

        public Criteria andFriIn(List<Integer> values) {
            addCriterion("Fri in", values, "fri");
            return (Criteria) this;
        }

        public Criteria andFriNotIn(List<Integer> values) {
            addCriterion("Fri not in", values, "fri");
            return (Criteria) this;
        }

        public Criteria andFriBetween(Integer value1, Integer value2) {
            addCriterion("Fri between", value1, value2, "fri");
            return (Criteria) this;
        }

        public Criteria andFriNotBetween(Integer value1, Integer value2) {
            addCriterion("Fri not between", value1, value2, "fri");
            return (Criteria) this;
        }

        public Criteria andStatefriIsNull() {
            addCriterion("StateFri is null");
            return (Criteria) this;
        }

        public Criteria andStatefriIsNotNull() {
            addCriterion("StateFri is not null");
            return (Criteria) this;
        }

        public Criteria andStatefriEqualTo(Short value) {
            addCriterion("StateFri =", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriNotEqualTo(Short value) {
            addCriterion("StateFri <>", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriGreaterThan(Short value) {
            addCriterion("StateFri >", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriGreaterThanOrEqualTo(Short value) {
            addCriterion("StateFri >=", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriLessThan(Short value) {
            addCriterion("StateFri <", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriLessThanOrEqualTo(Short value) {
            addCriterion("StateFri <=", value, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriIn(List<Short> values) {
            addCriterion("StateFri in", values, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriNotIn(List<Short> values) {
            addCriterion("StateFri not in", values, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriBetween(Short value1, Short value2) {
            addCriterion("StateFri between", value1, value2, "statefri");
            return (Criteria) this;
        }

        public Criteria andStatefriNotBetween(Short value1, Short value2) {
            addCriterion("StateFri not between", value1, value2, "statefri");
            return (Criteria) this;
        }

        public Criteria andSatIsNull() {
            addCriterion("Sat is null");
            return (Criteria) this;
        }

        public Criteria andSatIsNotNull() {
            addCriterion("Sat is not null");
            return (Criteria) this;
        }

        public Criteria andSatEqualTo(Integer value) {
            addCriterion("Sat =", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotEqualTo(Integer value) {
            addCriterion("Sat <>", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatGreaterThan(Integer value) {
            addCriterion("Sat >", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sat >=", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatLessThan(Integer value) {
            addCriterion("Sat <", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatLessThanOrEqualTo(Integer value) {
            addCriterion("Sat <=", value, "sat");
            return (Criteria) this;
        }

        public Criteria andSatIn(List<Integer> values) {
            addCriterion("Sat in", values, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotIn(List<Integer> values) {
            addCriterion("Sat not in", values, "sat");
            return (Criteria) this;
        }

        public Criteria andSatBetween(Integer value1, Integer value2) {
            addCriterion("Sat between", value1, value2, "sat");
            return (Criteria) this;
        }

        public Criteria andSatNotBetween(Integer value1, Integer value2) {
            addCriterion("Sat not between", value1, value2, "sat");
            return (Criteria) this;
        }

        public Criteria andStatesatIsNull() {
            addCriterion("StateSat is null");
            return (Criteria) this;
        }

        public Criteria andStatesatIsNotNull() {
            addCriterion("StateSat is not null");
            return (Criteria) this;
        }

        public Criteria andStatesatEqualTo(Short value) {
            addCriterion("StateSat =", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatNotEqualTo(Short value) {
            addCriterion("StateSat <>", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatGreaterThan(Short value) {
            addCriterion("StateSat >", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatGreaterThanOrEqualTo(Short value) {
            addCriterion("StateSat >=", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatLessThan(Short value) {
            addCriterion("StateSat <", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatLessThanOrEqualTo(Short value) {
            addCriterion("StateSat <=", value, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatIn(List<Short> values) {
            addCriterion("StateSat in", values, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatNotIn(List<Short> values) {
            addCriterion("StateSat not in", values, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatBetween(Short value1, Short value2) {
            addCriterion("StateSat between", value1, value2, "statesat");
            return (Criteria) this;
        }

        public Criteria andStatesatNotBetween(Short value1, Short value2) {
            addCriterion("StateSat not between", value1, value2, "statesat");
            return (Criteria) this;
        }

        public Criteria andSunIsNull() {
            addCriterion("Sun is null");
            return (Criteria) this;
        }

        public Criteria andSunIsNotNull() {
            addCriterion("Sun is not null");
            return (Criteria) this;
        }

        public Criteria andSunEqualTo(Integer value) {
            addCriterion("Sun =", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunNotEqualTo(Integer value) {
            addCriterion("Sun <>", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunGreaterThan(Integer value) {
            addCriterion("Sun >", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunGreaterThanOrEqualTo(Integer value) {
            addCriterion("Sun >=", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunLessThan(Integer value) {
            addCriterion("Sun <", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunLessThanOrEqualTo(Integer value) {
            addCriterion("Sun <=", value, "sun");
            return (Criteria) this;
        }

        public Criteria andSunIn(List<Integer> values) {
            addCriterion("Sun in", values, "sun");
            return (Criteria) this;
        }

        public Criteria andSunNotIn(List<Integer> values) {
            addCriterion("Sun not in", values, "sun");
            return (Criteria) this;
        }

        public Criteria andSunBetween(Integer value1, Integer value2) {
            addCriterion("Sun between", value1, value2, "sun");
            return (Criteria) this;
        }

        public Criteria andSunNotBetween(Integer value1, Integer value2) {
            addCriterion("Sun not between", value1, value2, "sun");
            return (Criteria) this;
        }

        public Criteria andStatesunIsNull() {
            addCriterion("StateSun is null");
            return (Criteria) this;
        }

        public Criteria andStatesunIsNotNull() {
            addCriterion("StateSun is not null");
            return (Criteria) this;
        }

        public Criteria andStatesunEqualTo(Short value) {
            addCriterion("StateSun =", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunNotEqualTo(Short value) {
            addCriterion("StateSun <>", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunGreaterThan(Short value) {
            addCriterion("StateSun >", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunGreaterThanOrEqualTo(Short value) {
            addCriterion("StateSun >=", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunLessThan(Short value) {
            addCriterion("StateSun <", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunLessThanOrEqualTo(Short value) {
            addCriterion("StateSun <=", value, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunIn(List<Short> values) {
            addCriterion("StateSun in", values, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunNotIn(List<Short> values) {
            addCriterion("StateSun not in", values, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunBetween(Short value1, Short value2) {
            addCriterion("StateSun between", value1, value2, "statesun");
            return (Criteria) this;
        }

        public Criteria andStatesunNotBetween(Short value1, Short value2) {
            addCriterion("StateSun not between", value1, value2, "statesun");
            return (Criteria) this;
        }

        public Criteria andPrizecodeIsNull() {
            addCriterion("PrizeCode is null");
            return (Criteria) this;
        }

        public Criteria andPrizecodeIsNotNull() {
            addCriterion("PrizeCode is not null");
            return (Criteria) this;
        }

        public Criteria andPrizecodeEqualTo(String value) {
            addCriterion("PrizeCode =", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeNotEqualTo(String value) {
            addCriterion("PrizeCode <>", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeGreaterThan(String value) {
            addCriterion("PrizeCode >", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeGreaterThanOrEqualTo(String value) {
            addCriterion("PrizeCode >=", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeLessThan(String value) {
            addCriterion("PrizeCode <", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeLessThanOrEqualTo(String value) {
            addCriterion("PrizeCode <=", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeLike(String value) {
            addCriterion("PrizeCode like", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeNotLike(String value) {
            addCriterion("PrizeCode not like", value, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeIn(List<String> values) {
            addCriterion("PrizeCode in", values, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeNotIn(List<String> values) {
            addCriterion("PrizeCode not in", values, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeBetween(String value1, String value2) {
            addCriterion("PrizeCode between", value1, value2, "prizecode");
            return (Criteria) this;
        }

        public Criteria andPrizecodeNotBetween(String value1, String value2) {
            addCriterion("PrizeCode not between", value1, value2, "prizecode");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumIsNull() {
            addCriterion("RetroactiveNum is null");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumIsNotNull() {
            addCriterion("RetroactiveNum is not null");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumEqualTo(Integer value) {
            addCriterion("RetroactiveNum =", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumNotEqualTo(Integer value) {
            addCriterion("RetroactiveNum <>", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumGreaterThan(Integer value) {
            addCriterion("RetroactiveNum >", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("RetroactiveNum >=", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumLessThan(Integer value) {
            addCriterion("RetroactiveNum <", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumLessThanOrEqualTo(Integer value) {
            addCriterion("RetroactiveNum <=", value, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumIn(List<Integer> values) {
            addCriterion("RetroactiveNum in", values, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumNotIn(List<Integer> values) {
            addCriterion("RetroactiveNum not in", values, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumBetween(Integer value1, Integer value2) {
            addCriterion("RetroactiveNum between", value1, value2, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivenumNotBetween(Integer value1, Integer value2) {
            addCriterion("RetroactiveNum not between", value1, value2, "retroactivenum");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeIsNull() {
            addCriterion("RetroactiveTime is null");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeIsNotNull() {
            addCriterion("RetroactiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeEqualTo(Integer value) {
            addCriterion("RetroactiveTime =", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeNotEqualTo(Integer value) {
            addCriterion("RetroactiveTime <>", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeGreaterThan(Integer value) {
            addCriterion("RetroactiveTime >", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RetroactiveTime >=", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeLessThan(Integer value) {
            addCriterion("RetroactiveTime <", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeLessThanOrEqualTo(Integer value) {
            addCriterion("RetroactiveTime <=", value, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeIn(List<Integer> values) {
            addCriterion("RetroactiveTime in", values, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeNotIn(List<Integer> values) {
            addCriterion("RetroactiveTime not in", values, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeBetween(Integer value1, Integer value2) {
            addCriterion("RetroactiveTime between", value1, value2, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andRetroactivetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("RetroactiveTime not between", value1, value2, "retroactivetime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeIsNull() {
            addCriterion("Drawtime is null");
            return (Criteria) this;
        }

        public Criteria andDrawtimeIsNotNull() {
            addCriterion("Drawtime is not null");
            return (Criteria) this;
        }

        public Criteria andDrawtimeEqualTo(Long value) {
            addCriterion("Drawtime =", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeNotEqualTo(Long value) {
            addCriterion("Drawtime <>", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeGreaterThan(Long value) {
            addCriterion("Drawtime >", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("Drawtime >=", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeLessThan(Long value) {
            addCriterion("Drawtime <", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeLessThanOrEqualTo(Long value) {
            addCriterion("Drawtime <=", value, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeIn(List<Long> values) {
            addCriterion("Drawtime in", values, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeNotIn(List<Long> values) {
            addCriterion("Drawtime not in", values, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeBetween(Long value1, Long value2) {
            addCriterion("Drawtime between", value1, value2, "drawtime");
            return (Criteria) this;
        }

        public Criteria andDrawtimeNotBetween(Long value1, Long value2) {
            addCriterion("Drawtime not between", value1, value2, "drawtime");
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

        public Criteria andPrizecodeLikeInsensitive(String value) {
            addCriterion("upper(PrizeCode) like", value.toUpperCase(), "prizecode");
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