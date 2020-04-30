package com.fzutopic.service;

import com.fzutopic.model.Tag;

import java.util.List;

public interface TagService {

    //按热度降序获取tag的方法，限制8个，221701401负责
    List<Tag> getTag();
}
