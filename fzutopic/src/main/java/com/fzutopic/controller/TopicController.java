package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Topic;
import com.fzutopic.service.TopicServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class TopicController {
    @Resource(name = "topicServiceImpl")
    TopicServiceImpl topicService;

    //获取所有话题，1组16个，热度倒序，使用pagehelper分页，221701401负责
    //前端操作可参考 https://blog.csdn.net/ftlnnl/article/details/104972751
    @UserLoginToken
    @GetMapping("/topic")
    public AjaxResponse getTopic() {
        PageInfo<Topic> topics = topicService.getTopics();
        if (topics.getList().isEmpty()) return AjaxResponse.error(404,"话题库没有话题");
        return AjaxResponse.success(topics);
    }

    //获取指定id的话题，考虑到使用情况，内置了浏览量加1的操作，221701401负责
    @UserLoginToken
    @GetMapping("/topic/topicid/{topicid}")
    public  @ResponseBody AjaxResponse getTopicById(@PathVariable(name="topicid") String topicid) {
        if (topicid.isEmpty() || topicid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        List<Topic> topic = topicService.getTopicByID(topicid);
        if (topic.size()==0) return AjaxResponse.error(404,"没有找到该id对应话题");
        //else这一行是浏览量加1.不需要的话注释掉即可
        else topic=topicService.updateViews(topic);
        return AjaxResponse.success(topic);
    }

    //获取指定tag的话题，1组16个，热度倒序，221701401负责
    @UserLoginToken
    @GetMapping("/topic/{tagid}")
    public  @ResponseBody AjaxResponse getTopicByTag(@PathVariable(name="tagid") String tagid) {
        if (tagid.isEmpty() || tagid.length()>5) return AjaxResponse.error(400,"tagid为空或不合规定");
        PageInfo<Topic> topics = topicService.getTopicsByTag(tagid);
        if (topics.getList().isEmpty()) return AjaxResponse.error(404,"找不到tag对应的话题");
        return AjaxResponse.success(topics);
    }

    //获取指定title的话题,模糊搜索->sql like,221701401负责
    @UserLoginToken
    @GetMapping("/topic/title/{title}")
    public  @ResponseBody AjaxResponse getTopicByTitle(@PathVariable(name="title") String title) {
        if (title.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<Topic> topic = topicService.getTopicsByTitle(title);
        if (topic.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该title对应话题");
        return AjaxResponse.success(topic);
    }

    /*//测试用的接口
    @GetMapping("/test/{topicid}")
    public  @ResponseBody AjaxResponse test(@PathVariable(name="topicid") String topicid) {
        //return AjaxResponse.success(topicService.updateLikesById(topicid,1));
        //return AjaxResponse.success(topicService.insertLikesById(topicid,1));
        return AjaxResponse.success(topicService.deleteLikesById(topicid,0));
    }*/
}
