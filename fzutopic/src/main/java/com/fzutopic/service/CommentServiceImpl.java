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
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentDao commentDao;

    public Comment createComment(Comment comment){
        commentDao.insert1(comment);

        return comment;
    }
}
