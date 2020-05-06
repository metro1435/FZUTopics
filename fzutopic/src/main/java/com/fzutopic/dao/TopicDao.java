package com.fzutopic.dao;

import com.fzutopic.model.Topic;
import com.fzutopic.model.TopicExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TopicDao {
    long countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(String topicid);

    int insert(Topic record);

    //1309
    @Insert({ "insert into topic(topicid, title, text, likes, unlikes, userid, time, isanony, views, heats, commentcount, auditstatus) " +
            "values(#{topicid}, #{title}, #{text}, #{likes}, #{unlikes}, #{userid}, #{time}, #{isanony}, #{views}, #{heats}, #{commentcount},#{auditstatus})" })
    int insert1(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExampleWithBLOBs(TopicExample example);

    List<Topic> selectByExample(TopicExample example);

    //模糊搜索，221701401负责
    @Select({"select * from topic where title like CONCAT('%',#{0},'%')" +
            " and auditstatus = '1' order by heats desc"})
    List<Topic> selectByTitleLike(String title);

    @Select({"select * from topic_tag inner join topic on topic_tag.topicid=topic.topicid " +
            "where topic_tag.tagid=#{tagid}"})
    List<Topic> selectByTag(String tagid);

    Topic selectByPrimaryKey(String topicid);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExampleWithBLOBs(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);
}