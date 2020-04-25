package com.fzutopic.dao;

import com.fzutopic.model.Teacherinfo;
import com.fzutopic.model.TeacherinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherinfoDao {
    long countByExample(TeacherinfoExample example);

    int deleteByExample(TeacherinfoExample example);

    int deleteByPrimaryKey(String teacherid);

    int insert(Teacherinfo record);

    int insertSelective(Teacherinfo record);

    List<Teacherinfo> selectByExampleWithBLOBs(TeacherinfoExample example);

    List<Teacherinfo> selectByExample(TeacherinfoExample example);

    Teacherinfo selectByPrimaryKey(String teacherid);

    int updateByExampleSelective(@Param("record") Teacherinfo record, @Param("example") TeacherinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Teacherinfo record, @Param("example") TeacherinfoExample example);

    int updateByExample(@Param("record") Teacherinfo record, @Param("example") TeacherinfoExample example);

    int updateByPrimaryKeySelective(Teacherinfo record);

    int updateByPrimaryKeyWithBLOBs(Teacherinfo record);

    int updateByPrimaryKey(Teacherinfo record);
}