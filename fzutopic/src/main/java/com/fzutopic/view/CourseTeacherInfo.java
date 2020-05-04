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

}
