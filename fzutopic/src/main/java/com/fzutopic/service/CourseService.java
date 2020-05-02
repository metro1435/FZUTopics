package com.fzutopic.service;


import com.fzutopic.model.Courseinfo;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseVO;

import java.util.List;

public interface CourseService {

    //转换成courseVO,221701401负责
    CourseVO changeToCourseVO(Courseinfo courseInfo);

    //根据课程名模糊搜索,221701401负责
    List<CourseTeacherInfo> selectByCourse(String name);
}
