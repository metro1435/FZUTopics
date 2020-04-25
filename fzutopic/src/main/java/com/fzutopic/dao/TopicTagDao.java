package com.fzutopic.dao;

import com.fzutopic.model.TopicTagExample;
import com.fzutopic.model.TopicTagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicTagDao {
    long countByExample(TopicTagExample example);

    int deleteByExample(TopicTagExample example);

    int deleteByPrimaryKey(TopicTagKey key);

    int insert(TopicTagKey record);

    int insertSelective(TopicTagKey record);

    List<TopicTagKey> selectByExample(TopicTagExample example);

    int updateByExampleSelective(@Param("record") TopicTagKey record, @Param("example") TopicTagExample example);

    int updateByExample(@Param("record") TopicTagKey record, @Param("example") TopicTagExample example);
}