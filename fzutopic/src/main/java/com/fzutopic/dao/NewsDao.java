package com.fzutopic.dao;

import com.fzutopic.model.Favlist;
import com.fzutopic.model.News;
import com.fzutopic.model.NewsExample;
import java.util.List;

import com.fzutopic.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NewsDao {
    long countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(String newsid);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    //模糊搜索 1309
    @Select({"select * from news where title like CONCAT('%',#{0},'%') ORDER BY time DESC"})
    List<News> selectByTitleLike(String name);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(String newsid);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    @Insert("insert into news(newsID,adminID,title,text,time) values (#{newsid},#{adminid},#{title},#{text},#{time})")
    int insertByNews(News news);
}