package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.dao.CtcommentDao;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.model.CtcommentExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class CtcommentServiceImpl {
    @Resource
    private CtcommentDao ctcommentDao;

    //实现通过课程（教师）id获取课程（教师）评论列表
    public List<Ctcomment> getctcomments(String commentitemid){
        CtcommentExample example=new CtcommentExample();
        CtcommentExample.Criteria criteria=example.createCriteria();
        criteria.andCommentitemidEqualTo(commentitemid);
        example.setOrderByClause("commentid desc");
        example.setLimit(15);
        return ctcommentDao.selectByExample(example);
    }

    //实现新增课程（教师）评论
    public Ctcomment createctcomment(Ctcomment ctcomment){
        ctcommentDao.insertByCtcomment(ctcomment);
        ctcomment.setCommentid(ctcomment.getCommentid());
        ctcomment.setText(ctcomment.getText());
        ctcomment.setLikes(ctcomment.getLikes());
        ctcomment.setUnlikes(ctcomment.getUnlikes());
        ctcomment.setTime(ctcomment.getTime());
        ctcomment.setIsanony(ctcomment.getIsanony());
        ctcomment.setUserid(ctcomment.getUserid());
        ctcomment.setCommentitemid(ctcomment.getCommentid());
        ctcomment.setAuditstatus(ctcomment.getAuditstatus());
        return ctcomment;
    }
}
