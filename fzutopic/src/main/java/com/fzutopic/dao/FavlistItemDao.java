package com.fzutopic.dao;

import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import java.util.List;

import org.apache.ibatis.annotations.*;

public interface FavlistItemDao {
    long countByExample(FavlistItemExample example);

    int deleteByExample(FavlistItemExample example);

    int deleteByPrimaryKey(FavlistItemKey key);

    int insert(FavlistItemKey record);

    int insertSelective(FavlistItemKey record);

    List<FavlistItemKey> selectByExample(FavlistItemExample example);

    int updateByExampleSelective(@Param("record") FavlistItemKey record, @Param("example") FavlistItemExample example);

    int updateByExample(@Param("record") FavlistItemKey record, @Param("example") FavlistItemExample example);

    //根据favlistid获取收藏内容列表 403
    @Select("SELECT * FROM `favlist_item` WHERE favlistID= #{favlistid}")
    @Results({
            @Result(property = "favlistid",  column = "favlistID")
    })
    List<FavlistItemKey> getFavlistItemByFavlistid(String favlistid);

    //根据collectedid获取收藏内容 403
    @Select("select * from favlist_item where collectedID = #{collectedid}")
    @Results({
            @Result(property = "collectedid",  column = "collectedID")
    })
    FavlistItemKey getFavlistItemKeyBycollectedid(String collectedid);

    //添加某个收藏内容 403
    @Insert("insert into favlist_item(favlistID,collectedID) values (#{favlistid},#{collectedid})")
    int insertByFavlistItemKey(FavlistItemKey favlistItemKey);
}