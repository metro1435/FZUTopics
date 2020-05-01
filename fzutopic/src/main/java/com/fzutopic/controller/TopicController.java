package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Topic;
import com.fzutopic.service.TopicService;
import com.fzutopic.service.TopicServiceImpl;
import com.fzutopic.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TopicController {
    @Resource(name = "topicServiceImpl")
    TopicServiceImpl topicService;
    //新增话题(要加入一个userid参数)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @PostMapping("/user/topic")
    public @ResponseBody AjaxResponse createTopic(@RequestBody Topic topic){
        return AjaxResponse.success(topicService.createTopic(topic));
    }


}
