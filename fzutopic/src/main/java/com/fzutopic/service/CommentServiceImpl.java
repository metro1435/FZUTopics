package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.dao.ReplyDao;
import com.fzutopic.dao.TopicDao;
import com.fzutopic.dao.UserDao;
import com.fzutopic.model.*;
import com.fzutopic.view.CommentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RestController
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentDao commentDao;

    @Resource
    UserDao userDao;

    @Resource
    private TopicDao topicDao;

    @Resource(name = "replyServiceImpl")
    ReplyServiceImpl replyService;

    //根据topicid找出对应的评论列表，，221701401负责
    public List<CommentVO> getCommentsById(String topicid) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("time desc");
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andTopicidEqualTo(topicid);
        criteria.andAuditstatusEqualTo(1);
        List<Comment> comments=commentDao.selectByExampleWithBLOBs(commentExample);
        List<CommentVO> list=searchReplyByComment(comments);
        return list;
    }

    public List<CommentVO> searchReplyByComment(List<Comment> comments) {
        List<CommentVO> list=new ArrayList<>();
        for (Comment comment:comments) {
            List<Reply> replies=replyService.getRepliesById(comment.getCommentid());
            User user=userDao.selectByPrimaryKey(comment.getPosterid());
            CommentVO commentVO=CommentVO.changeToCommentVO(comment,user.getNickname(),replies);
            list.add(commentVO);
        }
        return list;
    }

    //获取待审核评论列表，一页16个，221701401负责
    public PageInfo<Comment> getUnauditedComments() {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andAuditstatusEqualTo(0);
        PageHelper.startPage(1, 16);
        List<Comment> comments = commentDao.selectByExampleWithBLOBs(commentExample);
        return PageInfo.of(comments);
    }

    public int deleteComment(String commentid) {
        return commentDao.deleteByPrimaryKey(commentid);
    }

    //更新评论状态为已审核以及附带的操作（评论+1，计算热度），考虑后期加数据回滚，221701401
    public boolean updateComment(String commentid) {
        Comment comment=commentDao.selectByPrimaryKey(commentid);
        Topic topic=topicDao.selectByPrimaryKey(comment.getTopicid());
        int cnt=topic.getCommentcount()+1;
        int heats = (25 * topic.getViews() + 40 * cnt + 25 * topic.getLikes()
                - 5 * topic.getUnlikes()) / 100;
        topic.setCommentcount(cnt);
        topic.setHeats(heats);
        int success=topicDao.updateByPrimaryKeyWithBLOBs(topic);
        comment.setAuditstatus(1);
        success+=commentDao.updateByPrimaryKeyWithBLOBs(comment);
        if (success!=0) return true;
        else return false;
    }

    //1309
    public Comment createComment(Comment comment){
        //Comment comment = new Comment();
        //Topic topic = new Topic();
        int status,heat,count;
        String id;

        commentDao.insert1(comment);

        status = comment.getAuditstatus();
        id = comment.getTopicid();

        Topic topic_id = topicDao.selectByPrimaryKey(id);

        if(status==1)
        {
            count =topic_id.getCommentcount()+1;
            heat =(25*topic_id.getViews()+40*topic_id.getCommentcount()+25*topic_id.getLikes()-5*topic_id.getUnlikes())/100;
            topic_id.setCommentcount(count);
            topic_id.setHeats(heat);
            topicDao.updateByPrimaryKeyWithBLOBs(topic_id);

        }
        return comment;
    }

    /**
     * 修改评论表点赞和踩的总数，用户首次踩赞时调用此方法
     *
     * @param commentid 评论ID主键
     * @param status    0：用户点赞 1：用户踩
     * @return AjaxResponse
     * @Author 呼叫哆啦A梦
     */
    public AjaxResponse insertLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 0);
    }

    /**
     * 修改评论表点赞和踩的总数，用户修改态度时调用此方法
     *
     * @param commentid 评论ID主键
     * @param status    0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    public AjaxResponse updateLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 1);
    }

    /**
     * 修改评论表点赞和踩的总数，用户取消踩赞时调用此方法
     *
     * @param commentid 话题ID主键
     * @param status    0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     */
    public AjaxResponse deleteLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 2);
    }

    /**
     * @param commentid 回复主键
     * @param status
     * @param sort      0：标识用户首次踩赞 1：用户修改踩赞 2：用户取消踩赞
     * @return AjaxResponse
     */
    private AjaxResponse changeRecord(String commentid, int status, int sort) {
        Comment comment = commentDao.selectByPrimaryKey(commentid);
        if (comment == null)
            return AjaxResponse.error(500, "不存在话题评论:" + commentid);

        int likes, unlikes;
        likes = comment.getLikes();
        unlikes = comment.getUnlikes();
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
        comment.setLikes(likes);
        comment.setUnlikes(unlikes);
        commentDao.updateByPrimaryKey(comment);

        message += "  新的likes  unlikes分别为：" + likes + "   " + unlikes;
        return AjaxResponse.success(message);

    }

}
