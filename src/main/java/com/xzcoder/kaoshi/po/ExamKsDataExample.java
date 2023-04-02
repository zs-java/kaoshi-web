package com.xzcoder.kaoshi.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamKsDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamKsDataExample() {
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

        public Criteria andKsClassifyIdIsNull() {
            addCriterion("ks_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdIsNotNull() {
            addCriterion("ks_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdEqualTo(Integer value) {
            addCriterion("ks_classify_id =", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdNotEqualTo(Integer value) {
            addCriterion("ks_classify_id <>", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdGreaterThan(Integer value) {
            addCriterion("ks_classify_id >", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ks_classify_id >=", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdLessThan(Integer value) {
            addCriterion("ks_classify_id <", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("ks_classify_id <=", value, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdIn(List<Integer> values) {
            addCriterion("ks_classify_id in", values, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdNotIn(List<Integer> values) {
            addCriterion("ks_classify_id not in", values, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("ks_classify_id between", value1, value2, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andKsClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ks_classify_id not between", value1, value2, "ksClassifyId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNull() {
            addCriterion("pass_score is null");
            return (Criteria) this;
        }

        public Criteria andPassScoreIsNotNull() {
            addCriterion("pass_score is not null");
            return (Criteria) this;
        }

        public Criteria andPassScoreEqualTo(Integer value) {
            addCriterion("pass_score =", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotEqualTo(Integer value) {
            addCriterion("pass_score <>", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThan(Integer value) {
            addCriterion("pass_score >", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("pass_score >=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThan(Integer value) {
            addCriterion("pass_score <", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreLessThanOrEqualTo(Integer value) {
            addCriterion("pass_score <=", value, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreIn(List<Integer> values) {
            addCriterion("pass_score in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotIn(List<Integer> values) {
            addCriterion("pass_score not in", values, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreBetween(Integer value1, Integer value2) {
            addCriterion("pass_score between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andPassScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("pass_score not between", value1, value2, "passScore");
            return (Criteria) this;
        }

        public Criteria andMaxTimesIsNull() {
            addCriterion("max_times is null");
            return (Criteria) this;
        }

        public Criteria andMaxTimesIsNotNull() {
            addCriterion("max_times is not null");
            return (Criteria) this;
        }

        public Criteria andMaxTimesEqualTo(Integer value) {
            addCriterion("max_times =", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesNotEqualTo(Integer value) {
            addCriterion("max_times <>", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesGreaterThan(Integer value) {
            addCriterion("max_times >", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_times >=", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesLessThan(Integer value) {
            addCriterion("max_times <", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesLessThanOrEqualTo(Integer value) {
            addCriterion("max_times <=", value, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesIn(List<Integer> values) {
            addCriterion("max_times in", values, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesNotIn(List<Integer> values) {
            addCriterion("max_times not in", values, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesBetween(Integer value1, Integer value2) {
            addCriterion("max_times between", value1, value2, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andMaxTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("max_times not between", value1, value2, "maxTimes");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNull() {
            addCriterion("page_size is null");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNotNull() {
            addCriterion("page_size is not null");
            return (Criteria) this;
        }

        public Criteria andPageSizeEqualTo(Boolean value) {
            addCriterion("page_size =", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotEqualTo(Boolean value) {
            addCriterion("page_size <>", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThan(Boolean value) {
            addCriterion("page_size >", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("page_size >=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThan(Boolean value) {
            addCriterion("page_size <", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThanOrEqualTo(Boolean value) {
            addCriterion("page_size <=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeIn(List<Boolean> values) {
            addCriterion("page_size in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotIn(List<Boolean> values) {
            addCriterion("page_size not in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeBetween(Boolean value1, Boolean value2) {
            addCriterion("page_size between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("page_size not between", value1, value2, "pageSize");
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

        public Criteria andTotalTimeIsNull() {
            addCriterion("total_time is null");
            return (Criteria) this;
        }

        public Criteria andTotalTimeIsNotNull() {
            addCriterion("total_time is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTimeEqualTo(Integer value) {
            addCriterion("total_time =", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotEqualTo(Integer value) {
            addCriterion("total_time <>", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeGreaterThan(Integer value) {
            addCriterion("total_time >", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_time >=", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeLessThan(Integer value) {
            addCriterion("total_time <", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeLessThanOrEqualTo(Integer value) {
            addCriterion("total_time <=", value, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeIn(List<Integer> values) {
            addCriterion("total_time in", values, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotIn(List<Integer> values) {
            addCriterion("total_time not in", values, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeBetween(Integer value1, Integer value2) {
            addCriterion("total_time between", value1, value2, "totalTime");
            return (Criteria) this;
        }

        public Criteria andTotalTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("total_time not between", value1, value2, "totalTime");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgIsNull() {
            addCriterion("user_signup_flg is null");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgIsNotNull() {
            addCriterion("user_signup_flg is not null");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgEqualTo(Boolean value) {
            addCriterion("user_signup_flg =", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgNotEqualTo(Boolean value) {
            addCriterion("user_signup_flg <>", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgGreaterThan(Boolean value) {
            addCriterion("user_signup_flg >", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("user_signup_flg >=", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgLessThan(Boolean value) {
            addCriterion("user_signup_flg <", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("user_signup_flg <=", value, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgIn(List<Boolean> values) {
            addCriterion("user_signup_flg in", values, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgNotIn(List<Boolean> values) {
            addCriterion("user_signup_flg not in", values, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("user_signup_flg between", value1, value2, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andUserSignupFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("user_signup_flg not between", value1, value2, "userSignupFlg");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeIsNull() {
            addCriterion("signup_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeIsNotNull() {
            addCriterion("signup_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeEqualTo(Date value) {
            addCriterion("signup_begin_time =", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeNotEqualTo(Date value) {
            addCriterion("signup_begin_time <>", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeGreaterThan(Date value) {
            addCriterion("signup_begin_time >", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("signup_begin_time >=", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeLessThan(Date value) {
            addCriterion("signup_begin_time <", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("signup_begin_time <=", value, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeIn(List<Date> values) {
            addCriterion("signup_begin_time in", values, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeNotIn(List<Date> values) {
            addCriterion("signup_begin_time not in", values, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeBetween(Date value1, Date value2) {
            addCriterion("signup_begin_time between", value1, value2, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("signup_begin_time not between", value1, value2, "signupBeginTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeIsNull() {
            addCriterion("signup_end_time is null");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeIsNotNull() {
            addCriterion("signup_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeEqualTo(Date value) {
            addCriterion("signup_end_time =", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeNotEqualTo(Date value) {
            addCriterion("signup_end_time <>", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeGreaterThan(Date value) {
            addCriterion("signup_end_time >", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("signup_end_time >=", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeLessThan(Date value) {
            addCriterion("signup_end_time <", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("signup_end_time <=", value, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeIn(List<Date> values) {
            addCriterion("signup_end_time in", values, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeNotIn(List<Date> values) {
            addCriterion("signup_end_time not in", values, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeBetween(Date value1, Date value2) {
            addCriterion("signup_end_time between", value1, value2, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andSignupEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("signup_end_time not between", value1, value2, "signupEndTime");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgIsNull() {
            addCriterion("passing_again_flg is null");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgIsNotNull() {
            addCriterion("passing_again_flg is not null");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgEqualTo(Boolean value) {
            addCriterion("passing_again_flg =", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgNotEqualTo(Boolean value) {
            addCriterion("passing_again_flg <>", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgGreaterThan(Boolean value) {
            addCriterion("passing_again_flg >", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("passing_again_flg >=", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgLessThan(Boolean value) {
            addCriterion("passing_again_flg <", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("passing_again_flg <=", value, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgIn(List<Boolean> values) {
            addCriterion("passing_again_flg in", values, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgNotIn(List<Boolean> values) {
            addCriterion("passing_again_flg not in", values, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("passing_again_flg between", value1, value2, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andPassingAgainFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("passing_again_flg not between", value1, value2, "passingAgainFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgIsNull() {
            addCriterion("qsn_random_flg is null");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgIsNotNull() {
            addCriterion("qsn_random_flg is not null");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgEqualTo(Boolean value) {
            addCriterion("qsn_random_flg =", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgNotEqualTo(Boolean value) {
            addCriterion("qsn_random_flg <>", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgGreaterThan(Boolean value) {
            addCriterion("qsn_random_flg >", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("qsn_random_flg >=", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgLessThan(Boolean value) {
            addCriterion("qsn_random_flg <", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("qsn_random_flg <=", value, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgIn(List<Boolean> values) {
            addCriterion("qsn_random_flg in", values, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgNotIn(List<Boolean> values) {
            addCriterion("qsn_random_flg not in", values, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("qsn_random_flg between", value1, value2, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andQsnRandomFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("qsn_random_flg not between", value1, value2, "qsnRandomFlg");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIsNull() {
            addCriterion("time_type is null");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIsNotNull() {
            addCriterion("time_type is not null");
            return (Criteria) this;
        }

        public Criteria andTimeTypeEqualTo(Boolean value) {
            addCriterion("time_type =", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotEqualTo(Boolean value) {
            addCriterion("time_type <>", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeGreaterThan(Boolean value) {
            addCriterion("time_type >", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("time_type >=", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeLessThan(Boolean value) {
            addCriterion("time_type <", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("time_type <=", value, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeIn(List<Boolean> values) {
            addCriterion("time_type in", values, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotIn(List<Boolean> values) {
            addCriterion("time_type not in", values, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("time_type between", value1, value2, "timeType");
            return (Criteria) this;
        }

        public Criteria andTimeTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("time_type not between", value1, value2, "timeType");
            return (Criteria) this;
        }

        public Criteria andDisableTimeIsNull() {
            addCriterion("disable_time is null");
            return (Criteria) this;
        }

        public Criteria andDisableTimeIsNotNull() {
            addCriterion("disable_time is not null");
            return (Criteria) this;
        }

        public Criteria andDisableTimeEqualTo(Integer value) {
            addCriterion("disable_time =", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeNotEqualTo(Integer value) {
            addCriterion("disable_time <>", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeGreaterThan(Integer value) {
            addCriterion("disable_time >", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("disable_time >=", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeLessThan(Integer value) {
            addCriterion("disable_time <", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeLessThanOrEqualTo(Integer value) {
            addCriterion("disable_time <=", value, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeIn(List<Integer> values) {
            addCriterion("disable_time in", values, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeNotIn(List<Integer> values) {
            addCriterion("disable_time not in", values, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeBetween(Integer value1, Integer value2) {
            addCriterion("disable_time between", value1, value2, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("disable_time not between", value1, value2, "disableTime");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitIsNull() {
            addCriterion("disable_submit is null");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitIsNotNull() {
            addCriterion("disable_submit is not null");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitEqualTo(Integer value) {
            addCriterion("disable_submit =", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitNotEqualTo(Integer value) {
            addCriterion("disable_submit <>", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitGreaterThan(Integer value) {
            addCriterion("disable_submit >", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitGreaterThanOrEqualTo(Integer value) {
            addCriterion("disable_submit >=", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitLessThan(Integer value) {
            addCriterion("disable_submit <", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitLessThanOrEqualTo(Integer value) {
            addCriterion("disable_submit <=", value, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitIn(List<Integer> values) {
            addCriterion("disable_submit in", values, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitNotIn(List<Integer> values) {
            addCriterion("disable_submit not in", values, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitBetween(Integer value1, Integer value2) {
            addCriterion("disable_submit between", value1, value2, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andDisableSubmitNotBetween(Integer value1, Integer value2) {
            addCriterion("disable_submit not between", value1, value2, "disableSubmit");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeIsNull() {
            addCriterion("result_publish_time is null");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeIsNotNull() {
            addCriterion("result_publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeEqualTo(Date value) {
            addCriterion("result_publish_time =", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeNotEqualTo(Date value) {
            addCriterion("result_publish_time <>", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeGreaterThan(Date value) {
            addCriterion("result_publish_time >", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("result_publish_time >=", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeLessThan(Date value) {
            addCriterion("result_publish_time <", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("result_publish_time <=", value, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeIn(List<Date> values) {
            addCriterion("result_publish_time in", values, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeNotIn(List<Date> values) {
            addCriterion("result_publish_time not in", values, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeBetween(Date value1, Date value2) {
            addCriterion("result_publish_time between", value1, value2, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andResultPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("result_publish_time not between", value1, value2, "resultPublishTime");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgIsNull() {
            addCriterion("publish_rightkey_flg is null");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgIsNotNull() {
            addCriterion("publish_rightkey_flg is not null");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgEqualTo(Boolean value) {
            addCriterion("publish_rightkey_flg =", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgNotEqualTo(Boolean value) {
            addCriterion("publish_rightkey_flg <>", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgGreaterThan(Boolean value) {
            addCriterion("publish_rightkey_flg >", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("publish_rightkey_flg >=", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgLessThan(Boolean value) {
            addCriterion("publish_rightkey_flg <", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("publish_rightkey_flg <=", value, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgIn(List<Boolean> values) {
            addCriterion("publish_rightkey_flg in", values, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgNotIn(List<Boolean> values) {
            addCriterion("publish_rightkey_flg not in", values, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("publish_rightkey_flg between", value1, value2, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andPublishRightkeyFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("publish_rightkey_flg not between", value1, value2, "publishRightkeyFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgIsNull() {
            addCriterion("result_rank_flg is null");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgIsNotNull() {
            addCriterion("result_rank_flg is not null");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgEqualTo(Boolean value) {
            addCriterion("result_rank_flg =", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgNotEqualTo(Boolean value) {
            addCriterion("result_rank_flg <>", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgGreaterThan(Boolean value) {
            addCriterion("result_rank_flg >", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("result_rank_flg >=", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgLessThan(Boolean value) {
            addCriterion("result_rank_flg <", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("result_rank_flg <=", value, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgIn(List<Boolean> values) {
            addCriterion("result_rank_flg in", values, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgNotIn(List<Boolean> values) {
            addCriterion("result_rank_flg not in", values, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("result_rank_flg between", value1, value2, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andResultRankFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("result_rank_flg not between", value1, value2, "resultRankFlg");
            return (Criteria) this;
        }

        public Criteria andReviewIsNull() {
            addCriterion("review is null");
            return (Criteria) this;
        }

        public Criteria andReviewIsNotNull() {
            addCriterion("review is not null");
            return (Criteria) this;
        }

        public Criteria andReviewEqualTo(Boolean value) {
            addCriterion("review =", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotEqualTo(Boolean value) {
            addCriterion("review <>", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThan(Boolean value) {
            addCriterion("review >", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("review >=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThan(Boolean value) {
            addCriterion("review <", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThanOrEqualTo(Boolean value) {
            addCriterion("review <=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewIn(List<Boolean> values) {
            addCriterion("review in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotIn(List<Boolean> values) {
            addCriterion("review not in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewBetween(Boolean value1, Boolean value2) {
            addCriterion("review between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("review not between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
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

        public Criteria andSelectFlgIsNull() {
            addCriterion("select_flg is null");
            return (Criteria) this;
        }

        public Criteria andSelectFlgIsNotNull() {
            addCriterion("select_flg is not null");
            return (Criteria) this;
        }

        public Criteria andSelectFlgEqualTo(Byte value) {
            addCriterion("select_flg =", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgNotEqualTo(Byte value) {
            addCriterion("select_flg <>", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgGreaterThan(Byte value) {
            addCriterion("select_flg >", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgGreaterThanOrEqualTo(Byte value) {
            addCriterion("select_flg >=", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgLessThan(Byte value) {
            addCriterion("select_flg <", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgLessThanOrEqualTo(Byte value) {
            addCriterion("select_flg <=", value, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgIn(List<Byte> values) {
            addCriterion("select_flg in", values, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgNotIn(List<Byte> values) {
            addCriterion("select_flg not in", values, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgBetween(Byte value1, Byte value2) {
            addCriterion("select_flg between", value1, value2, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andSelectFlgNotBetween(Byte value1, Byte value2) {
            addCriterion("select_flg not between", value1, value2, "selectFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgIsNull() {
            addCriterion("option_random_flg is null");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgIsNotNull() {
            addCriterion("option_random_flg is not null");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgEqualTo(Boolean value) {
            addCriterion("option_random_flg =", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgNotEqualTo(Boolean value) {
            addCriterion("option_random_flg <>", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgGreaterThan(Boolean value) {
            addCriterion("option_random_flg >", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("option_random_flg >=", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgLessThan(Boolean value) {
            addCriterion("option_random_flg <", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgLessThanOrEqualTo(Boolean value) {
            addCriterion("option_random_flg <=", value, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgIn(List<Boolean> values) {
            addCriterion("option_random_flg in", values, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgNotIn(List<Boolean> values) {
            addCriterion("option_random_flg not in", values, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgBetween(Boolean value1, Boolean value2) {
            addCriterion("option_random_flg between", value1, value2, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andOptionRandomFlgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("option_random_flg not between", value1, value2, "optionRandomFlg");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Integer value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Integer value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Integer value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Integer value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Integer value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Integer> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Integer> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Integer value1, Integer value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("credit not between", value1, value2, "credit");
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
