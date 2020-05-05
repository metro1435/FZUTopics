package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.News;
import com.fzutopic.service.FavlistItemService;
import com.fzutopic.service.NewsServiceImpl;
import com.fzutopic.service.TokenService;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author 1309
 */
@Slf4j
@RestController
public class NewsController {
    @Resource(name = "newsServiceImpl")
    NewsServiceImpl newsService;
    @Resource
    private TokenService tokenService;
    @Resource
    private FavlistItemService favlistItemService;

    //获取已发布新闻（限制8个),221701309负责
    @GetMapping("/news")
    @CrossOrigin
    @UserLoginToken
    public AjaxResponse getNews() {
        //List<News> news = newsService.getNews();
        PageInfo<News> news = newsService.getNews();
        if (news.getList().isEmpty()) return AjaxResponse.error(404, "没有找到news");
        return AjaxResponse.success(news);
    }

    //获取某一新闻内容（指定id），221701309负责
    @GetMapping("/news/{newsid}")
    @CrossOrigin
    @UserLoginToken
    public  @ResponseBody AjaxResponse getNewsById(@PathVariable(name="newsid") String newsid) {
        if (newsid.isEmpty() || newsid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        News news = newsService.getNewsByID(newsid);
        if (news==null) return AjaxResponse.error(404,"没有找到该id对应新闻");
        return AjaxResponse.success(news);
    }

    //搜索新闻（指定title，模糊搜索），221701309负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/news/search/{name}")
    public  @ResponseBody AjaxResponse getNewsByName(@PathVariable(name="name") String name) {
        if (name.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<News> news = newsService.getNewsByName(name);
        if (news.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该name对应新闻");
        return AjaxResponse.success(news);
    }

    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/user/news/favstatus/{topicid}")
    public AjaxResponse gettopicfavstatus(HttpServletRequest httpServletRequest, @PathVariable String newsid) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        boolean favstatus  = favlistItemService.getfavstatus(newsid, userid);
        return AjaxResponse.success(favstatus);
    }


}

