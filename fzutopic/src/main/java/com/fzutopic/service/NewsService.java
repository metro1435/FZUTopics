package com.fzutopic.service;

import com.fzutopic.model.News;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 1309
 */
public interface NewsService {
    PageInfo<News> getNews();

    News getNewsByID(String id);

    PageInfo<News> getNewsByName(String name);

    //News insertTag(News news);
}


