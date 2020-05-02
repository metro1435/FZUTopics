package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.CourseinfoExample;
import java.util.List;

import com.fzutopic.model.Topic;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseVO;
import org.apache.ibatis.annotations.*;

public interface CourseinfoDao {
    long countByExample(CourseinfoExample example);

    int deleteByExample(CourseinfoExample example);

    int deleteByPrimaryKey(String courseid);

    //模糊搜索，221701401负责
    @Select({"select * from courseinfo where coursename like CONCAT('%',#{0},'%')"})
    List<Courseinfo> selectCourseNameByLike(String name);

    int insert(Courseinfo record);

    int insertSelective(Courseinfo record);

    List<Courseinfo> selectByExample(CourseinfoExample example);

    Courseinfo selectByPrimaryKey(String courseid);

    int updateByExampleSelective(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByExample(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByPrimaryKeySelective(Courseinfo record);

    int updateByPrimaryKey(Courseinfo record);
}