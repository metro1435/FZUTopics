package com.fzutopic.service;

import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.TeacherName;
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
public class TeacherServiceImpl implements TeacherService{

    @Resource
    TeacherinfoDao teacherinfoDao;

    //根据教师名模糊搜索,221701401负责
    public PageInfo<CourseTeacherInfo> selectByTeacher(String name,int page) {
        PageHelper.startPage(page, 10);
        List<CourseTeacherInfo> infos=teacherinfoDao.selectTeacherNameByLike(name);
        return PageInfo.of(infos);
    }

    @Override
    public List<TeacherName> selectAllTeacher() {
        return teacherinfoDao.selectAllTeacher();
    }
}
