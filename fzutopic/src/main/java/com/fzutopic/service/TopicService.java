package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Topic;
import com.fzutopic.model.TopicTagKey;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TopicService {

    //打印全部话题，一组16个,按热度降序,221701401负责
    PageInfo<Topic> getTopics();

    //根据topicid找话题，221701401负责
    List<Topic> getTopicByID(String id);

    //根据tagid找话题,一组16,按热度降序，221701401负责
    PageInfo<Topic> getTopicsByTag(String tagid);

    //遍历TopicTag的list，找出每个topicid对应的topic信息，拼接成list返回，221701401负责
    List<Topic> getTopicListByTagList(List<TopicTagKey> topicTagKeys);

    //根据title找话题，模糊搜索使用sql的like，221701401负责
    PageInfo<Topic> getTopicsByTitle(String title);

    //浏览量+1，221701401负责
    List<Topic> updateViews(List<Topic> topic);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse insertLikesById(String topicid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse updateLikesById(String topicid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse deleteLikesById(String topicid, int status);

    //1309
    Topic createTopic(Topic topic);

}
