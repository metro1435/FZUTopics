package com.fzutopic.dao;

import com.fzutopic.model.Comment;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.model.CtcommentExample;
import java.util.List;

import com.fzutopic.model.Favlist;
import org.apache.ibatis.annotations.*;

public interface CtcommentDao {
    long countByExample(CtcommentExample example);

    int deleteByExample(CtcommentExample example);

    int deleteByPrimaryKey(String commentid);

    int insert(Ctcomment record);

    int insertSelective(Ctcomment record);

    List<Ctcomment> selectByExampleWithBLOBs(CtcommentExample example);

    List<Ctcomment> selectByExample(CtcommentExample example);

    Ctcomment selectByPrimaryKey(String commentid);

    int updateByExampleSelective(@Param("record") Ctcomment record, @Param("example") CtcommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Ctcomment record, @Param("example") CtcommentExample example);

    int updateByExample(@Param("record") Ctcomment record, @Param("example") CtcommentExample example);

    int updateByPrimaryKeySelective(Ctcomment record);

    int updateByPrimaryKeyWithBLOBs(Ctcomment record);

    int updateByPrimaryKey(Ctcomment record);

    //根据课程（教师）id获取评论列表 403
    @Select("SELECT * FROM `ctcomment` WHERE commentItemID = #{commentitemid}")
    @Results({
            @Result(property = "commentitemid", column = "commentItemID")
    })
    List<Comment> getCommentBycommentitemid(String commentitemid);

    //新增课程（教师）评论 403
    @Insert("insert into ctcomment(commentID,text,likes,unlikes,time,isAnony,userID,commentItemID,auditStatus) " +
            "values (#{commentid},#{text},#{likes},#{unlikes},#{time},#{isanony},#{userid},#{commentitemid},#{auditstatus})")
    int insertByCtcomment(Ctcomment ctcomment);
}