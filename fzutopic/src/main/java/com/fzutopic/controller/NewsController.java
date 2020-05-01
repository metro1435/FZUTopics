package com.fzutopic.controller;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.News;
import com.fzutopic.service.NewsServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
public class NewsController {
    @Resource(name = "newsServiceImpl")
    NewsServiceImpl newsService;

    //获取已发布新闻
    //获取8个热度高的标签的接口
    @GetMapping("/news")
    public AjaxResponse getNews() {
        List<News> news = newsService.getNews();
        if (news.isEmpty()) return AjaxResponse.error(404, "没有找到news");
        return AjaxResponse.success(news);
    }

    //获取某一新闻内容
    //返回指定id的话题
    @GetMapping("/news/{newsid}")
    public  @ResponseBody
    AjaxResponse getNewsById(@PathVariable(name="newsid") String newsid) {
        if (newsid.isEmpty() || newsid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        List<News> news = newsService.getNewsByID(newsid);
        if (news.isEmpty()) return AjaxResponse.error(404,"没有找到该id对应新闻");
        return AjaxResponse.success(news);
    }

    //获取用户对id为xxx新闻的收藏状态
    //返回指定id的话题
    @GetMapping("/user/news/favstatus/{newsid}")
    public  @ResponseBody
    AjaxResponse getNewsFavStatusById(@PathVariable(name="newsid") String newsid) {
        if (newsid.isEmpty() || newsid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        List<News> news = newsService.getNewsFavStatusByID(newsid);
        if (news.isEmpty()) return AjaxResponse.error(404,"没有找到该id对应新闻");
        return AjaxResponse.success(news);
    }


    //搜索新闻（模糊搜索）
    //返回指定title的话题,模糊搜索->sql like
    @GetMapping("/news/name/{name}")
    public  @ResponseBody
    AjaxResponse getNewsByName(@PathVariable(name="name") String name) {
        if (name.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<News> news = newsService.getNewsByName(name);
        if (news.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该name对应新闻");
        return AjaxResponse.success(news);
    }




}

