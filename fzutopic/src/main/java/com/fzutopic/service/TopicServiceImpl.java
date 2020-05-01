package com.fzutopic.service;

import com.fzutopic.dao.TopicDao;
import com.fzutopic.dao.UserDao;
import com.fzutopic.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Service
@RestController
public class TopicServiceImpl implements TopicService{
    @Resource
    private TopicDao topicDao;

    public Topic createTopic(Topic topic){
        topicDao.insert1(topic);

        return topic;
    }

}
