package com.fzutopic.service;

import com.fzutopic.dao.CommentlikesDao;
import com.fzutopic.dao.TopiclikesDao;
import com.fzutopic.model.Commentlikes;
import com.fzutopic.model.CommentlikesKey;
import com.fzutopic.model.Topiclikes;
import com.fzutopic.model.TopiclikesKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Service
@RestController
@Slf4j
public class LikesServiceImpl implements LikesService {

    @Resource
    private TopiclikesDao topiclikesDao;
    @Resource
    private CommentlikesDao commentlikesDao;

    @Override
    public void deleteTopiclikes(TopiclikesKey key) {
        topiclikesDao.deleteByPrimaryKey(key);
    }

    @Override
    public Topiclikes updateTopicLikes(Topiclikes topiclikes) {
        topiclikesDao.updateByPrimaryKey(topiclikes);
        return topiclikes;
    }

    @Override
    public Topiclikes postTopicLikes(Topiclikes topiclikes) {
        topiclikesDao.insert1(topiclikes);
        return topiclikes;
    }

    @Override
    public void deleteCommentlikes(CommentlikesKey key) {
        commentlikesDao.deleteByPrimaryKey(key);
    }

    @Override
    public Commentlikes updateCommentLikes(Commentlikes commentlikes) {
        commentlikesDao.updateByPrimaryKey(commentlikes);
        return commentlikes;
    }

    @Override
    public Commentlikes postCommentLikes(Commentlikes commentlikes) {
        commentlikesDao.insert1(commentlikes);
        return commentlikes;
    }
}
