package com.fzutopic.dao;

import com.fzutopic.model.CourseTeacherExample;
import com.fzutopic.model.CourseTeacherKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseTeacherDao {
    long countByExample(CourseTeacherExample example);

    int deleteByExample(CourseTeacherExample example);

    int deleteByPrimaryKey(CourseTeacherKey key);

    int insert(CourseTeacherKey record);

    int insertSelective(CourseTeacherKey record);

    List<CourseTeacherKey> selectByExample(CourseTeacherExample example);

    int updateByExampleSelective(@Param("record") CourseTeacherKey record, @Param("example") CourseTeacherExample example);

    int updateByExample(@Param("record") CourseTeacherKey record, @Param("example") CourseTeacherExample example);
}