package com.fzutopic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * favlist
 * @author 
 */
public class Favlist implements Serializable {
    private String favlistid;

    private String userid;

    private String name;

    private Date time;

    private static final long serialVersionUID = 1L;

    //403
    private List<FavlistItemKey> favlistItemKeys;

    public String getFavlistid() {
        return favlistid;
    }

    public void setFavlistid(String favlistid) {
        this.favlistid = favlistid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        Favlist other = (Favlist) that;
        return (this.getFavlistid() == null ? other.getFavlistid() == null : this.getFavlistid().equals(other.getFavlistid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFavlistid() == null) ? 0 : getFavlistid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", favlistid=").append(favlistid);
        sb.append(", userid=").append(userid);
        sb.append(", name=").append(name);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    //403
    public List<FavlistItemKey> getFavlistItemKeys() {
        return favlistItemKeys;
    }

    public void setFavlistItemKeys(List<FavlistItemKey> favlistItemKeys) {
        this.favlistItemKeys = favlistItemKeys;
    }
}