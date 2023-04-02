package com.xzcoder.kaoshi.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamStMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamStMainExample() {
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

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(Integer value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(Integer value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(Integer value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(Integer value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(Integer value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<Integer> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<Integer> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(Integer value1, Integer value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
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

        public Criteria andStTypeIdIsNull() {
            addCriterion("st_type_id is null");
            return (Criteria) this;
        }

        public Criteria andStTypeIdIsNotNull() {
            addCriterion("st_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andStTypeIdEqualTo(Integer value) {
            addCriterion("st_type_id =", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdNotEqualTo(Integer value) {
            addCriterion("st_type_id <>", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdGreaterThan(Integer value) {
            addCriterion("st_type_id >", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_type_id >=", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdLessThan(Integer value) {
            addCriterion("st_type_id <", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("st_type_id <=", value, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdIn(List<Integer> values) {
            addCriterion("st_type_id in", values, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdNotIn(List<Integer> values) {
            addCriterion("st_type_id not in", values, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("st_type_id between", value1, value2, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("st_type_id not between", value1, value2, "stTypeId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdIsNull() {
            addCriterion("st_classify_id is null");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdIsNotNull() {
            addCriterion("st_classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdEqualTo(Integer value) {
            addCriterion("st_classify_id =", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdNotEqualTo(Integer value) {
            addCriterion("st_classify_id <>", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdGreaterThan(Integer value) {
            addCriterion("st_classify_id >", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_classify_id >=", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdLessThan(Integer value) {
            addCriterion("st_classify_id <", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("st_classify_id <=", value, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdIn(List<Integer> values) {
            addCriterion("st_classify_id in", values, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdNotIn(List<Integer> values) {
            addCriterion("st_classify_id not in", values, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("st_classify_id between", value1, value2, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("st_classify_id not between", value1, value2, "stClassifyId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdIsNull() {
            addCriterion("st_level_id is null");
            return (Criteria) this;
        }

        public Criteria andStLevelIdIsNotNull() {
            addCriterion("st_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andStLevelIdEqualTo(Integer value) {
            addCriterion("st_level_id =", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdNotEqualTo(Integer value) {
            addCriterion("st_level_id <>", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdGreaterThan(Integer value) {
            addCriterion("st_level_id >", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_level_id >=", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdLessThan(Integer value) {
            addCriterion("st_level_id <", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("st_level_id <=", value, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdIn(List<Integer> values) {
            addCriterion("st_level_id in", values, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdNotIn(List<Integer> values) {
            addCriterion("st_level_id not in", values, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("st_level_id between", value1, value2, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("st_level_id not between", value1, value2, "stLevelId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdIsNull() {
            addCriterion("st_knowledge_id is null");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdIsNotNull() {
            addCriterion("st_knowledge_id is not null");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdEqualTo(Integer value) {
            addCriterion("st_knowledge_id =", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdNotEqualTo(Integer value) {
            addCriterion("st_knowledge_id <>", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdGreaterThan(Integer value) {
            addCriterion("st_knowledge_id >", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("st_knowledge_id >=", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdLessThan(Integer value) {
            addCriterion("st_knowledge_id <", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdLessThanOrEqualTo(Integer value) {
            addCriterion("st_knowledge_id <=", value, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdIn(List<Integer> values) {
            addCriterion("st_knowledge_id in", values, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdNotIn(List<Integer> values) {
            addCriterion("st_knowledge_id not in", values, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdBetween(Integer value1, Integer value2) {
            addCriterion("st_knowledge_id between", value1, value2, "stKnowledgeId");
            return (Criteria) this;
        }

        public Criteria andStKnowledgeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("st_knowledge_id not between", value1, value2, "stKnowledgeId");
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
