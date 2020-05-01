package com.fzutopic.service;

import com.fzutopic.dao.NewsDao;
import com.fzutopic.model.News;
import com.fzutopic.model.NewsExample;
import com.fzutopic.model.Topic;
import com.fzutopic.model.TopicExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import com.fzutopic.dao.CommentDao;
import com.fzutopic.dao.TopicDao;
import com.fzutopic.dao.TopicTagDao;
import com.fzutopic.dao.UserDao;
import com.fzutopic.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Slf4j
@Service
@RestController
public class NewsServiceImpl implements NewsService{
    @Resource
    private NewsDao newsDao;

    //获取已发布新闻
    //按热度降序获取tag的方法，限制8个
    public List<News> getNews(){
        NewsExample newsExample=new NewsExample();
        //newsExample.setOrderByClause("time desc");
        newsExample.setLimit(8);
        return newsDao.selectByExampleWithBLOBs(newsExample);
    }

    //获取某一新闻内容
    //根据topicid找话题
    public List<News> getNewsByID(String id) {
        NewsExample newsExample=new NewsExample();
        NewsExample.Criteria criteria=newsExample.createCriteria();
        criteria.andNewsidEqualTo(id);
        return newsDao.selectByExampleWithBLOBs(newsExample);
    }

    //获取用户对id为xxx新闻的收藏状态
    //根据topicid找话题
    public List<News> getNewsFavStatusByID(String id) {
        NewsExample newsExample=new NewsExample();
        NewsExample.Criteria criteria=newsExample.createCriteria();
        criteria.andNewsidEqualTo(id);
        return newsDao.selectByExampleWithBLOBs(newsExample);
    }

    //搜索新闻
    //根据title找话题，目前没做模糊搜索
    public PageInfo<News> getNewsByName(String name) {
        NewsExample newsExample=new NewsExample();
        newsExample.setOrderByClause("time desc");
        NewsExample.Criteria criteria=newsExample.createCriteria();
        criteria.andTitleEqualTo(name);
        PageHelper.startPage(1,16);
        List<News> test=newsDao.selectByTitleLike(name);
        return PageInfo.of(test);
        //List<Topic> topics=topicDao.selectByExampleWithBLOBs(topicExample);
        //return PageInfo.of(topics);
    }
}








