package com.fzutopic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CommentExample() {
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

        public Criteria andCommentidIsNull() {
            addCriterion("commentID is null");
            return (Criteria) this;
        }

        public Criteria andCommentidIsNotNull() {
            addCriterion("commentID is not null");
            return (Criteria) this;
        }

        public Criteria andCommentidEqualTo(String value) {
            addCriterion("commentID =", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotEqualTo(String value) {
            addCriterion("commentID <>", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThan(String value) {
            addCriterion("commentID >", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidGreaterThanOrEqualTo(String value) {
            addCriterion("commentID >=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThan(String value) {
            addCriterion("commentID <", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLessThanOrEqualTo(String value) {
            addCriterion("commentID <=", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidLike(String value) {
            addCriterion("commentID like", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotLike(String value) {
            addCriterion("commentID not like", value, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidIn(List<String> values) {
            addCriterion("commentID in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotIn(List<String> values) {
            addCriterion("commentID not in", values, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidBetween(String value1, String value2) {
            addCriterion("commentID between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andCommentidNotBetween(String value1, String value2) {
            addCriterion("commentID not between", value1, value2, "commentid");
            return (Criteria) this;
        }

        public Criteria andLikesIsNull() {
            addCriterion("likes is null");
            return (Criteria) this;
        }

        public Criteria andLikesIsNotNull() {
            addCriterion("likes is not null");
            return (Criteria) this;
        }

        public Criteria andLikesEqualTo(Integer value) {
            addCriterion("likes =", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotEqualTo(Integer value) {
            addCriterion("likes <>", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesGreaterThan(Integer value) {
            addCriterion("likes >", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes >=", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesLessThan(Integer value) {
            addCriterion("likes <", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesLessThanOrEqualTo(Integer value) {
            addCriterion("likes <=", value, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesIn(List<Integer> values) {
            addCriterion("likes in", values, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotIn(List<Integer> values) {
            addCriterion("likes not in", values, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesBetween(Integer value1, Integer value2) {
            addCriterion("likes between", value1, value2, "likes");
            return (Criteria) this;
        }

        public Criteria andLikesNotBetween(Integer value1, Integer value2) {
            addCriterion("likes not between", value1, value2, "likes");
            return (Criteria) this;
        }

        public Criteria andUnlikesIsNull() {
            addCriterion("unlikes is null");
            return (Criteria) this;
        }

        public Criteria andUnlikesIsNotNull() {
            addCriterion("unlikes is not null");
            return (Criteria) this;
        }

        public Criteria andUnlikesEqualTo(Integer value) {
            addCriterion("unlikes =", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesNotEqualTo(Integer value) {
            addCriterion("unlikes <>", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesGreaterThan(Integer value) {
            addCriterion("unlikes >", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesGreaterThanOrEqualTo(Integer value) {
            addCriterion("unlikes >=", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesLessThan(Integer value) {
            addCriterion("unlikes <", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesLessThanOrEqualTo(Integer value) {
            addCriterion("unlikes <=", value, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesIn(List<Integer> values) {
            addCriterion("unlikes in", values, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesNotIn(List<Integer> values) {
            addCriterion("unlikes not in", values, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesBetween(Integer value1, Integer value2) {
            addCriterion("unlikes between", value1, value2, "unlikes");
            return (Criteria) this;
        }

        public Criteria andUnlikesNotBetween(Integer value1, Integer value2) {
            addCriterion("unlikes not between", value1, value2, "unlikes");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("`time` is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("`time` is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("`time` =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("`time` <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("`time` >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`time` >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("`time` <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("`time` <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("`time` in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("`time` not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("`time` between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("`time` not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andIsanonyIsNull() {
            addCriterion("isAnony is null");
            return (Criteria) this;
        }

        public Criteria andIsanonyIsNotNull() {
            addCriterion("isAnony is not null");
            return (Criteria) this;
        }

        public Criteria andIsanonyEqualTo(Integer value) {
            addCriterion("isAnony =", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyNotEqualTo(Integer value) {
            addCriterion("isAnony <>", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyGreaterThan(Integer value) {
            addCriterion("isAnony >", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyGreaterThanOrEqualTo(Integer value) {
            addCriterion("isAnony >=", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyLessThan(Integer value) {
            addCriterion("isAnony <", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyLessThanOrEqualTo(Integer value) {
            addCriterion("isAnony <=", value, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyIn(List<Integer> values) {
            addCriterion("isAnony in", values, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyNotIn(List<Integer> values) {
            addCriterion("isAnony not in", values, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyBetween(Integer value1, Integer value2) {
            addCriterion("isAnony between", value1, value2, "isanony");
            return (Criteria) this;
        }

        public Criteria andIsanonyNotBetween(Integer value1, Integer value2) {
            addCriterion("isAnony not between", value1, value2, "isanony");
            return (Criteria) this;
        }

        public Criteria andPosteridIsNull() {
            addCriterion("posterID is null");
            return (Criteria) this;
        }

        public Criteria andPosteridIsNotNull() {
            addCriterion("posterID is not null");
            return (Criteria) this;
        }

        public Criteria andPosteridEqualTo(String value) {
            addCriterion("posterID =", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridNotEqualTo(String value) {
            addCriterion("posterID <>", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridGreaterThan(String value) {
            addCriterion("posterID >", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridGreaterThanOrEqualTo(String value) {
            addCriterion("posterID >=", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridLessThan(String value) {
            addCriterion("posterID <", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridLessThanOrEqualTo(String value) {
            addCriterion("posterID <=", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridLike(String value) {
            addCriterion("posterID like", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridNotLike(String value) {
            addCriterion("posterID not like", value, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridIn(List<String> values) {
            addCriterion("posterID in", values, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridNotIn(List<String> values) {
            addCriterion("posterID not in", values, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridBetween(String value1, String value2) {
            addCriterion("posterID between", value1, value2, "posterid");
            return (Criteria) this;
        }

        public Criteria andPosteridNotBetween(String value1, String value2) {
            addCriterion("posterID not between", value1, value2, "posterid");
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

        public Criteria andIsreplyIsNull() {
            addCriterion("isReply is null");
            return (Criteria) this;
        }

        public Criteria andIsreplyIsNotNull() {
            addCriterion("isReply is not null");
            return (Criteria) this;
        }

        public Criteria andIsreplyEqualTo(Integer value) {
            addCriterion("isReply =", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyNotEqualTo(Integer value) {
            addCriterion("isReply <>", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyGreaterThan(Integer value) {
            addCriterion("isReply >", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyGreaterThanOrEqualTo(Integer value) {
            addCriterion("isReply >=", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyLessThan(Integer value) {
            addCriterion("isReply <", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyLessThanOrEqualTo(Integer value) {
            addCriterion("isReply <=", value, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyIn(List<Integer> values) {
            addCriterion("isReply in", values, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyNotIn(List<Integer> values) {
            addCriterion("isReply not in", values, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyBetween(Integer value1, Integer value2) {
            addCriterion("isReply between", value1, value2, "isreply");
            return (Criteria) this;
        }

        public Criteria andIsreplyNotBetween(Integer value1, Integer value2) {
            addCriterion("isReply not between", value1, value2, "isreply");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIsNull() {
            addCriterion("auditStatus is null");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIsNotNull() {
            addCriterion("auditStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAuditstatusEqualTo(Integer value) {
            addCriterion("auditStatus =", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotEqualTo(Integer value) {
            addCriterion("auditStatus <>", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusGreaterThan(Integer value) {
            addCriterion("auditStatus >", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditStatus >=", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusLessThan(Integer value) {
            addCriterion("auditStatus <", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusLessThanOrEqualTo(Integer value) {
            addCriterion("auditStatus <=", value, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusIn(List<Integer> values) {
            addCriterion("auditStatus in", values, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotIn(List<Integer> values) {
            addCriterion("auditStatus not in", values, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusBetween(Integer value1, Integer value2) {
            addCriterion("auditStatus between", value1, value2, "auditstatus");
            return (Criteria) this;
        }

        public Criteria andAuditstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auditStatus not between", value1, value2, "auditstatus");
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