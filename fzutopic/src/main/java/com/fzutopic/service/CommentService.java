package com.fzutopic.service;

import com.fzutopic.model.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    //根据topicid找出对应的评论列表
    PageInfo<Comment> getCommentsById(String topicid);

    //根据commentid找评论（example返回的是列表=-=我也莫的法子），221701401负责
    List<Comment> getCommentById(String commentid);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    boolean insertLikesById(String commentid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞，221701401负责
    boolean updateLikesById(String commentid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    boolean deleteLikesById(String commentid, int status);

}
