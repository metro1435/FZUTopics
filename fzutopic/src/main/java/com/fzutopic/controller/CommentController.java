package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.service.CommentServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CommentController {

    @Resource(name = "commentServiceImpl")
    CommentServiceImpl commentService;

    //根据topicid找对应评论，221701401负责
    @UserLoginToken
    @GetMapping("/topic/{topicid}/comment")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="topicid") String topicid) {
        if (topicid.isEmpty() || topicid.length()!=24) return AjaxResponse.error(400,"topicid为空或不合规定");
        PageInfo<Comment> pageInfo =commentService.getCommentsById(topicid);
        if (pageInfo.getList().isEmpty()) return AjaxResponse.error(404,"没有评论");
        return AjaxResponse.success(pageInfo);
    }

    //新增用户对帖子进行评论 1309
    @UserLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @PostMapping("/user/topic/comment")
    public @ResponseBody
    AjaxResponse createComment(@RequestBody Comment comment){
        return AjaxResponse.success(commentService.createComment(comment));
    }
}


