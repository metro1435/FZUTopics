package com.fzutopic.controller;

import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CourseTeacherDao;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.CourseTeacherExample;
import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import com.fzutopic.service.CourseService;
import com.fzutopic.service.TeacherService;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseTeacherid;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TeacherController {
    @Resource(name = "courseServiceImpl")
    CourseService courseService;

    @Resource(name = "teacherServiceImpl")
    TeacherService teacherService;

    @Resource
    CourseinfoDao courseinfoDao;

    @Resource
    TeacherinfoDao teacherinfoDao;

    @Resource
    CourseTeacherDao courseTeacherDao;


    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/getteacher")
    public AjaxResponse getAllTeacher(){
        return AjaxResponse.success(teacherService.selectAllTeacher());
    }
}
