package com.fzutopic.dao;

import com.fzutopic.model.Topiclikes;
import com.fzutopic.model.TopiclikesExample;
import com.fzutopic.model.TopiclikesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopiclikesDao {
    long countByExample(TopiclikesExample example);

    int deleteByExample(TopiclikesExample example);

    int deleteByPrimaryKey(TopiclikesKey key);

    int insert(Topiclikes record);

    int insertSelective(Topiclikes record);

    List<Topiclikes> selectByExample(TopiclikesExample example);

    Topiclikes selectByPrimaryKey(TopiclikesKey key);

    int updateByExampleSelective(@Param("record") Topiclikes record, @Param("example") TopiclikesExample example);

    int updateByExample(@Param("record") Topiclikes record, @Param("example") TopiclikesExample example);

    int updateByPrimaryKeySelective(Topiclikes record);

    int updateByPrimaryKey(Topiclikes record);
}