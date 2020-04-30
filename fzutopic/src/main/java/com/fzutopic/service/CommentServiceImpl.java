package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
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

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    public boolean insertLikesById(String commentid, int status) {
        int success=0;
        List<Comment> comments=getCommentById(commentid);
        for (Comment comment:comments) {
            if (status == 1) comment.setLikes(comment.getLikes() + 1);
            else if (status == 0) comment.setUnlikes(comment.getUnlikes() + 1);
            success=commentDao.updateByPrimaryKey(comment);
        }
        if (success!=0) return true;
        else return false;
    }

    //修改，对应一方+1一方-1的情况,status：0为修改为踩，1为修改为赞，221701401负责
    public boolean updateLikesById(String commentid, int status) {
        int success=0;
        List<Comment> comments=getCommentById(commentid);
        for (Comment comment:comments) {
            int likes=comment.getLikes(),unlikes=comment.getUnlikes();
            if (status == 1) {
                if (unlikes == 0) return false;
                comment.setLikes(likes + 1);
                comment.setUnlikes(unlikes - 1);
            }
            else if (status == 0) {
                if (likes == 0) return false;
                comment.setUnlikes(unlikes + 1);
                comment.setLikes(likes - 1);
            }
            success=commentDao.updateByPrimaryKey(comment);
        }
        if (success!=0) return true;
        else return false;

    }

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    public boolean deleteLikesById(String commentid, int status) {
        int success=0;
        List<Comment> comments=getCommentById(commentid);
        for (Comment comment:comments) {
            int likes=comment.getLikes(),unlikes=comment.getUnlikes();
            if (status == 1) {
                if (likes == 0) return false;
                comment.setLikes(likes - 1);
            }
            else if (status == 0) {
                if (unlikes == 0) return false;
                comment.setUnlikes(unlikes - 1);
            }
            success=commentDao.updateByPrimaryKey(comment);
        }
        if (success!=0) return true;
        else return false;

    }

}
