package com.fzutopic.service;

import com.fzutopic.dao.CourseTeacherDao;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.model.CourseTeacherKey;
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
class CourseTeacherServiceImpl implements CourseTeacherService{
    @Resource
    CourseTeacherDao courseTeacherDao;

    @Override
    public boolean insertcourseTearche(CourseTeacherKey courseTeacherKey) {
        courseTeacherDao.insert(courseTeacherKey);
        return true;
    }

    @Override
    public boolean deletecourse(CourseTeacherKey courseTeacherKey) {
        courseTeacherDao.deleteByPrimaryKey(courseTeacherKey);
        return true;
    }
}
