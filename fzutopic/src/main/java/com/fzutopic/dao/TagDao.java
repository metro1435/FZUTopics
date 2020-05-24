package com.fzutopic.dao;

import com.fzutopic.model.Tag;
import com.fzutopic.model.TagExample;
import java.util.List;

import com.fzutopic.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TagDao {
    long countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(String tagid);

    int insert(Tag record);

    //管理员新增标签，221701309负责
    @Insert({ "insert into tag(tagid, name, times) values(#{tagid}, #{name}, #{times})" })
    int insert1(Tag record);

    int insertSelective(Tag record);

    //1401
    @Select("select * from tag")
    List<Tag> select();

    List<Tag> selectByExample(TagExample example);

    Tag selectByPrimaryKey(String tagid);

    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

}