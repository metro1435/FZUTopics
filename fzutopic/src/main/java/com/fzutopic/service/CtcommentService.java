package com.fzutopic.service;

import com.fzutopic.model.Comment;
import com.fzutopic.model.CourseTeacherKey;
import com.fzutopic.model.Ctcomment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CtcommentService {
    //通过课程（教师）id获取课程（教师）评论列表,1403
    List<Ctcomment> getctcomments(String commentitemid);

    //新增课程（教师）评论,1403
    Ctcomment createctcomment(Ctcomment ctcomment);
}
