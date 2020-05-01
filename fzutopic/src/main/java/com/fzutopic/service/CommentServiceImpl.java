package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.model.CommentExample;
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
public class CommentServiceImpl implements CommentService{
    @Resource
    CommentDao commentDao;

    //根据topicid找出对应的评论列表，，221701401负责
    public PageInfo<Comment> getCommentsById(String topicid) {
        CommentExample commentExample=new CommentExample();
        commentExample.setOrderByClause("time desc");
        CommentExample.Criteria criteria=commentExample.createCriteria();
        criteria.andTopicidEqualTo(topicid);
        PageHelper.startPage(1,20);
        List<Comment> comments=commentDao.selectByExampleWithBLOBs(commentExample);
        return PageInfo.of(comments);
    }

    //根据commentid找评论，221701401负责
    public List<Comment> getCommentById(String commentid) {
        CommentExample commentExample=new CommentExample();
        CommentExample.Criteria criteria=commentExample.createCriteria();
        criteria.andCommentidEqualTo(commentid);
        return commentDao.selectByExampleWithBLOBs(commentExample);
    }

    /**
     * 修改评论表点赞和踩的总数，用户首次点赞（踩）时调用此方法
     * @Author 呼叫哆啦A梦
     * @param commentid 评论ID主键
     * @param status 0：用户点赞 1：用户踩
     * @return AjaxResponse
     */
    public AjaxResponse insertLikesById(String commentid, int status) {
        Comment comment=commentDao.selectByPrimaryKey(commentid);
        if(comment==null)
            return AjaxResponse.error(500, "不存在评论:"+commentid);
        int likes, unlikes;
        likes = comment.getLikes();
        unlikes = comment.getUnlikes();
        if(status==1)   likes+=1;
        else unlikes+=1;
        comment.setUnlikes(unlikes);
        comment.setLikes(likes);
        commentDao.updateByPrimaryKey(comment);
        return AjaxResponse.success();
    }

    /**
     * 修改评论表点赞和踩的总数，用户修改态度时调用此方法
     * @author 呼叫哆啦A梦
     * @param commentid 评论ID主键
     * @param status 0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     */
    public AjaxResponse updateLikesById(String commentid, int status) {
        Comment comment=commentDao.selectByPrimaryKey(commentid);
        if(comment==null)
            return AjaxResponse.error(500, "不存在评论:"+commentid);
        int likes, unlikes;
        likes = comment.getLikes();
        unlikes = comment.getUnlikes();
        if(status==0){
            likes-=1;
            unlikes+=1;
        }else{
            likes+=1;
            unlikes-=1;
        }
        if(likes<0||unlikes<0) return AjaxResponse.error(500,"修改点赞（踩）总数失败，出现小于0");
        else{
            comment.setLikes(likes);
            comment.setUnlikes(unlikes);
            commentDao.updateByPrimaryKey(comment);
            return AjaxResponse.success();
        }

    }

    /**
     * 修改评论表点赞和踩的总数，用户取消点赞（踩）时调用此方法
     * @param commentid 话题ID主键
     * @param status 0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     */
    public AjaxResponse deleteLikesById(String commentid, int status) {
        Comment comment=commentDao.selectByPrimaryKey(commentid);
        if(comment==null)
            return AjaxResponse.error(500, "不存在评论:"+commentid);
        int likes, unlikes;
        likes = comment.getLikes();
        unlikes = comment.getUnlikes();
        if(status==1) likes-=1;
        else unlikes-=1;
        if(likes<0||unlikes<0) return AjaxResponse.error(500,"修改点赞（踩）总数失败，出现小于0");
        else{
            comment.setLikes(likes);
            comment.setUnlikes(unlikes);
            commentDao.updateByPrimaryKey(comment);
            return AjaxResponse.success();
        }

    }

}
