package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.CourseinfoExample;
import java.util.List;

import com.fzutopic.view.CourseTeacherInfo;
import org.apache.ibatis.annotations.*;

public interface CourseinfoDao {
    long countByExample(CourseinfoExample example);

    int deleteByExample(CourseinfoExample example);

    int deleteByPrimaryKey(String courseid);

    //联表模糊搜索，221701401负责
    @Select("select * from teacherinfo inner join courseinfo inner join " +
            "course_teacher on teacherinfo.teacherID=course_teacher.teacherID " +
            "and courseinfo.courseID=course_teacher.courseID " +
            "where courseinfo.courseName like CONCAT('%',#{0},'%')")
    List<CourseTeacherInfo> selectCourseNameByLike(String name);

    int insert(Courseinfo record);

    int insertSelective(Courseinfo record);

    List<Courseinfo> selectByExample(CourseinfoExample example);

    Courseinfo selectByPrimaryKey(String courseid);

    int updateByExampleSelective(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByExample(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByPrimaryKeySelective(Courseinfo record);

    int updateByPrimaryKey(Courseinfo record);
}