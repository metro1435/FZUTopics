package com.fzutopic.service;

import com.fzutopic.model.News;
import com.fzutopic.model.Topic;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NewsService {
    List<News> getNews();

    List<News> getNewsByID(String id);

    List<News> getNewsFavStatusByID(String id);

    PageInfo<News> getNewsByName(String name);

    //News insertTag(News news);
}


