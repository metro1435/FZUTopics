package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import com.fzutopic.model.TeacherinfoExample;
import java.util.List;

import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.TeacherName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherinfoDao {
    long countByExample(TeacherinfoExample example);

    int deleteByExample(TeacherinfoExample example);

    int deleteByPrimaryKey(String teacherid);

    //联表模糊搜索，221701401负责
    @Select("select * from teacherinfo inner join courseinfo inner join " +
            "course_teacher on teacherinfo.teacherID=course_teacher.teacherID " +
            "and courseinfo.courseID=course_teacher.courseID " +
            "where teacherinfo.teacherName like CONCAT('%',#{0},'%')")
    List<CourseTeacherInfo> selectTeacherNameByLike(String name);

    int insert(Teacherinfo record);

    int insertSelective(Teacherinfo record);

    List<TeacherName> selectAllTeacher();

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