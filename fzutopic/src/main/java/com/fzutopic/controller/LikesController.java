package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.*;
import com.fzutopic.service.LikesServiceImpl;
import com.fzutopic.service.TopicServiceImpl;
import com.fzutopic.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@EnableWebMvc
/**
 * @author 呼叫哆啦A梦
 */
public class LikesController {
    @Resource(name = "likesServiceImpl")
    LikesServiceImpl likesService;

    @Resource(name = "topicServiceImpl")
    TopicServiceImpl topicService;

    //用户取消对话题的点赞或踩
    @UserLoginToken
    @DeleteMapping("/user/topic/topiclikes")
    public AjaxResponse deleteTopicLikes(@RequestParam(name = "topicid") String id, HttpServletRequest httpServletRequest) {
        TopiclikesKey key = new TopiclikesKey();
        key.setTopicid(id);
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        key.setUserid(userid);
        //更新话题总的点赞（踩）数

        //boolean success=topicService.updateLikesById()
        likesService.deleteTopiclikes(key);
        return AjaxResponse.success(id);
    }

    //用户修改对话题的态度
    @UserLoginToken
    @PutMapping("/user/topic/topiclikes")
    public AjaxResponse updateTopicLikes(@RequestParam(name = "topicid") String id, @RequestParam(name = "likes") int likes,
                                         HttpServletRequest httpServletRequest) {

        Topiclikes topiclikes = new Topiclikes();
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        topiclikes.setUserid(userid);
        topiclikes.setTopicid(id);
        topiclikes.setLikedstatus(likes);
        likesService.updateTopicLikes(topiclikes);
        return AjaxResponse.success(id);
    }

    //用户首次对话题点赞（踩）
    @UserLoginToken
    @PostMapping("/user/topic/topiclikes")
    public Topiclikes postToplicLikes(@RequestBody Topiclikes topiclikes, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        topiclikes.setUserid(userid);
        likesService.postTopicLikes(topiclikes);
        return topiclikes;
    }

    //用户取消对评论（回复）的赞（踩）
    @UserLoginToken
    @DeleteMapping("/user/topic/comment/commentlikes")
    public AjaxResponse deleteCommentLikes(@RequestParam(name = "itemid") String itemid, HttpServletRequest httpServletRequest) {
        CommentlikesKey key = new CommentlikesKey();
        key.setItemid(itemid);
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        key.setUserid(userid);
        likesService.deleteCommentlikes(key);
        return AjaxResponse.success(itemid);
    }

    //用户修改对评论（回复）的态度
    @UserLoginToken
    @PutMapping("/user/topic/comment/commentlikes")
    public AjaxResponse updateCommentLikes(@RequestParam(name = "itemid") String itemid,
                                           @RequestParam(name = "likes") int likes, HttpServletRequest httpServletRequest) {
        Commentlikes commentlikes = new Commentlikes();
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        commentlikes.setUserid(userid);
        commentlikes.setItemid(itemid);
        commentlikes.setLikedstatus(likes);
        likesService.updateCommentLikes(commentlikes);
        return AjaxResponse.success(itemid);
    }

    //用户首次对评论（回复）点赞（踩）
    @UserLoginToken
    @PostMapping("/user/topic/comment/commentlikes")
    public Commentlikes postCommentLikes(@RequestBody Commentlikes commentlikes, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        commentlikes.setUserid(userid);
        likesService.postCommentLikes(commentlikes);
        return commentlikes;
    }

}
