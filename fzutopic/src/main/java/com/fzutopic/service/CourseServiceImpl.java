package com.fzutopic.service;

import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseTeacherid;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class CourseServiceImpl implements CourseService{
    @Resource
    CourseinfoDao courseinfoDao;

    //根据课程查询，模糊搜索,221701401负责
    public PageInfo<CourseTeacherInfo> selectByCourse(String name,int page) {
        PageHelper.startPage(page, 10);
        List<CourseTeacherInfo> infos=courseinfoDao.selectCourseNameByLike(name);
        return PageInfo.of(infos);
    }

    @Override
    public CourseTeacherInfo selectInfoByID(String courseID, String teacherID) {
        return courseinfoDao.selectInfoByID(courseID,teacherID);
    }
    //221701416，管理员列出所有课程和教师id
    @Override
    public List<CourseTeacherid> selectAllCourseAndTeacherid() {
        return courseinfoDao.selectAllCourseAndTeacherid();
    }
}
