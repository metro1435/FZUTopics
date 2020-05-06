package com.fzutopic.dao;

import com.fzutopic.model.Comment;
import com.fzutopic.model.CommentExample;
import java.util.List;

import com.fzutopic.model.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CommentDao {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(String commentid);

    int insert(Comment record);

    //1309
    @Insert({ "insert into comment(commentid, text, time, isanony, posterid, topicid, isreply, auditstatus) " +
            "values(#{commentid}, #{text}, #{time}, #{isanony}, #{posterid}, #{topicid}, #{isreply},#{auditstatus})" })
    int insert1(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(String commentid);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
}