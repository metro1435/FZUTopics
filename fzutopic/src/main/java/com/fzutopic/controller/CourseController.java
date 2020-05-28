package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CourseTeacherDao;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.model.*;
import com.fzutopic.service.CourseService;
import com.fzutopic.service.CourseTeacherService;
import com.fzutopic.service.TagServiceImpl;
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
public class CourseController {
    @Resource(name = "courseServiceImpl")
    CourseService courseService;

    @Resource(name = "teacherServiceImpl")
    TeacherService teacherService;

    @Resource(name = "courseTeacherServiceImpl")
    CourseTeacherService courseTeacherService;

    @Resource(name = "tagServiceImpl")
    TagServiceImpl tagService;

    @Resource
    CourseinfoDao courseinfoDao;

    @Resource
    TeacherinfoDao teacherinfoDao;

    @Resource
    CourseTeacherDao courseTeacherDao;

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
    public @ResponseBody
    AjaxResponse getCommentById(@PathVariable(name = "name") String name
            , @PathVariable(name = "sort") int sort, @PathVariable(name = "page") int page) {
        PageInfo<CourseTeacherInfo> res = new PageInfo<>();
        if (sort == 1) res = courseService.selectByCourse(name, page);
        else if (sort == 2) res = teacherService.selectByTeacher(name, page);
        else return AjaxResponse.error(400, "无法确定是按教师还是按课程查询");
        if (res.getList().size() == 0) return AjaxResponse.error(404, "没有找到相关信息");
        return AjaxResponse.success(res);
    }

    /**
     * 根据课程ID和教师ID获取信息
     *
     * @param courseid  课程ID
     * @param teacherid 教师ID
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/ctinfo/{courseid}/{teacherid}")
    public AjaxResponse getCTinfo(@PathVariable(name = "courseid") String courseid
            , @PathVariable(name = "teacherid") String teacherid) {
        if (courseinfoDao.selectByPrimaryKey(courseid) == null)
            return AjaxResponse.error(500, "课程ID不存在");
        if (teacherinfoDao.selectByPrimaryKey(teacherid) == null)
            return AjaxResponse.error(500, "教师ID不存在");
        CourseTeacherExample courseTeacherExample = new CourseTeacherExample();
        courseTeacherExample.createCriteria().andCourseidEqualTo(courseid).andTeacheridEqualTo(teacherid);
        if (courseTeacherDao.selectByExample(courseTeacherExample).isEmpty())
            return AjaxResponse.error(500, "此课程：" + courseid + "-教师：" + teacherid + "对应关系不存在");

        CourseTeacherInfo courseTeacherInfo = new CourseTeacherInfo();
        courseTeacherInfo = courseService.selectInfoByID(courseid, teacherid);
        return AjaxResponse.success(courseTeacherInfo);
    }

    //221701416管理员获取所有课程
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/getcourse")
    public AjaxResponse getAllCourse(){
        return AjaxResponse.success(courseService.selectAllCourseAndTeacherid());
    }

    //221701416管理员新增课程
    @AdminLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @PostMapping("/admin/addcourse")
    public AjaxResponse insertcourse(@RequestBody CourseTeacherid courseTeacherid){
        Courseinfo courseinfo=new Courseinfo();
        courseinfo.setCourseid(courseTeacherid.getCourseid());
        courseinfo.setCoursename(courseTeacherid.getCoursename());
        courseinfo.setCredits(courseTeacherid.getCredits());
        String courseid=courseService.insertcourse(courseinfo);
        CourseTeacherKey courseTeacherKey=new CourseTeacherKey();
        courseTeacherKey.setCourseid(courseid);
        courseTeacherKey.setTeacherid(courseTeacherid.getTeacherid());
        courseTeacherService.insertcourseTeacher(courseTeacherKey);
        return AjaxResponse.success("新增课程成功");
    }

    //221701416删除课程
    @AdminLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @DeleteMapping("/admin/deletecourse")
    public AjaxResponse deletecourse(@RequestBody CourseTeacherKey courseTeacherKey){
        courseTeacherService.deletecourse(courseTeacherKey);
        courseService.deletecourse(courseTeacherKey);
        return AjaxResponse.success("删除课程成功");
    }

    //221701416编辑更新课程
    @AdminLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @PutMapping("/admin/updatecourse")
    public AjaxResponse updatecourse(@RequestParam(name = "courseid") String courseid,
                                     @RequestParam(name = "credit") double credit,
                                     @RequestParam(name = "teacherid") String teacherid
                                     ){
        CourseTeacherKey courseTeacherKey=new CourseTeacherKey();
        courseTeacherKey.setCourseid(courseid);
        courseTeacherKey.setTeacherid(teacherid);
        courseTeacherService.updatecourse(courseTeacherKey);
        Courseinfo courseinfo=new Courseinfo();
        courseinfo.setCourseid(courseid);
        courseinfo.setCredits(credit);
        courseService.updatecourse(courseinfo);
        return AjaxResponse.success("更新课程成功");
    }
}
