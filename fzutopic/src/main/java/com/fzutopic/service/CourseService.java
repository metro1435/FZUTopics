package com.fzutopic.service;


import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseTeacherid;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {

    //根据课程名模糊搜索,221701401负责
    PageInfo<CourseTeacherInfo> selectByCourse(String name,int page);

    /**
     * 根据课程和教师ID获取相应信息
     * @param courseID 课程ID
     * @param teacherID 教师ID
     * @author 呼叫哆啦A梦
     */
    CourseTeacherInfo selectInfoByID(String courseID,String teacherID);

    //221701416增
    List<CourseTeacherid> selectAllCourseAndTeacherid();
}
