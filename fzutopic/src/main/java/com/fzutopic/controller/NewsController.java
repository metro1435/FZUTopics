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

    //获取已发布新闻（限制8个）
    @GetMapping("/news")
    public AjaxResponse getNews() {
        //List<News> news = newsService.getNews();
        PageInfo<News> news = newsService.getNews();
        if (news.getList().isEmpty()) return AjaxResponse.error(404, "没有找到news");
        return AjaxResponse.success(news);
    }

    //获取某一新闻内容（指定id）
    @GetMapping("/news/{newsid}")
    public  @ResponseBody AjaxResponse getNewsById(@PathVariable(name="newsid") String newsid) {
        if (newsid.isEmpty() || newsid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        News news = newsService.getNewsByID(newsid);
        if (news==null) return AjaxResponse.error(404,"没有找到该id对应新闻");
        return AjaxResponse.success(news);
    }

    //搜索新闻（指定title，模糊搜索）
    @GetMapping("/news/name/{name}")
    public  @ResponseBody AjaxResponse getNewsByName(@PathVariable(name="name") String name) {
        if (name.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<News> news = newsService.getNewsByName(name);
        if (news.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该name对应新闻");
        return AjaxResponse.success(news);
    }




}

