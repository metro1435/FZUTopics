package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    //根据topicid找出对应的评论列表
    PageInfo<Comment> getCommentsById(String topicid);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse insertLikesById(String commentid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse updateLikesById(String commentid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse deleteLikesById(String commentid, int status);

}
