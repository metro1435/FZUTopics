package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import com.fzutopic.model.TeacherinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherinfoDao {
    long countByExample(TeacherinfoExample example);

    int deleteByExample(TeacherinfoExample example);

    int deleteByPrimaryKey(String teacherid);

    //模糊搜索，221701401负责
    @Select({"select * from teacherinfo where teachername like CONCAT('%',#{0},'%')"})
    List<Teacherinfo> selectTeacherNameByLike(String name);

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