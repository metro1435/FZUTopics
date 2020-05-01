package com.fzutopic.service;

import com.fzutopic.dao.ReplyDao;
import com.fzutopic.model.AjaxResponse;
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
        criteria.andAuditstatusEqualTo(1);
        PageHelper.startPage(1,10);
        List<Reply> replies=replyDao.selectByExampleWithBLOBs(replyExample);
        return PageInfo.of(replies);
    }

    //插入reply,221701401负责
    public boolean postReply(Reply reply) {
        return replyDao.insertReply(reply);
    }

    /**
     * 修改回复表点赞和踩的总数，用户首次点赞（踩）时调用此方法
     * @Author 呼叫哆啦A梦
     * @param replyid 回复ID主键
     * @param status 0：用户点赞 1：用户踩
     * @return AjaxResponse
     */
    public AjaxResponse insertLikesById(String replyid, int status) {
        Reply reply=replyDao.selectByPrimaryKey(replyid);
        if(reply==null)
            return AjaxResponse.error(500, "不存在回复:"+replyid);
        int likes,unlikes;
        likes = reply.getLikes();
        unlikes = reply.getUnlikes();
        if(status==1)   likes+=1;
        else unlikes+=1;
        reply.setUnlikes(unlikes);
        reply.setLikes(likes);
        replyDao.updateByPrimaryKey(reply);
        return AjaxResponse.success();

    }

    /**
     * 修改回复表点赞和踩的总数，用户修改态度时调用此方法
     * @author 呼叫哆啦A梦
     * @param replyid 评论ID主键
     * @param status 0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     */
    public AjaxResponse updateLikesById(String replyid, int status) {
        Reply reply=replyDao.selectByPrimaryKey(replyid);
        if(reply==null)
            return AjaxResponse.error(500, "不存在回复:"+replyid);
        int likes, unlikes;
        likes = reply.getLikes();
        unlikes = reply.getUnlikes();
        if(status==0){
            likes-=1;
            unlikes+=1;
        }else{
            likes+=1;
            unlikes-=1;
        }
        if(likes<0||unlikes<0) return AjaxResponse.error(500,"修改点赞（踩）总数失败，出现小于0");
        else{
            reply.setLikes(likes);
            reply.setUnlikes(unlikes);
            replyDao.updateByPrimaryKey(reply);
            return AjaxResponse.success();
        }

    }

    /**
     * 修改回复表点赞和踩的总数，用户取消点赞（踩）时调用此方法
     * @param replyid 话题ID主键
     * @param status 0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     */
    public AjaxResponse deleteLikesById(String replyid, int status) {
        Reply reply=replyDao.selectByPrimaryKey(replyid);
        if(reply==null)
            return AjaxResponse.error(500, "不存在回复:"+replyid);
        int likes, unlikes;
        likes = reply.getLikes();
        unlikes = reply.getUnlikes();
        if(status==1) likes-=1;
        else unlikes-=1;
        if(likes<0||unlikes<0) return AjaxResponse.error(500,"修改点赞（踩）总数失败，出现小于0");
        else{
            reply.setLikes(likes);
            reply.setUnlikes(unlikes);
            replyDao.updateByPrimaryKey(reply);
            return AjaxResponse.success();
        }
    }
}
