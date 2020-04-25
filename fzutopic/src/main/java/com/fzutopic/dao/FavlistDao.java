package com.fzutopic.dao;

import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavlistDao {
    long countByExample(FavlistExample example);

    int deleteByExample(FavlistExample example);

    int deleteByPrimaryKey(String favlistid);

    int insert(Favlist record);

    int insertSelective(Favlist record);

    List<Favlist> selectByExample(FavlistExample example);

    Favlist selectByPrimaryKey(String favlistid);

    int updateByExampleSelective(@Param("record") Favlist record, @Param("example") FavlistExample example);

    int updateByExample(@Param("record") Favlist record, @Param("example") FavlistExample example);

    int updateByPrimaryKeySelective(Favlist record);

    int updateByPrimaryKey(Favlist record);
}