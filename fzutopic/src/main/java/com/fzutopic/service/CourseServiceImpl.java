package com.fzutopic.service;

import com.fzutopic.dao.CourseTeacherDao;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.model.*;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.CourseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RestController
public class CourseServiceImpl implements CourseService{
    @Resource
    CourseinfoDao courseinfoDao;

    @Resource
    TeacherinfoDao teacherinfoDao;

    @Resource
    CourseTeacherDao courseTeacherDao;

    //把课程信息转换成VO,221701401负责
    public CourseVO changeToCourseVO(Courseinfo courseinfo) {
        CourseTeacherExample courseTeacherExample=new CourseTeacherExample();
        CourseTeacherExample.Criteria criteria=courseTeacherExample.createCriteria();
        criteria.andCourseidEqualTo(courseinfo.getCourseid());
        List<CourseTeacherKey> courseTeacherKeys=courseTeacherDao.selectByExample(courseTeacherExample);
        List<Teacherinfo> teacherinfos=new ArrayList<>();
        for (CourseTeacherKey courseTeacherKey:courseTeacherKeys) {
            Teacherinfo tmp=teacherinfoDao.selectByPrimaryKey(courseTeacherKey.getTeacherid());
            teacherinfos.add(tmp);
        }
        CourseVO courseVO=new CourseVO();
        courseVO.setCourseinfo(courseinfo);
        courseVO.setTeacherinfo(teacherinfos);
        return courseVO;
    }

    //根据课程查询，模糊搜索,221701401负责
    public List<CourseTeacherInfo> selectByCourse(String name) {
        List<Courseinfo> courseinfos=courseinfoDao.selectCourseNameByLike(name);
        List<CourseTeacherInfo> courseTeacherInfos=new ArrayList<>();
        for (Courseinfo courseinfo:courseinfos) {
            CourseVO courseVO=changeToCourseVO(courseinfo);
            List<CourseTeacherInfo> tmp=CourseTeacherInfo.getCourseTeacherInfo(courseVO);
            courseTeacherInfos.addAll(tmp);
        }
        return courseTeacherInfos;
    }
}
