package com.fzutopic.view;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

//课程教师信息展示类，221701401负责
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacherInfo {
    private String courseid;
    private String coursename;
    private Double credits;
    private String teacherid;
    private String teachername;
    private String tel;
    private String qq;
    private String college;
    private String info;

    //课程信息和教师信息转换成课程教师信息,221701401负责
    public CourseTeacherInfo changeToCourseTeacherInfo(Teacherinfo teacherinfo, Courseinfo courseinfo) {
        CourseTeacherInfo courseTeacherInfo=new CourseTeacherInfo();
        courseTeacherInfo.setCourseid(courseinfo.getCourseid());
        courseTeacherInfo.setCollege(teacherinfo.getCollege());
        courseTeacherInfo.setCoursename(courseinfo.getCoursename());
        courseTeacherInfo.setCredits(courseinfo.getCredits());
        courseTeacherInfo.setQq(teacherinfo.getQq());
        courseTeacherInfo.setTel(teacherinfo.getTel());
        courseTeacherInfo.setTeacherid(teacherinfo.getTeacherid());
        courseTeacherInfo.setInfo(teacherinfo.getInfo());
        courseTeacherInfo.setTeachername(teacherinfo.getTeachername());
        return courseTeacherInfo;
    }

    //把courseVO转换成课程教师信息,221701401负责
    static public List<CourseTeacherInfo> getCourseTeacherInfo(CourseVO courseVO) {
        List<CourseTeacherInfo> courseTeacherInfos=new ArrayList<>();
        Courseinfo courseinfo=courseVO.getCourseinfo();
        List<Teacherinfo> teacherinfos=courseVO.getTeacherinfo();
        int cnt=teacherinfos.size();
        for (int i=0;i<cnt;i++) {
            CourseTeacherInfo tmp=new CourseTeacherInfo();
            tmp=tmp.changeToCourseTeacherInfo(teacherinfos.get(i),courseinfo);
            courseTeacherInfos.add(tmp);
        }
        return courseTeacherInfos;
    }

    //把teacherVO转换成课程教师信息,221701401负责
    static public List<CourseTeacherInfo> getCourseTeacherInfo(TeacherVO teacherVO) {
        List<CourseTeacherInfo> courseTeacherInfos=new ArrayList<>();
        List<Courseinfo> courseinfos=teacherVO.getCourseinfo();
        Teacherinfo teacherinfo=teacherVO.getTeacherinfo();
        int cnt=courseinfos.size();
        for (int i=0;i<cnt;i++) {
            CourseTeacherInfo tmp=new CourseTeacherInfo();
            tmp=tmp.changeToCourseTeacherInfo(teacherinfo,courseinfos.get(i));
            courseTeacherInfos.add(tmp);
        }
        return courseTeacherInfos;
    }

}
