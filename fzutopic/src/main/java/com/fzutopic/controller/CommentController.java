package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Topic;
import com.fzutopic.service.CommentServiceImpl;
import com.fzutopic.service.TopicServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CommentController {
    @Resource(name = "commentServiceImpl")
    CommentServiceImpl commentService;

    //新增用户对帖子进行评论
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @PostMapping("/user/topic/comment")
    public @ResponseBody AjaxResponse createComment(@RequestBody Comment comment){
        return AjaxResponse.success(commentService.createComment(comment));
    }
}


