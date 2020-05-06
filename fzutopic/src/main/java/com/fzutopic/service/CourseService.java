package com.fzutopic.service;


import com.fzutopic.view.CourseTeacherInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {

    //根据课程名模糊搜索,221701401负责
    PageInfo<CourseTeacherInfo> selectByCourse(String name,int page);
}
