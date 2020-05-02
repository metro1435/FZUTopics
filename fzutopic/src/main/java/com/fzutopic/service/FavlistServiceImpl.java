package com.fzutopic.service;

import com.fzutopic.dao.FavlistDao;
import com.fzutopic.dao.FavlistItemDao;
import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistExample;
import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class FavlistServiceImpl {

    @Resource
    private FavlistDao favlistDao;
    @Resource
    private FavlistItemDao favlistItemDao;

    //实现根据userid获取收藏夹列表
    public List<Favlist> getFavlists(String userid){
        FavlistExample example=new FavlistExample();
        FavlistExample.Criteria criteria=example.createCriteria();
        criteria.andUseridEqualTo(userid);
        example.setOrderByClause("favlistid desc");
        example.setLimit(10);
        return favlistDao.selectByExample(example);
    }

    //实现根据favlistid获取收藏内容列表
    public List<FavlistItemKey> getFavlistItems(String favlistid){
        FavlistItemExample example1=new FavlistItemExample();
        FavlistItemExample.Criteria criteria=example1.createCriteria();
        criteria.andFavlistidEqualTo(favlistid);
        example1.setOrderByClause("collectedid desc");
        example1.setLimit(15);
        return favlistItemDao.selectByExample(example1);
    }

    //实现新建收藏夹
    public Favlist createFavlist(Favlist favlist){
        favlistDao.insertByFavlist(favlist);
        favlist.setFavlistid(favlist.getFavlistid());
        favlist.setUserid(favlist.getUserid());
        favlist.setName(favlist.getName());
        favlist.setTime(favlist.getTime());
        return favlist;
    }

    //实现添加某个收藏内容
    public FavlistItemKey createFavlistItem(FavlistItemKey favlistItemKey){
        favlistItemDao.insertByFavlistItemKey(favlistItemKey);
        favlistItemKey.setFavlistid(favlistItemKey.getFavlistid());
        favlistItemKey.setCollectedid(favlistItemKey.getCollectedid());
        return favlistItemKey;
    }

    //实现删除某个收藏夹
    public void deleteFavlist(String favlistid){
        favlistDao.deleteByPrimaryKey(favlistid);
    }

    //实现根据collectedid获取某项收藏内容
    public FavlistItemKey getFavlistItem(String collectedid){
        return favlistItemDao.getFavlistItemKeyBycollectedid(collectedid);
    }

    //实现取消收藏某个内容
    public String pushFavlistItem(FavlistItemKey favlistItemKey){
        String string=favlistItemKey.getCollectedid();
        favlistItemDao.deleteByPrimaryKey(favlistItemKey);
        return string;
    }
}
