package com.fzutopic.dao;

import com.fzutopic.model.Ctcomment;
import com.fzutopic.model.CtcommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}