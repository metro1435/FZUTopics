package com.fzutopic.dao;

import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavlistItemDao {
    long countByExample(FavlistItemExample example);

    int deleteByExample(FavlistItemExample example);

    int deleteByPrimaryKey(FavlistItemKey key);

    int insert(FavlistItemKey record);

    int insertSelective(FavlistItemKey record);

    List<FavlistItemKey> selectByExample(FavlistItemExample example);

    int updateByExampleSelective(@Param("record") FavlistItemKey record, @Param("example") FavlistItemExample example);

    int updateByExample(@Param("record") FavlistItemKey record, @Param("example") FavlistItemExample example);
}