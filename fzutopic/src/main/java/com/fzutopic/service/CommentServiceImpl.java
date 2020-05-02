package com.fzutopic.service;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.dao.TopicDao;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Service
@RestController
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private TopicDao topicDao;

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
}
