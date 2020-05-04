package com.fzutopic.service;

import com.fzutopic.view.CourseTeacherInfo;

import java.util.List;

public interface TeacherService {
    //根据教师名模糊搜索,221701401负责
    List<CourseTeacherInfo> selectByTeacher(String name);

}
