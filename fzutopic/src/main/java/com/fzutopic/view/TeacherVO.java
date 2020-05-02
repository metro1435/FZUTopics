package com.fzutopic.view;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.Teacherinfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

//按教师查询信息转换类.221701401负责
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVO {
    private List<Courseinfo> courseinfo;
    private Teacherinfo teacherinfo;
}
