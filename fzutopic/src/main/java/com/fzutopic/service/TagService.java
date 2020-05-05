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

    //管理员获取标签列表，221701309负责
    List<Tag> getTagList();

    //管理员新增标签，221701309负责
    Tag createTag(Tag tag);

    //管理员删除标签（指定id）,221701309负责
    void deleteTag(String tagid);
}
