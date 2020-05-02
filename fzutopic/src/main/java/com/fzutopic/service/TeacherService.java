package com.fzutopic.service;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseVO;
import com.fzutopic.view.TeacherVO;

import java.util.List;

public interface TeacherService {
    //转换成teacherVO,221701401负责
    TeacherVO changeToTeacherVO(Teacherinfo teacherinfo);

    //根据教师名模糊搜索,221701401负责s
    List<CourseTeacherInfo> selectByTeacher(String name);

}
