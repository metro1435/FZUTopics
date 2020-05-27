package com.fzutopic.controller;

import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.News;
import com.fzutopic.service.FavlistServiceImpl;
import com.fzutopic.service.NewsServiceImpl;
import com.fzutopic.service.TokenService;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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

    @Resource(name="favlistServiceImpl")
    FavlistServiceImpl favlistService;

    //获取已发布新闻（限制8个）
    @GetMapping("/news/page/{page}")
    @CrossOrigin
    @UserLoginToken
    public AjaxResponse getNews(@PathVariable(name="page") int page) {
        //List<News> news = newsService.getNews();
        PageInfo<News> news = newsService.getNews(page);
        if (news.getList().isEmpty()) return AjaxResponse.error(404, "没有找到news");
        return AjaxResponse.success(news);
    }

    //获取某一新闻内容（指定id）
    @GetMapping("/news/{newsid}")
    @CrossOrigin
    @UserLoginToken
    public  @ResponseBody AjaxResponse getNewsById(@PathVariable(name="newsid") String newsid) {
        if (newsid.isEmpty() || newsid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        News news = newsService.getNewsByID(newsid);
        if (news==null) return AjaxResponse.error(404,"没有找到该id对应新闻");
        return AjaxResponse.success(news);
    }

    //搜索新闻（指定title，模糊搜索）
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/news/search/{name}/page/{page}")
    public  @ResponseBody AjaxResponse getNewsByName(@PathVariable(name="name") String name,
                                                     @PathVariable(name="page") int page) {
        if (name.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<News> news = newsService.getNewsByName(name,page);
        if (news.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该name对应新闻");
        return AjaxResponse.success(news);
    }

    //获取用户对newsid新闻的收藏状态402
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/user/news/favstatus/{newsid}")
    public AjaxResponse gettopicfavstatus(HttpServletRequest httpServletRequest, @PathVariable String newsid) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        boolean favstatus  = favlistService.getfavstatus(newsid, userid);
        return AjaxResponse.success(favstatus);
    }

    //管理员添加新闻，1403负责
    @AdminLoginToken
    @CrossOrigin
    @PostMapping("/admin/news")
    public AjaxResponse createNews(@RequestBody News news){
        return AjaxResponse.success(newsService.createNews(news));
    }
}

