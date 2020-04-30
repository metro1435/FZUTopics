package com.fzutopic.service;

import com.fzutopic.dao.ReplyDao;
import com.fzutopic.model.Reply;
import com.fzutopic.model.ReplyExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class ReplyServiceImpl implements ReplyService{

    @Resource
    ReplyDao replyDao;

    //根据commentid找评论,221701401负责
    public PageInfo<Reply> getRepliesById(String commentid) {
        ReplyExample replyExample=new ReplyExample();
        replyExample.setOrderByClause("time desc");
        ReplyExample.Criteria criteria=replyExample.createCriteria();
        criteria.andCommentidEqualTo(commentid);
        PageHelper.startPage(1,10);
        List<Reply> replies=replyDao.selectByExampleWithBLOBs(replyExample);
        return PageInfo.of(replies);
    }

    //插入reply,221701401负责
    public boolean postReply(Reply reply) {
        return replyDao.insertReply(reply);
    }

    //根据replyid找回复，221701401负责
    public List<Reply> getReplyById(String replyid) {
        ReplyExample replyExample=new ReplyExample();
        ReplyExample.Criteria criteria=replyExample.createCriteria();
        criteria.andReplyidEqualTo(replyid);
        return replyDao.selectByExampleWithBLOBs(replyExample);
    }

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    public boolean insertLikesById(String replyid, int status) {
        int success=0;
        List<Reply> replies= getReplyById(replyid);
        for (Reply reply : replies) {
            if (status == 1) reply.setLikes(reply.getLikes() + 1);
            else if (status == 0) reply.setUnlikes(reply.getUnlikes() + 1);
            success=replyDao.updateByPrimaryKey(reply);
        }
        if (success!=0) return true;
        else return false;
    }

    //修改，对应一方+1一方-1的情况,status：0为修改为踩，1为修改为赞，221701401负责
    public boolean updateLikesById(String replyid, int status) {
        int success=0;
        List<Reply> replies=getReplyById(replyid);
        for (Reply reply : replies) {
            int likes=reply.getLikes(),unlikes=reply.getUnlikes();
            if (status == 1) {
                if (unlikes == 0) return false;
                reply.setLikes(likes + 1);
                reply.setUnlikes(unlikes - 1);
            }
            else if (status == 0) {
                if (likes == 0) return false;
                reply.setUnlikes(unlikes + 1);
                reply.setLikes(likes - 1);
            }
            success=replyDao.updateByPrimaryKey(reply);
        }
        if (success!=0) return true;
        else return false;

    }

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    public boolean deleteLikesById(String topicid, int status) {
        int success=0;
        List<Reply> replies=getReplyById(topicid);
        for (Reply reply : replies) {
            int likes=reply.getLikes(),unlikes=reply.getUnlikes();
            if (status == 1) {
                if (likes == 0) return false;
                reply.setLikes(likes - 1);
            }
            else if (status == 0) {
                if (unlikes == 0) return false;
                reply.setUnlikes(unlikes - 1);
            }
            success=replyDao.updateByPrimaryKey(reply);
        }
        if (success!=0) return true;
        else return false;

    }
}
