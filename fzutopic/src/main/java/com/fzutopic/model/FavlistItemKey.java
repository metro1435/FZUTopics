package com.fzutopic.model;

import java.io.Serializable;

/**
 * favlist_item
 * @author 
 */
public class FavlistItemKey implements Serializable {
    private String favlistid;

    private String collectedid;

    private static final long serialVersionUID = 1L;

    public String getFavlistid() {
        return favlistid;
    }

    public void setFavlistid(String favlistid) {
        this.favlistid = favlistid;
    }

    public String getCollectedid() {
        return collectedid;
    }

    public void setCollectedid(String collectedid) {
        this.collectedid = collectedid;
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
        FavlistItemKey other = (FavlistItemKey) that;
        return (this.getFavlistid() == null ? other.getFavlistid() == null : this.getFavlistid().equals(other.getFavlistid()))
            && (this.getCollectedid() == null ? other.getCollectedid() == null : this.getCollectedid().equals(other.getCollectedid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFavlistid() == null) ? 0 : getFavlistid().hashCode());
        result = prime * result + ((getCollectedid() == null) ? 0 : getCollectedid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", favlistid=").append(favlistid);
        sb.append(", collectedid=").append(collectedid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}