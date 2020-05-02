package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.dao.ReplyDao;
import com.fzutopic.dao.TopicDao;
import com.fzutopic.model.*;
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
public class ReplyServiceImpl implements ReplyService {

    @Resource
    ReplyDao replyDao;

    @Resource
    CommentDao commentDao;

    @Resource
    TopicDao topicDao;


    //根据commentid找评论,221701401负责
    public PageInfo<Reply> getRepliesById(String commentid) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.setOrderByClause("time desc");
        ReplyExample.Criteria criteria = replyExample.createCriteria();
        criteria.andCommentidEqualTo(commentid);
        criteria.andAuditstatusEqualTo(1);
        PageHelper.startPage(1, 10);
        List<Reply> replies = replyDao.selectByExampleWithBLOBs(replyExample);
        return PageInfo.of(replies);
    }

    //插入reply,221701401负责
    public boolean postReply(Reply reply) {
        if (reply.getAuditstatus() == 1) {
            int replyStatus = 0;
            Comment comment = commentDao.selectByPrimaryKey(reply.getCommentid());
            if (comment == null) return false;
            Topic topic = topicDao.selectByPrimaryKey(comment.getTopicid());
            if (topic == null) return false;
            if (comment.getIsreply() == 0) {
                replyStatus = 1;
                comment.setIsreply(1);
            }
            int cnt = topic.getCommentcount() + 1;
            topic.setCommentcount(cnt);
            int heats = (25 * topic.getViews() + 40 * cnt + 25 * topic.getLikes()
                    - 5 * topic.getUnlikes()) / 100;
            topic.setHeats(heats);
            int topicStatus, commentStatus;
            topicStatus = topicDao.updateByPrimaryKeyWithBLOBs(topic);
            if (replyStatus != 0) {
                commentStatus = commentDao.updateByPrimaryKeyWithBLOBs(comment);
                if (commentStatus == 0) return false;
            }
            if (topicStatus == 0) return false;
        }
        return replyDao.insertReply(reply);
    }

    /**
     * 修改回复表点赞和踩的总数，用户首次踩赞时调用此方法
     *
     * @param replyid 回复ID主键
     * @param status  0：用户点赞 1：用户踩
     * @return AjaxResponse
     * @Author 呼叫哆啦A梦
     */
    public AjaxResponse insertLikesById(String replyid, int status) {
        return changeRecord(replyid, status, 0);
    }

    /**
     * 修改回复表点赞和踩的总数，用户修改态度时调用此方法
     *
     * @param replyid 评论ID主键
     * @param status  0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    public AjaxResponse updateLikesById(String replyid, int status) {
        return changeRecord(replyid, status, 1);
    }

    /**
     * 修改回复表点赞和踩的总数，用户取消点踩赞时调用此方法
     *
     * @param replyid 话题ID主键
     * @param status  0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     */
    public AjaxResponse deleteLikesById(String replyid, int status) {
        return changeRecord(replyid, status, 2);
    }


    /**
     * @param replyid 回复主键
     * @param status
     * @param sort    0：标识用户首次踩赞 1：用户修改踩赞 2：用户取消踩赞
     * @return AjaxResponse
     */
    private AjaxResponse changeRecord(String replyid, int status, int sort) {
        Reply reply = replyDao.selectByPrimaryKey(replyid);
        if (reply == null)
            return AjaxResponse.error(500, "不存在回复:" + replyid);

        int likes, unlikes;
        likes = reply.getLikes();
        unlikes = reply.getUnlikes();
        String message = "旧likes unlikes分别为 " + likes + "  " + unlikes;

        if (sort == 0) {
            //用户首次踩赞
            if (status == 1) likes += 1;
            else unlikes += 1;
        } else if (sort == 1) {
            //用户修改踩赞
            if (status == 0) {
                likes -= 1;
                unlikes += 1;
            } else {
                likes += 1;
                unlikes -= 1;
            }
        } else {
            //用户取消踩赞
            if (status == 1) likes -= 1;
            else unlikes -= 1;
        }
        if (likes < 0 || unlikes < 0) return AjaxResponse.error(500, "修改点赞（踩）总数失败，出现小于0");
        reply.setLikes(likes);
        reply.setUnlikes(unlikes);
        replyDao.updateByPrimaryKey(reply);

        message += "  新的likes  unlikes分别为：" + likes + "   " + unlikes;
        return AjaxResponse.success(message);

    }
}
