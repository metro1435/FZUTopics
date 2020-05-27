package com.fzutopic.service;

import com.fzutopic.dao.FavlistDao;
import com.fzutopic.dao.FavlistItemDao;
import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistExample;
import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    //实现根据userid获取收藏夹列表,1403
    //1页显示10个收藏夹
    public PageInfo<Favlist> getFavlists(String userid,int page){
        FavlistExample example=new FavlistExample();
        FavlistExample.Criteria criteria=example.createCriteria();
        criteria.andUseridEqualTo(userid);
        example.setOrderByClause("favlistid desc");
        PageHelper.startPage(page,10);
        List<Favlist> favlists=favlistDao.selectByExample(example);
        return new PageInfo<>(favlists);
    }

    //实现根据favlistid获取收藏内容列表,1403
    //1页显示15个收藏内容
    //实现根据favlistid获取收藏内容列表,1403
    //1页显示15个收藏内容
    public PageInfo<FavlistItemKey> getFavlistItems(String favlistid,int page){
        FavlistItemExample example1=new FavlistItemExample();
        FavlistItemExample.Criteria criteria=example1.createCriteria();
        criteria.andFavlistidEqualTo(favlistid);
        example1.setOrderByClause("collectedid desc");
        PageHelper.startPage(page,15);
        List<FavlistItemKey> favlistItemKeys=favlistItemDao.selectByExample(example1);
        return new PageInfo<>(favlistItemKeys);
    }

    //实现新建收藏夹,1403
    public Favlist createFavlist(Favlist favlist){
        favlistDao.insertByFavlist(favlist);
        favlist.setFavlistid(favlist.getFavlistid());
        favlist.setUserid(favlist.getUserid());
        favlist.setName(favlist.getName());
        favlist.setTime(favlist.getTime());
        return favlist;
    }

    //实现添加某个收藏内容,1403
    public FavlistItemKey createFavlistItem(FavlistItemKey favlistItemKey){
        favlistItemDao.insertByFavlistItemKey(favlistItemKey);
        favlistItemKey.setFavlistid(favlistItemKey.getFavlistid());
        favlistItemKey.setCollectedid(favlistItemKey.getCollectedid());
        return favlistItemKey;
    }

    //实现删除某个收藏夹,1403
    public void deleteFavlist(String favlistid){
        favlistDao.deleteByPrimaryKey(favlistid);
    }

    //实现根据collectedid获取某项收藏内容,1403
    public FavlistItemKey getFavlistItem(String collectedid){
        return favlistItemDao.getFavlistItemKeyBycollectedid(collectedid);
    }

    //实现取消收藏某个内容,1403
    public String pushFavlistItem(FavlistItemKey favlistItemKey){
        String string=favlistItemKey.getCollectedid();
        favlistItemDao.deleteByPrimaryKey(favlistItemKey);
        return string;
    }

    //1309
    public boolean getfavstatus(String collectedid, String userid) {
        FavlistItemExample example = new FavlistItemExample();
        FavlistItemExample.Criteria criteria = example.createCriteria();
        criteria.andCollectedidEqualTo(collectedid);
        List<FavlistItemKey> favlistItemKeys = favlistItemDao.selectByExample(example);
        for (FavlistItemKey favlistItemKey : favlistItemKeys) {
            if(favlistItemKey.getFavlistid().substring(0, 9).equals(userid))
                return true;
        }
        return false;
    }
}
