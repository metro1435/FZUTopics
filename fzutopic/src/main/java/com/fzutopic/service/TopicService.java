package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Topic;
import com.fzutopic.model.TopicTagKey;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TopicService {

    //打印全部话题，一组10个,按热度降序,221701401负责
    PageInfo<Topic> getTopics(int page);

    //根据topicid找话题，221701401负责
    List<Topic> getTopicByID(String id);

    //根据tagid找话题,一组16,按热度降序，221701401负责
    PageInfo<Topic> getTopicsByTag(String tagid,int page);

    //根据title找话题，模糊搜索使用sql的like，221701401负责
    PageInfo<Topic> getTopicsByTitle(String title,int page);

    //浏览量+1，221701401负责
    List<Topic> updateViews(List<Topic> topic);

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse insertLikesById(String topicid, int status);

    //修改，对应一方+1一方-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse updateLikesById(String topicid, int status);

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    AjaxResponse deleteLikesById(String topicid, int status);

    //新增话题，221701309负责
    Topic createTopic(Topic topic);

    //管理员获取待审核话题列表，1403负责
    PageInfo<Topic> getunauditedTopics(int page);

    //管理员查看某个待审核话题，1403负责
    Topic checkunauditedTopic(String topicid);

    //管理员审核话题，1403负责
    Topic updateTopicstatus(String topicid,int status);
}
