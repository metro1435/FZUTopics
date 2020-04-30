package com.fzutopic.dao;

import com.fzutopic.model.Reply;
import com.fzutopic.model.ReplyExample;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ReplyDao {
    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(String replyid);

    //插入回复，221701401负责
    @Insert({"insert into reply(replyid,text,likes,unlikes,time,isAnony,userid,commentid,auditstatus) " +
            "values(#{replyid},#{text},#{likes},#{unlikes},#{time},#{isanony},#{userid},#{commentid},#{auditstatus})"})
    boolean insertReply(Reply reply);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExampleWithBLOBs(ReplyExample example);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(String replyid);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);
}