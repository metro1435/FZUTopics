package com.fzutopic.controller;


import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Reply;
import com.fzutopic.service.ReplyServiceImpl;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class ReplyController {
    @Resource(name = "replyServiceImpl")
    ReplyServiceImpl replyService;

    //根据commentid找对应回复，，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/topic/{commentid}/reply")
    public  @ResponseBody AjaxResponse getCommentById(@PathVariable(name="commentid") String commentid) {
        if (commentid.isEmpty() || commentid.length()!=23) return AjaxResponse.error(400,"commentid为空或不合规定");
        List<Reply> replies =replyService.getRepliesById(commentid);
        if (replies.isEmpty()) return AjaxResponse.error(404,"没有回复");
        return AjaxResponse.success(replies);
    }

    //提交用户对评论的回复，221701401负责
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/user/topic/comment/reply")
    public @ResponseBody AjaxResponse postReply(@RequestBody Reply reply,
                                                HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        reply.setUserid(userid);
        boolean postsuccess=true;
        postsuccess=replyService.postReply(reply);
        if (postsuccess) return AjaxResponse.success(reply);
        else return AjaxResponse.error(400,"插入失败");
    }

    //获取待审核回复列表，1416负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/reply/unaudited/page/{page}")
    public  @ResponseBody AjaxResponse getUnauditedReplys(@PathVariable(name="page") int page) {
        PageInfo<Reply> replys =replyService.getunauditedReplys(page);
        if (replys.getList().isEmpty()) return AjaxResponse.error(404,"没有待审核回复");
        return AjaxResponse.success(replys);
    }

    /**
     * 审核回复（选择通过或不通过）
     * 221701416改
     * @param replyid     回复的id
     * @param auditstatus 0：审核不通过 1：审核通过
     * @return
     */
    @AdminLoginToken
    @CrossOrigin
    @PutMapping("/admin/reply/unaudited")
    public AjaxResponse ReplyAudit(@RequestParam String replyid,
                                   @RequestParam int auditstatus) {
        if (auditstatus == 1)
            return AjaxResponse.success(replyService.updateReplystatus(replyid));
        else if (auditstatus==0)
            return AjaxResponse.success(replyService.deleteunauditedReply(replyid));
        else
            return AjaxResponse.error(400,"审核状态异常");
    }
}
