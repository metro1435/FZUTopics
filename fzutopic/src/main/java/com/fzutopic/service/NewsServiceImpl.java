package com.fzutopic.service;

import com.fzutopic.dao.NewsDao;
import com.fzutopic.model.News;
import com.fzutopic.model.NewsExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 1309
 */
@Slf4j
@Service
@RestController
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDao newsDao;

    //获取已发布新闻（按时间，限制8个）
    public PageInfo<News> getNews(){
        NewsExample newsExample=new NewsExample();
        newsExample.setOrderByClause("time desc");
        //newsExample.setLimit(8);
        //return newsDao.selectByExampleWithBLOBs(newsExample);
        PageHelper.startPage(1,10);
        List<News> test=newsDao.selectByExampleWithBLOBs(newsExample);
        return PageInfo.of(test);
    }

    //获取某一新闻内容（按newsid）
    public News getNewsByID(String id) {
        /*NewsExample newsExample=new NewsExample();
        NewsExample.Criteria criteria=newsExample.createCriteria();
        criteria.andNewsidEqualTo(id);
        return newsDao.selectByExampleWithBLOBs(newsExample);*/
        return newsDao.selectByPrimaryKey(id);
    }

    //搜索新闻(按title，模糊搜索)
    public PageInfo<News> getNewsByName(String name) {
        /*NewsExample newsExample=new NewsExample();
        newsExample.setOrderByClause("time desc");
        NewsExample.Criteria criteria=newsExample.createCriteria();
        criteria.andTitleEqualTo(name);*/
        PageHelper.startPage(1,10);
        List<News> test=newsDao.selectByTitleLike(name);
        return PageInfo.of(test);
        //List<Topic> topics=topicDao.selectByExampleWithBLOBs(topicExample);
        //return PageInfo.of(topics);
    }
}








