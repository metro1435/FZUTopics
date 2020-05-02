package com.fzutopic.service;

import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistItemKey;

import java.util.List;

public interface FavlistService {

    //根据userid获取收藏夹列表
    List<Favlist> getFavlists(String userid);

    //根据favlistid获取收藏内容列表
    List<FavlistItemKey> getFavlistItems(String favlistid);

    //新建收藏夹
    Favlist createFavlist(Favlist favlist);

    //添加某个收藏内容
    FavlistItemKey createFavlistItem(FavlistItemKey favlistItemKey);

    //删除收藏夹
    void deleteFavlist(String favlistid);

    //根据collectedid获取某项收藏内容
    FavlistItemKey getFavlistItem(String collectedid);

    //取消收藏某个内容
    void pushFavlistItem(FavlistItemKey favlistItemKey);
}
