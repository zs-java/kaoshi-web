package com.xzcoder.kaoshi.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamKsUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamKsUserExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andKsIdIsNull() {
            addCriterion("ks_id is null");
            return (Criteria) this;
        }

        public Criteria andKsIdIsNotNull() {
            addCriterion("ks_id is not null");
            return (Criteria) this;
        }

        public Criteria andKsIdEqualTo(Integer value) {
            addCriterion("ks_id =", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdNotEqualTo(Integer value) {
            addCriterion("ks_id <>", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdGreaterThan(Integer value) {
            addCriterion("ks_id >", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ks_id >=", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdLessThan(Integer value) {
            addCriterion("ks_id <", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdLessThanOrEqualTo(Integer value) {
            addCriterion("ks_id <=", value, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdIn(List<Integer> values) {
            addCriterion("ks_id in", values, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdNotIn(List<Integer> values) {
            addCriterion("ks_id not in", values, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdBetween(Integer value1, Integer value2) {
            addCriterion("ks_id between", value1, value2, "ksId");
            return (Criteria) this;
        }

        public Criteria andKsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ks_id not between", value1, value2, "ksId");
            return (Criteria) this;
        }

        public Criteria andSjIdIsNull() {
            addCriterion("sj_id is null");
            return (Criteria) this;
        }

        public Criteria andSjIdIsNotNull() {
            addCriterion("sj_id is not null");
            return (Criteria) this;
        }

        public Criteria andSjIdEqualTo(Integer value) {
            addCriterion("sj_id =", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdNotEqualTo(Integer value) {
            addCriterion("sj_id <>", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdGreaterThan(Integer value) {
            addCriterion("sj_id >", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sj_id >=", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdLessThan(Integer value) {
            addCriterion("sj_id <", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdLessThanOrEqualTo(Integer value) {
            addCriterion("sj_id <=", value, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdIn(List<Integer> values) {
            addCriterion("sj_id in", values, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdNotIn(List<Integer> values) {
            addCriterion("sj_id not in", values, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdBetween(Integer value1, Integer value2) {
            addCriterion("sj_id between", value1, value2, "sjId");
            return (Criteria) this;
        }

        public Criteria andSjIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sj_id not between", value1, value2, "sjId");
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSignupTimeIsNull() {
            addCriterion("signup_time is null");
            return (Criteria) this;
        }

        public Criteria andSignupTimeIsNotNull() {
            addCriterion("signup_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignupTimeEqualTo(Date value) {
            addCriterion("signup_time =", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeNotEqualTo(Date value) {
            addCriterion("signup_time <>", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeGreaterThan(Date value) {
            addCriterion("signup_time >", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("signup_time >=", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeLessThan(Date value) {
            addCriterion("signup_time <", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeLessThanOrEqualTo(Date value) {
            addCriterion("signup_time <=", value, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeIn(List<Date> values) {
            addCriterion("signup_time in", values, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeNotIn(List<Date> values) {
            addCriterion("signup_time not in", values, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeBetween(Date value1, Date value2) {
            addCriterion("signup_time between", value1, value2, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupTimeNotBetween(Date value1, Date value2) {
            addCriterion("signup_time not between", value1, value2, "signupTime");
            return (Criteria) this;
        }

        public Criteria andSignupStateIsNull() {
            addCriterion("signup_state is null");
            return (Criteria) this;
        }

        public Criteria andSignupStateIsNotNull() {
            addCriterion("signup_state is not null");
            return (Criteria) this;
        }

        public Criteria andSignupStateEqualTo(Integer value) {
            addCriterion("signup_state =", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateNotEqualTo(Integer value) {
            addCriterion("signup_state <>", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateGreaterThan(Integer value) {
            addCriterion("signup_state >", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("signup_state >=", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateLessThan(Integer value) {
            addCriterion("signup_state <", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateLessThanOrEqualTo(Integer value) {
            addCriterion("signup_state <=", value, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateIn(List<Integer> values) {
            addCriterion("signup_state in", values, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateNotIn(List<Integer> values) {
            addCriterion("signup_state not in", values, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateBetween(Integer value1, Integer value2) {
            addCriterion("signup_state between", value1, value2, "signupState");
            return (Criteria) this;
        }

        public Criteria andSignupStateNotBetween(Integer value1, Integer value2) {
            addCriterion("signup_state not between", value1, value2, "signupState");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNull() {
            addCriterion("review_time is null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNotNull() {
            addCriterion("review_time is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeEqualTo(Date value) {
            addCriterion("review_time =", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotEqualTo(Date value) {
            addCriterion("review_time <>", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThan(Date value) {
            addCriterion("review_time >", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("review_time >=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThan(Date value) {
            addCriterion("review_time <", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("review_time <=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIn(List<Date> values) {
            addCriterion("review_time in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotIn(List<Date> values) {
            addCriterion("review_time not in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeBetween(Date value1, Date value2) {
            addCriterion("review_time between", value1, value2, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("review_time not between", value1, value2, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
            return (Criteria) this;
        }

        public Criteria andTimesEqualTo(Integer value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotEqualTo(Integer value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThan(Integer value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThan(Integer value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThanOrEqualTo(Integer value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesIn(List<Integer> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotIn(List<Integer> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesBetween(Integer value1, Integer value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("times not between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<BigDecimal> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<BigDecimal> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andReadFlgIsNull() {
            addCriterion("read_flg is null");
            return (Criteria) this;
        }

        public Criteria andReadFlgIsNotNull() {
            addCriterion("read_flg is not null");
            return (Criteria) this;
        }

        public Criteria andReadFlgEqualTo(Boolean value) {
            addCriterion("read_flg =", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgNotEqualTo(Boolean value) {
            addCriterion("read_flg <>", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgGreaterThan(Boolean value) {
            addCriterion("read_flg >", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("read_flg >=", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgLessThan(Boolean value) {
            addCriterion("read_flg <", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("read_flg <=", value, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgIn(List<Boolean> values) {
            addCriterion("read_flg in", values, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgNotIn(List<Boolean> values) {
            addCriterion("read_flg not in", values, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("read_flg between", value1, value2, "readFlg");
            return (Criteria) this;
        }

        public Criteria andReadFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("read_flg not between", value1, value2, "readFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgIsNull() {
            addCriterion("wrong_flg is null");
            return (Criteria) this;
        }

        public Criteria andWrongFlgIsNotNull() {
            addCriterion("wrong_flg is not null");
            return (Criteria) this;
        }

        public Criteria andWrongFlgEqualTo(Boolean value) {
            addCriterion("wrong_flg =", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgNotEqualTo(Boolean value) {
            addCriterion("wrong_flg <>", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgGreaterThan(Boolean value) {
            addCriterion("wrong_flg >", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("wrong_flg >=", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgLessThan(Boolean value) {
            addCriterion("wrong_flg <", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("wrong_flg <=", value, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgIn(List<Boolean> values) {
            addCriterion("wrong_flg in", values, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgNotIn(List<Boolean> values) {
            addCriterion("wrong_flg not in", values, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("wrong_flg between", value1, value2, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andWrongFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("wrong_flg not between", value1, value2, "wrongFlg");
            return (Criteria) this;
        }

        public Criteria andDelIsNull() {
            addCriterion("del is null");
            return (Criteria) this;
        }

        public Criteria andDelIsNotNull() {
            addCriterion("del is not null");
            return (Criteria) this;
        }

        public Criteria andDelEqualTo(Boolean value) {
            addCriterion("del =", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotEqualTo(Boolean value) {
            addCriterion("del <>", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThan(Boolean value) {
            addCriterion("del >", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del >=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThan(Boolean value) {
            addCriterion("del <", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThanOrEqualTo(Boolean value) {
            addCriterion("del <=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelIn(List<Boolean> values) {
            addCriterion("del in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotIn(List<Boolean> values) {
            addCriterion("del not in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelBetween(Boolean value1, Boolean value2) {
            addCriterion("del between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del not between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andInsUserIsNull() {
            addCriterion("ins_user is null");
            return (Criteria) this;
        }

        public Criteria andInsUserIsNotNull() {
            addCriterion("ins_user is not null");
            return (Criteria) this;
        }

        public Criteria andInsUserEqualTo(String value) {
            addCriterion("ins_user =", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserNotEqualTo(String value) {
            addCriterion("ins_user <>", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserGreaterThan(String value) {
            addCriterion("ins_user >", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserGreaterThanOrEqualTo(String value) {
            addCriterion("ins_user >=", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserLessThan(String value) {
            addCriterion("ins_user <", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserLessThanOrEqualTo(String value) {
            addCriterion("ins_user <=", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserLike(String value) {
            addCriterion("ins_user like", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserNotLike(String value) {
            addCriterion("ins_user not like", value, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserIn(List<String> values) {
            addCriterion("ins_user in", values, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserNotIn(List<String> values) {
            addCriterion("ins_user not in", values, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserBetween(String value1, String value2) {
            addCriterion("ins_user between", value1, value2, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsUserNotBetween(String value1, String value2) {
            addCriterion("ins_user not between", value1, value2, "insUser");
            return (Criteria) this;
        }

        public Criteria andInsDateIsNull() {
            addCriterion("ins_date is null");
            return (Criteria) this;
        }

        public Criteria andInsDateIsNotNull() {
            addCriterion("ins_date is not null");
            return (Criteria) this;
        }

        public Criteria andInsDateEqualTo(Date value) {
            addCriterion("ins_date =", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotEqualTo(Date value) {
            addCriterion("ins_date <>", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateGreaterThan(Date value) {
            addCriterion("ins_date >", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ins_date >=", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateLessThan(Date value) {
            addCriterion("ins_date <", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateLessThanOrEqualTo(Date value) {
            addCriterion("ins_date <=", value, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateIn(List<Date> values) {
            addCriterion("ins_date in", values, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotIn(List<Date> values) {
            addCriterion("ins_date not in", values, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateBetween(Date value1, Date value2) {
            addCriterion("ins_date between", value1, value2, "insDate");
            return (Criteria) this;
        }

        public Criteria andInsDateNotBetween(Date value1, Date value2) {
            addCriterion("ins_date not between", value1, value2, "insDate");
            return (Criteria) this;
        }

        public Criteria andUpdUserIsNull() {
            addCriterion("upd_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdUserIsNotNull() {
            addCriterion("upd_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdUserEqualTo(String value) {
            addCriterion("upd_user =", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserNotEqualTo(String value) {
            addCriterion("upd_user <>", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserGreaterThan(String value) {
            addCriterion("upd_user >", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserGreaterThanOrEqualTo(String value) {
            addCriterion("upd_user >=", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserLessThan(String value) {
            addCriterion("upd_user <", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserLessThanOrEqualTo(String value) {
            addCriterion("upd_user <=", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserLike(String value) {
            addCriterion("upd_user like", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserNotLike(String value) {
            addCriterion("upd_user not like", value, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserIn(List<String> values) {
            addCriterion("upd_user in", values, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserNotIn(List<String> values) {
            addCriterion("upd_user not in", values, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserBetween(String value1, String value2) {
            addCriterion("upd_user between", value1, value2, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdUserNotBetween(String value1, String value2) {
            addCriterion("upd_user not between", value1, value2, "updUser");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNull() {
            addCriterion("upd_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdDateIsNotNull() {
            addCriterion("upd_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdDateEqualTo(Date value) {
            addCriterion("upd_date =", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotEqualTo(Date value) {
            addCriterion("upd_date <>", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThan(Date value) {
            addCriterion("upd_date >", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateGreaterThanOrEqualTo(Date value) {
            addCriterion("upd_date >=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThan(Date value) {
            addCriterion("upd_date <", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateLessThanOrEqualTo(Date value) {
            addCriterion("upd_date <=", value, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateIn(List<Date> values) {
            addCriterion("upd_date in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotIn(List<Date> values) {
            addCriterion("upd_date not in", values, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateBetween(Date value1, Date value2) {
            addCriterion("upd_date between", value1, value2, "updDate");
            return (Criteria) this;
        }

        public Criteria andUpdDateNotBetween(Date value1, Date value2) {
            addCriterion("upd_date not between", value1, value2, "updDate");
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
