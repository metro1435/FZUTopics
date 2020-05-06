package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.service.CourseService;
import com.fzutopic.service.TeacherService;
import com.fzutopic.view.CourseTeacherInfo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CourseController {
    @Resource(name = "courseServiceImpl")
    CourseService courseService;

    @Resource(name = "teacherServiceImpl")
    TeacherService teacherService;

    /*//测试用接口
    @GetMapping("/test")
    public  @ResponseBody AjaxResponse getCommentById() {
        //List<CourseTeacherInfo> res = courseService.selectByCourse("课"); //按课程搜索在这
        List<CourseTeacherInfo> res = teacherService.selectByTeacher("教"); //按教师搜索在这
        return AjaxResponse.success(res);
    }*/

    //按课程或教师查询课程教师信息，sort，1：课程，2：教师,221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/course/{name}/sort/{sort}/page/{page}")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="name") String name
            ,@PathVariable(name="sort") int sort,@PathVariable(name="page") int page) {
        PageInfo<CourseTeacherInfo> res=new PageInfo<>();
        if (sort==1) res = courseService.selectByCourse(name,page);
        else if (sort==2) res = teacherService.selectByTeacher(name,page);
        else return AjaxResponse.error(400,"无法确定是按教师还是按课程查询");
        if (res.getList().size()==0) return AjaxResponse.error(404,"没有找到相关信息");
        return AjaxResponse.success(res);
    }
}
