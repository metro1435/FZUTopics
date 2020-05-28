package com.fzutopic.service;

import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistItemKey;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FavlistService {

    //根据userid获取收藏夹列表,1403
    //1页显示10个收藏夹
    PageInfo<Favlist> getFavlists(String userid);

    List<Favlist> getAllFavlist(String userid);

    //根据favlistid获取收藏内容列表,1403
    //1页显示15个收藏内容
    PageInfo<FavlistItemKey> getFavlistItems(String favlistid);

    //新建收藏夹,1403
    Favlist createFavlist(Favlist favlist);

    //添加某个收藏内容,1403
    FavlistItemKey createFavlistItem(FavlistItemKey favlistItemKey);

    //删除收藏夹,1403
    void deleteFavlist(String favlistid);

    //根据collectedid获取某项收藏内容,1403
    FavlistItemKey getFavlistItem(String collectedid);

    //取消收藏某个内容,1403
    void pushFavlistItem(FavlistItemKey favlistItemKey);
}
