package com.fzutopic.dao;

import com.fzutopic.model.CourseTeacherExample;
import com.fzutopic.model.CourseTeacherKey;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CourseTeacherDao {
    long countByExample(CourseTeacherExample example);

    int deleteByExample(CourseTeacherExample example);

    int deleteByPrimaryKey(CourseTeacherKey key);

//    @Insert({"insert into course_teacher(courseID,teacherID) " +
//            "values(#{courseid},#{teacherid})"})
    int insert(CourseTeacherKey record);

    int insertSelective(CourseTeacherKey record);

    int updatecourse(CourseTeacherKey record);

    List<CourseTeacherKey> selectByExample(CourseTeacherExample example);

    int updateByExampleSelective(@Param("record") CourseTeacherKey record, @Param("example") CourseTeacherExample example);

    int updateByExample(@Param("record") CourseTeacherKey record, @Param("example") CourseTeacherExample example);
}