package com.fzutopic.controller;


import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.service.CommentServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CommentController {

    @Resource(name = "commentServiceImpl")
    CommentServiceImpl commentService;

    //根据topicid找对应评论，221701401负责
    @GetMapping("/topic/{topicid}/comment")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="topicid") String topicid) {
        if (topicid.isEmpty() || topicid.length()!=24) return AjaxResponse.error(400,"topicid为空或不合规定");
        PageInfo<Comment> pageInfo =commentService.getCommentsById(topicid);
        if (pageInfo.getList().isEmpty()) return AjaxResponse.error(404,"没有评论");
        return AjaxResponse.success(pageInfo);
    }

}
