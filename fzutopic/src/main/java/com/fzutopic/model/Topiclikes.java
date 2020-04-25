package com.fzutopic.model;

import java.io.Serializable;

/**
 * topiclikes
 * @author 
 */
public class Topiclikes extends TopiclikesKey implements Serializable {
    private Integer likedstatus;

    private static final long serialVersionUID = 1L;

    public Integer getLikedstatus() {
        return likedstatus;
    }

    public void setLikedstatus(Integer likedstatus) {
        this.likedstatus = likedstatus;
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
        Topiclikes other = (Topiclikes) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getTopicid() == null ? other.getTopicid() == null : this.getTopicid().equals(other.getTopicid()))
            && (this.getLikedstatus() == null ? other.getLikedstatus() == null : this.getLikedstatus().equals(other.getLikedstatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getTopicid() == null) ? 0 : getTopicid().hashCode());
        result = prime * result + ((getLikedstatus() == null) ? 0 : getLikedstatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", likedstatus=").append(likedstatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}