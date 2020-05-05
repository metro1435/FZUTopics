package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.AdminLoginToken;
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
    @CrossOrigin
    @GetMapping("/topic/{topicid}/comment")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="topicid") String topicid) {
        if (topicid.isEmpty() || topicid.length()!=24) return AjaxResponse.error(400,"topicid为空或不合规定");
        PageInfo<Comment> pageInfo =commentService.getCommentsById(topicid);
        if (pageInfo.getList().isEmpty()) return AjaxResponse.error(404,"没有评论");
        return AjaxResponse.success(pageInfo);
    }

    //获取待审核评论，221701401负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/comment/unaudited")
    public  @ResponseBody AjaxResponse getUnauditedComment() {
        PageInfo<Comment> pageInfo =commentService.getUnauditedComments();
        if (pageInfo.getList().isEmpty()) return AjaxResponse.error(404,"没有待审核评论");
        return AjaxResponse.success(pageInfo);
    }

    //审核评论（通过/不通过），221701401负责
    @AdminLoginToken
    @CrossOrigin
    @PutMapping("/admin/comment/unaudited")
    public @ResponseBody AjaxResponse CommentAudit(@RequestParam String commmentid,
                                                   @RequestParam int auditstatus) {
        if (auditstatus==1) {
            boolean success=commentService.updateComment(commmentid);
            if (success) return AjaxResponse.success("操作成功");
            else return AjaxResponse.error(500,"操作失败");
        }
        else if (auditstatus==0) {
            int success=commentService.deleteComment(commmentid);
            if (success==0) return AjaxResponse.error(500,"删除失败");
            else return AjaxResponse.success("删除成功");
        }
        else return AjaxResponse.error(400,"审核状态异常");
    }


    //新增用户对帖子进行评论 1309
    @UserLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @PostMapping("/user/topic/comment")
    public @ResponseBody
    AjaxResponse createComment(@RequestBody Comment comment){
        return AjaxResponse.success(commentService.createComment(comment));
    }


}


