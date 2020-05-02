package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Reply;
import com.fzutopic.service.ReplyServiceImpl;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ReplyController {
    @Resource(name = "replyServiceImpl")
    ReplyServiceImpl replyService;

    //根据commentid找对应回复，，221701401负责
    @UserLoginToken
    @GetMapping("/topic/{commentid}/reply")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="commentid") String commentid) {
        if (commentid.isEmpty() || commentid.length()!=23) return AjaxResponse.error(400,"commentid为空或不合规定");
        PageInfo<Reply> pageInfo =replyService.getRepliesById(commentid);
        if (pageInfo.getList().isEmpty()) return AjaxResponse.error(404,"没有回复");
        return AjaxResponse.success(pageInfo);
    }

    //提交用户对评论的回复，221701401负责
    @UserLoginToken
    @PostMapping("/user/topic/comment/reply")
    public @ResponseBody AjaxResponse postReply(@RequestBody Reply reply, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        reply.setUserid(userid);
        boolean postsuccess=true;
        postsuccess=replyService.postReply(reply);
        if (postsuccess) return AjaxResponse.success(reply);
        else return AjaxResponse.error(400,"插入失败");
    }

}
