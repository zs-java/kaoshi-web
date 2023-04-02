package com.xzcoder.kaoshi.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamSjDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamSjDataExample() {
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

        public Criteria andSjClassifyIdIsNull() {
            addCriterion("sj_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdIsNotNull() {
            addCriterion("sj_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdEqualTo(Integer value) {
            addCriterion("sj_classify_id =", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdNotEqualTo(Integer value) {
            addCriterion("sj_classify_id <>", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdGreaterThan(Integer value) {
            addCriterion("sj_classify_id >", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sj_classify_id >=", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdLessThan(Integer value) {
            addCriterion("sj_classify_id <", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("sj_classify_id <=", value, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdIn(List<Integer> values) {
            addCriterion("sj_classify_id in", values, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdNotIn(List<Integer> values) {
            addCriterion("sj_classify_id not in", values, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("sj_classify_id between", value1, value2, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andSjClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sj_classify_id not between", value1, value2, "sjClassifyId");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("des =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("des <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("des >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("des >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("des <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("des <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("des not between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(BigDecimal value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(BigDecimal value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(BigDecimal value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(BigDecimal value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<BigDecimal> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<BigDecimal> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("COUNT =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("COUNT <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("COUNT >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUNT >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("COUNT <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("COUNT <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("COUNT in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("COUNT not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("COUNT between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("COUNT not between", value1, value2, "count");
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
