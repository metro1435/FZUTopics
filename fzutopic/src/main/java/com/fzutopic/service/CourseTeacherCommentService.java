package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseTeacherCommentService {
    /**
     * 修改课程教师评论表踩赞总数，用户首次踩赞时调用
     *
     * @param commentid 评论表主键
     * @param status    0：用户点赞 1：用户踩
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse insertLikesById(String commentid, int status);

    /**
     * 修改课程教师评论表踩赞总数，用户修改踩赞为赞踩时调用
     *
     * @param commentid 评论表主键
     * @param status    0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse updateLikesById(String commentid, int status);

    /**
     * 修改课程教师评论表踩赞总数，用户取消踩赞时调用
     *
     * @param commentid 评论表主键
     * @param status    0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse deleteLikesById(String commentid, int status);

    //通过课程（教师）id获取课程（教师）评论列表,1403
    List<Ctcomment> getctcomments(String commentitemid);

    //新增课程（教师）评论,1403
    Ctcomment createctcomment(Ctcomment ctcomment);

    //获取待审核课程（教师）评论列表，1403负责
    PageInfo<Ctcomment> getUnauditedCtcomments(int page);

    //管理员审核课程（教师）评论通过，1403负责
    Ctcomment updateCtcommentstatus(String ctcommentid);

    //管理员审核课程（教师）评论不通过直接删除，1403负责
    int deleteunauditedCtcomment(String ctcommentid);
}
