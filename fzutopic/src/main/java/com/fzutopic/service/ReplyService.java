package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Reply;
import com.fzutopic.model.Topic;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ReplyService {

    //根据commentid找回复,221701401负责
    List<Reply> getRepliesById(String commentid);

    //插入reply,221701401负责
    boolean postReply(Reply reply);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，，221701401负责
    AjaxResponse insertLikesById(String replyid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞，，221701401负责
    AjaxResponse updateLikesById(String replyid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，，221701401负责
    AjaxResponse deleteLikesById(String replyid, int status);

    //管理员获取待审核回复列表，1416负责
    PageInfo<Reply> getunauditedReplys(int page);

    //管理员审核回复通过，1416负责
    Reply updateReplystatus(String replyid);

    //管理员审核回复不通过直接删除，1416负责
    int deleteunauditedReply(String replyid);
}
