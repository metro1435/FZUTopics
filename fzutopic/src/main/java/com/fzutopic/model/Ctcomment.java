package com.fzutopic.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * ctcomment
 * @author 
 */
public class Ctcomment implements Serializable {
    private String commentid;

    private Integer likes;

    private Integer unlikes;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;

    private Integer isanony;

    private String userid;

    private String commentitemid;

    private Integer auditstatus;

    private String text;

    private static final long serialVersionUID = 1L;

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommentitemid() {
        return commentitemid;
    }

    public void setCommentitemid(String commentitemid) {
        this.commentitemid = commentitemid;
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
        Ctcomment other = (Ctcomment) that;
        return (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()))
            && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
            && (this.getUnlikes() == null ? other.getUnlikes() == null : this.getUnlikes().equals(other.getUnlikes()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getIsanony() == null ? other.getIsanony() == null : this.getIsanony().equals(other.getIsanony()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getCommentitemid() == null ? other.getCommentitemid() == null : this.getCommentitemid().equals(other.getCommentitemid()))
            && (this.getAuditstatus() == null ? other.getAuditstatus() == null : this.getAuditstatus().equals(other.getAuditstatus()))
            && (this.getText() == null ? other.getText() == null : this.getText().equals(other.getText()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentid() == null) ? 0 : getCommentid().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getUnlikes() == null) ? 0 : getUnlikes().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getIsanony() == null) ? 0 : getIsanony().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getCommentitemid() == null) ? 0 : getCommentitemid().hashCode());
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
        sb.append(", commentid=").append(commentid);
        sb.append(", likes=").append(likes);
        sb.append(", unlikes=").append(unlikes);
        sb.append(", time=").append(time);
        sb.append(", isanony=").append(isanony);
        sb.append(", userid=").append(userid);
        sb.append(", commentitemid=").append(commentitemid);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", text=").append(text);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}