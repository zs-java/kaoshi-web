package com.xzcoder.kaoshi.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExercisesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExercisesExample() {
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

        public Criteria andDayDateIsNull() {
            addCriterion("day_date is null");
            return (Criteria) this;
        }

        public Criteria andDayDateIsNotNull() {
            addCriterion("day_date is not null");
            return (Criteria) this;
        }

        public Criteria andDayDateEqualTo(Date value) {
            addCriterion("day_date =", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateNotEqualTo(Date value) {
            addCriterion("day_date <>", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateGreaterThan(Date value) {
            addCriterion("day_date >", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("day_date >=", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateLessThan(Date value) {
            addCriterion("day_date <", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateLessThanOrEqualTo(Date value) {
            addCriterion("day_date <=", value, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateIn(List<Date> values) {
            addCriterion("day_date in", values, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateNotIn(List<Date> values) {
            addCriterion("day_date not in", values, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateBetween(Date value1, Date value2) {
            addCriterion("day_date between", value1, value2, "dayDate");
            return (Criteria) this;
        }

        public Criteria andDayDateNotBetween(Date value1, Date value2) {
            addCriterion("day_date not between", value1, value2, "dayDate");
            return (Criteria) this;
        }

        public Criteria andStClassifysIsNull() {
            addCriterion("st_classifys is null");
            return (Criteria) this;
        }

        public Criteria andStClassifysIsNotNull() {
            addCriterion("st_classifys is not null");
            return (Criteria) this;
        }

        public Criteria andStClassifysEqualTo(String value) {
            addCriterion("st_classifys =", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysNotEqualTo(String value) {
            addCriterion("st_classifys <>", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysGreaterThan(String value) {
            addCriterion("st_classifys >", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysGreaterThanOrEqualTo(String value) {
            addCriterion("st_classifys >=", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysLessThan(String value) {
            addCriterion("st_classifys <", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysLessThanOrEqualTo(String value) {
            addCriterion("st_classifys <=", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysLike(String value) {
            addCriterion("st_classifys like", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysNotLike(String value) {
            addCriterion("st_classifys not like", value, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysIn(List<String> values) {
            addCriterion("st_classifys in", values, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysNotIn(List<String> values) {
            addCriterion("st_classifys not in", values, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysBetween(String value1, String value2) {
            addCriterion("st_classifys between", value1, value2, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStClassifysNotBetween(String value1, String value2) {
            addCriterion("st_classifys not between", value1, value2, "stClassifys");
            return (Criteria) this;
        }

        public Criteria andStLevelsIsNull() {
            addCriterion("st_levels is null");
            return (Criteria) this;
        }

        public Criteria andStLevelsIsNotNull() {
            addCriterion("st_levels is not null");
            return (Criteria) this;
        }

        public Criteria andStLevelsEqualTo(String value) {
            addCriterion("st_levels =", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsNotEqualTo(String value) {
            addCriterion("st_levels <>", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsGreaterThan(String value) {
            addCriterion("st_levels >", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsGreaterThanOrEqualTo(String value) {
            addCriterion("st_levels >=", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsLessThan(String value) {
            addCriterion("st_levels <", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsLessThanOrEqualTo(String value) {
            addCriterion("st_levels <=", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsLike(String value) {
            addCriterion("st_levels like", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsNotLike(String value) {
            addCriterion("st_levels not like", value, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsIn(List<String> values) {
            addCriterion("st_levels in", values, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsNotIn(List<String> values) {
            addCriterion("st_levels not in", values, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsBetween(String value1, String value2) {
            addCriterion("st_levels between", value1, value2, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStLevelsNotBetween(String value1, String value2) {
            addCriterion("st_levels not between", value1, value2, "stLevels");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesIsNull() {
            addCriterion("st_knowledges is null");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesIsNotNull() {
            addCriterion("st_knowledges is not null");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesEqualTo(String value) {
            addCriterion("st_knowledges =", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesNotEqualTo(String value) {
            addCriterion("st_knowledges <>", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesGreaterThan(String value) {
            addCriterion("st_knowledges >", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesGreaterThanOrEqualTo(String value) {
            addCriterion("st_knowledges >=", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesLessThan(String value) {
            addCriterion("st_knowledges <", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesLessThanOrEqualTo(String value) {
            addCriterion("st_knowledges <=", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesLike(String value) {
            addCriterion("st_knowledges like", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesNotLike(String value) {
            addCriterion("st_knowledges not like", value, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesIn(List<String> values) {
            addCriterion("st_knowledges in", values, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesNotIn(List<String> values) {
            addCriterion("st_knowledges not in", values, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesBetween(String value1, String value2) {
            addCriterion("st_knowledges between", value1, value2, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andStKnowledgesNotBetween(String value1, String value2) {
            addCriterion("st_knowledges not between", value1, value2, "stKnowledges");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("total_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("total_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Integer value) {
            addCriterion("total_count =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Integer value) {
            addCriterion("total_count <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Integer value) {
            addCriterion("total_count >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_count >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Integer value) {
            addCriterion("total_count <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_count <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Integer> values) {
            addCriterion("total_count in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Integer> values) {
            addCriterion("total_count not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Integer value1, Integer value2) {
            addCriterion("total_count between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_count not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andRightCountIsNull() {
            addCriterion("right_count is null");
            return (Criteria) this;
        }

        public Criteria andRightCountIsNotNull() {
            addCriterion("right_count is not null");
            return (Criteria) this;
        }

        public Criteria andRightCountEqualTo(Integer value) {
            addCriterion("right_count =", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountNotEqualTo(Integer value) {
            addCriterion("right_count <>", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountGreaterThan(Integer value) {
            addCriterion("right_count >", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("right_count >=", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountLessThan(Integer value) {
            addCriterion("right_count <", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountLessThanOrEqualTo(Integer value) {
            addCriterion("right_count <=", value, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountIn(List<Integer> values) {
            addCriterion("right_count in", values, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountNotIn(List<Integer> values) {
            addCriterion("right_count not in", values, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountBetween(Integer value1, Integer value2) {
            addCriterion("right_count between", value1, value2, "rightCount");
            return (Criteria) this;
        }

        public Criteria andRightCountNotBetween(Integer value1, Integer value2) {
            addCriterion("right_count not between", value1, value2, "rightCount");
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
