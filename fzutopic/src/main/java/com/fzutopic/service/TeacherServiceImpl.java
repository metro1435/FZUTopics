package com.fzutopic.service;

import com.fzutopic.dao.CourseTeacherDao;
import com.fzutopic.dao.CourseinfoDao;
import com.fzutopic.dao.TeacherinfoDao;
import com.fzutopic.model.CourseTeacherExample;
import com.fzutopic.model.CourseTeacherKey;
import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import com.fzutopic.view.CourseTeacherInfo;
import com.fzutopic.view.TeacherVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RestController
public class TeacherServiceImpl implements TeacherService{

    @Resource
    CourseinfoDao courseinfoDao;

    @Resource
    TeacherinfoDao teacherinfoDao;

    @Resource
    CourseTeacherDao courseTeacherDao;

    //转换成teacherVO,221701401负责
    public TeacherVO changeToTeacherVO(Teacherinfo teacherinfo) {
        CourseTeacherExample courseTeacherExample=new CourseTeacherExample();
        CourseTeacherExample.Criteria criteria=courseTeacherExample.createCriteria();
        criteria.andTeacheridEqualTo(teacherinfo.getTeacherid());
        List<CourseTeacherKey> courseTeacherKeys=courseTeacherDao.selectByExample(courseTeacherExample);
        List<Courseinfo> courseinfos=new ArrayList<>();
        for (CourseTeacherKey courseTeacherKey:courseTeacherKeys) {
            Courseinfo tmp=courseinfoDao.selectByPrimaryKey(courseTeacherKey.getCourseid());
            courseinfos.add(tmp);
        }
        TeacherVO teacherVO=new TeacherVO();
        teacherVO.setCourseinfo(courseinfos);
        teacherVO.setTeacherinfo(teacherinfo);
        return teacherVO;
    }

    //根据教师名模糊搜索,221701401负责
    public List<CourseTeacherInfo> selectByTeacher(String name) {
        List<Teacherinfo> teacherinfos=teacherinfoDao.selectTeacherNameByLike(name);
        List<CourseTeacherInfo> courseTeacherInfos=new ArrayList<>();
        for (Teacherinfo teacherinfo:teacherinfos) {
            TeacherVO teacherVO=changeToTeacherVO(teacherinfo);
            List<CourseTeacherInfo> tmp=CourseTeacherInfo.getCourseTeacherInfo(teacherVO);
            courseTeacherInfos.addAll(tmp);
        }
        return courseTeacherInfos;
    }
}
