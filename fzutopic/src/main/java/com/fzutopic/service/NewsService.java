package com.fzutopic.service;

import com.fzutopic.model.News;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 1309
 */
public interface NewsService {
    PageInfo<News> getNews(int page);

    News getNewsByID(String id);

    PageInfo<News> getNewsByName(String name,int page);

    //News insertTag(News news);

    //管理员添加新闻，1403负责
    News createNews(News news);
}


