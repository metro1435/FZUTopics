package com.fzutopic.model;

import java.util.ArrayList;
import java.util.List;

public class TopicTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TopicTagExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andTagidIsNull() {
            addCriterion("tagID is null");
            return (Criteria) this;
        }

        public Criteria andTagidIsNotNull() {
            addCriterion("tagID is not null");
            return (Criteria) this;
        }

        public Criteria andTagidEqualTo(String value) {
            addCriterion("tagID =", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotEqualTo(String value) {
            addCriterion("tagID <>", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidGreaterThan(String value) {
            addCriterion("tagID >", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidGreaterThanOrEqualTo(String value) {
            addCriterion("tagID >=", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidLessThan(String value) {
            addCriterion("tagID <", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidLessThanOrEqualTo(String value) {
            addCriterion("tagID <=", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidLike(String value) {
            addCriterion("tagID like", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotLike(String value) {
            addCriterion("tagID not like", value, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidIn(List<String> values) {
            addCriterion("tagID in", values, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotIn(List<String> values) {
            addCriterion("tagID not in", values, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidBetween(String value1, String value2) {
            addCriterion("tagID between", value1, value2, "tagid");
            return (Criteria) this;
        }

        public Criteria andTagidNotBetween(String value1, String value2) {
            addCriterion("tagID not between", value1, value2, "tagid");
            return (Criteria) this;
        }

        public Criteria andTopicidIsNull() {
            addCriterion("topicID is null");
            return (Criteria) this;
        }

        public Criteria andTopicidIsNotNull() {
            addCriterion("topicID is not null");
            return (Criteria) this;
        }

        public Criteria andTopicidEqualTo(String value) {
            addCriterion("topicID =", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotEqualTo(String value) {
            addCriterion("topicID <>", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThan(String value) {
            addCriterion("topicID >", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidGreaterThanOrEqualTo(String value) {
            addCriterion("topicID >=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThan(String value) {
            addCriterion("topicID <", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLessThanOrEqualTo(String value) {
            addCriterion("topicID <=", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidLike(String value) {
            addCriterion("topicID like", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotLike(String value) {
            addCriterion("topicID not like", value, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidIn(List<String> values) {
            addCriterion("topicID in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotIn(List<String> values) {
            addCriterion("topicID not in", values, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidBetween(String value1, String value2) {
            addCriterion("topicID between", value1, value2, "topicid");
            return (Criteria) this;
        }

        public Criteria andTopicidNotBetween(String value1, String value2) {
            addCriterion("topicID not between", value1, value2, "topicid");
            return (Criteria) this;
        }
    }

    /**
     */
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