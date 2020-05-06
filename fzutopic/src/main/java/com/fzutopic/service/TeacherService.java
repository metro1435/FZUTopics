package com.fzutopic.service;

import com.fzutopic.view.CourseTeacherInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherService {
    //根据教师名模糊搜索,221701401负责
    PageInfo<CourseTeacherInfo> selectByTeacher(String name,int page);

}
