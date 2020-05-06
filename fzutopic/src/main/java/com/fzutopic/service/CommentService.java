package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    //根据topicid找出对应的评论列表，221701401负责
    List<Comment> getCommentsById(String topicid);

    //获取待审核评论列表，221701401负责
    PageInfo<Comment> getUnauditedComments();

    //删除评论，221701401
    int deleteComment(String commentid);

    //更新评论状态为通过以及关联修改，221701401
    boolean updateComment(String commnetid);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞
    AjaxResponse insertLikesById(String commentid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞
    AjaxResponse updateLikesById(String commentid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞
    AjaxResponse deleteLikesById(String commentid, int status);

}
