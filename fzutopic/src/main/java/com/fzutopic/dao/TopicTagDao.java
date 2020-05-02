package com.fzutopic.dao;

import com.fzutopic.model.Reply;
import com.fzutopic.model.TopicTagExample;
import com.fzutopic.model.TopicTagKey;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface TopicTagDao {
    long countByExample(TopicTagExample example);

    int deleteByExample(TopicTagExample example);

    int deleteByPrimaryKey(TopicTagKey key);

    //插入回复，221701401负责
    @Insert({"insert into topic_tag(tagid,topicid) values(#{tagid},#{topicid})"})
    boolean insertTopicTag(TopicTagKey topicTagKey);

    int insert(TopicTagKey record);

    int insertSelective(TopicTagKey record);

    List<TopicTagKey> selectByExample(TopicTagExample example);

    int updateByExampleSelective(@Param("record") TopicTagKey record, @Param("example") TopicTagExample example);

    int updateByExample(@Param("record") TopicTagKey record, @Param("example") TopicTagExample example);
}