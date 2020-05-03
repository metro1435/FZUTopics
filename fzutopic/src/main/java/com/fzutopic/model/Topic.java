package com.fzutopic.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * topic
 * @author 
 */
public class Topic implements Serializable {
    private String topicid;

    private String title;

    private Integer likes;

    private Integer unlikes;

    private String userid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;

    private Integer isanony;

    private Integer views;

    private Integer heats;

    private Integer commentcount;

    private Integer auditstatus;

    private String text;

    private static final long serialVersionUID = 1L;

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getUnlikes() {
        return unlikes;
    }

    public void setUnlikes(Integer unlikes) {
        this.unlikes = unlikes;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIsanony() {
        return isanony;
    }

    public void setIsanony(Integer isanony) {
        this.isanony = isanony;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getHeats() {
        return heats;
    }

    public void setHeats(Integer heats) {
        this.heats = heats;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getAuditstatus() {
        return auditstatus;
    }

    public void setAuditstatus(Integer auditstatus) {
        this.auditstatus = auditstatus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Topic other = (Topic) that;
        return (this.getTopicid() == null ? other.getTopicid() == null : this.getTopicid().equals(other.getTopicid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
            && (this.getUnlikes() == null ? other.getUnlikes() == null : this.getUnlikes().equals(other.getUnlikes()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getIsanony() == null ? other.getIsanony() == null : this.getIsanony().equals(other.getIsanony()))
            && (this.getViews() == null ? other.getViews() == null : this.getViews().equals(other.getViews()))
            && (this.getHeats() == null ? other.getHeats() == null : this.getHeats().equals(other.getHeats()))
            && (this.getCommentcount() == null ? other.getCommentcount() == null : this.getCommentcount().equals(other.getCommentcount()))
            && (this.getAuditstatus() == null ? other.getAuditstatus() == null : this.getAuditstatus().equals(other.getAuditstatus()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopicid() == null) ? 0 : getTopicid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getUnlikes() == null) ? 0 : getUnlikes().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getIsanony() == null) ? 0 : getIsanony().hashCode());
        result = prime * result + ((getViews() == null) ? 0 : getViews().hashCode());
        result = prime * result + ((getHeats() == null) ? 0 : getHeats().hashCode());
        result = prime * result + ((getCommentcount() == null) ? 0 : getCommentcount().hashCode());
        result = prime * result + ((getAuditstatus() == null) ? 0 : getAuditstatus().hashCode());
        result = prime * result + ((getText() == null) ? 0 : getText().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", topicid=").append(topicid);
        sb.append(", title=").append(title);
        sb.append(", likes=").append(likes);
        sb.append(", unlikes=").append(unlikes);
        sb.append(", userid=").append(userid);
        sb.append(", time=").append(time);
        sb.append(", isanony=").append(isanony);
        sb.append(", views=").append(views);
        sb.append(", heats=").append(heats);
        sb.append(", commentcount=").append(commentcount);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", text=").append(text);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}