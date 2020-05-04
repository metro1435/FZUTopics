package com.fzutopic.service;

import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.view.CourseTeacherInfo;
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
    public List<CourseTeacherInfo> selectByTeacher(String name) {
        return teacherinfoDao.selectTeacherNameByLike(name);
    }
}
