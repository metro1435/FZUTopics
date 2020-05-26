package com.fzutopic.service;

import com.fzutopic.model.CourseTeacherKey;
import com.fzutopic.model.Courseinfo;
import com.fzutopic.view.CourseTeacherid;

public interface CourseTeacherService {
    //221701416增
    boolean insertcourseTearche(CourseTeacherKey courseTeacherKey);

    //221701416增
    boolean deletecourse(CourseTeacherKey courseTeacherKey);

    //221701416增
    boolean updatecourse(CourseTeacherKey courseTeacherKey);

}
