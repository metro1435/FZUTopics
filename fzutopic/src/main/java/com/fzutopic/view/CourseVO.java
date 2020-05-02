package com.fzutopic.view;

import com.fzutopic.model.CourseTeacherExample;
import com.fzutopic.model.CourseTeacherKey;
import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

//按课程查询信息转换类，221701401负责
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVO {
    private Courseinfo courseinfo;
    private List<Teacherinfo> teacherinfo;
}
