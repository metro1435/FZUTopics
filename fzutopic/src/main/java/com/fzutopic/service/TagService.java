package com.fzutopic.service;

import com.fzutopic.model.Tag;
import com.fzutopic.model.TopicTagKey;

import java.util.List;

public interface TagService {

    //按热度降序获取tag的方法，限制8个，221701401负责
    List<Tag> getTag();

    //插入标签，221701401负责
    boolean insertTag(List<TopicTagKey> topicTagKeys);

    //获取所有标签，221701401负责
    List<Tag> getAllTag();

    //更新标签使用次数，221701401负责
    boolean updateTimes(String tagid);
}
