package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.CourseinfoExample;
import java.util.List;

import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseTeacherid;
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

    /**
     * @author 呼叫哆啦A梦
     *
     * 根据课程和教师id获取相应信息
     */
    @Select("select teacherinfo.* , courseinfo.* " +
            "from teacherinfo , courseinfo " +
            "where teacherinfo.teacherID=#{teacherID} and courseinfo.courseID=#{courseID}")
    CourseTeacherInfo selectInfoByID(String courseID,String teacherID);

    List<CourseTeacherid> selectAllCourseAndTeacherid();

    int insert(Courseinfo record);

    int insertSelective(Courseinfo record);

    List<Courseinfo> selectByExample(CourseinfoExample example);

    Courseinfo selectByPrimaryKey(String courseid);

    int updateByExampleSelective(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByExample(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByPrimaryKeySelective(Courseinfo record);

    int updateByPrimaryKey(Courseinfo record);
}