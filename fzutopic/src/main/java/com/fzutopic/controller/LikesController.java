package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CommentlikesDao;
import com.fzutopic.dao.TopiclikesDao;
import com.fzutopic.model.*;
import com.fzutopic.service.CommentServiceImpl;
import com.fzutopic.service.LikesServiceImpl;
import com.fzutopic.service.ReplyServiceImpl;
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

    @Resource(name = "replyServiceImpl")
    ReplyServiceImpl replyService;

    @Resource(name = "commentServiceImpl")
    CommentServiceImpl commentService;

    @Resource
    TopiclikesDao topiclikesDao;

    @Resource
    CommentlikesDao commentlikesDao;

    //用户首次对话题点赞（踩）
    @UserLoginToken
    @PostMapping("/user/topic/topiclikes")
    public AjaxResponse postToplicLikes(@RequestBody Topiclikes topiclikes, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        String topicid = topiclikes.getTopicid();
        topiclikes.setUserid(userid);

        if (topiclikesDao.selectByPrimaryKey(topiclikes) != null)
            return AjaxResponse.error(500, "用户：" + userid + "非首次对话题：" + topicid + "点赞（踩）");
        int likes=topiclikes.getLikedstatus();
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likes参数值非法要求为0或1");

        AjaxResponse ajaxResponse = topicService.insertLikesById(topicid, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.postTopicLikes(topiclikes);
        return AjaxResponse.success("话题点赞（踩）成功");
    }

    //用户取消对话题的点赞或踩
    @UserLoginToken
    @DeleteMapping("/user/topic/topiclikes")
    public AjaxResponse deleteTopicLikes(@RequestParam(name = "topicid") String id, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        TopiclikesKey key = new TopiclikesKey();
        key.setTopicid(id);
        key.setUserid(userid);
        Topiclikes topiclikes = topiclikesDao.selectByPrimaryKey(key);
        if (topiclikes == null)
            return AjaxResponse.error(500, "不存在用户：" + userid + "对话题：" + id + "的点赞（踩）记录）");
        int status = topiclikes.getLikedstatus();
        AjaxResponse ajaxResponse = topicService.deleteLikesById(id, status);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.deleteTopiclikes(key);
        return AjaxResponse.success("取消话题点赞（踩）成功");
    }

    //用户修改对话题的态度
    @UserLoginToken
    @PutMapping("/user/topic/topiclikes")
    public AjaxResponse updateTopicLikes(@RequestParam(name = "topicid") String id, @RequestParam(name = "likes") int likes,
                                         HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        TopiclikesKey key = new TopiclikesKey();
        key.setTopicid(id);
        key.setUserid(userid);
        Topiclikes topiclikes = topiclikesDao.selectByPrimaryKey(key);
        if (topiclikes == null)
            return AjaxResponse.error(500, "不存在用户：" + userid + "对话题：" + id + "的点赞（踩）记录）");
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likes参数值非法要求为0或1");
        if (likes == topiclikes.getLikedstatus())
            return AjaxResponse.error(500, "用户：" + userid + "未对话题：" + id + "的点赞（踩）作出修改");
        topiclikes.setLikedstatus(likes);
        AjaxResponse ajaxResponse = topicService.updateLikesById(id, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.updateTopicLikes(topiclikes);
        return AjaxResponse.success("成功修改点赞（踩）为踩（点赞）");
    }

    //用户首次对评论（回复）点赞（踩）
    @UserLoginToken
    @PostMapping("/user/topic/comment/commentlikes")
    public AjaxResponse postCommentLikes(@RequestBody Commentlikes commentlikes,
                                         @RequestParam(name = "sort") int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1)
            return AjaxResponse.error(500, "sorts参数值非法，要求是1（评论）或0（回复）");

        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        String itemid = commentlikes.getItemid();
        int likes = commentlikes.getLikedstatus();
        commentlikes.setUserid(userid);
        if (commentlikesDao.selectByPrimaryKey(commentlikes) != null)
            return AjaxResponse.error(500, "用户：" + userid + "非首次对此评论（回复）点赞（踩）");
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likes参数值非法，要求为0或1");

        AjaxResponse ajaxResponse;
        if (sort == 1)
            //表示是对评论点赞（踩）
            ajaxResponse = commentService.insertLikesById(itemid, likes);
        else
            //表示是对评论的回复点赞（踩）
            ajaxResponse = replyService.insertLikesById(itemid, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.postCommentLikes(commentlikes);
        return AjaxResponse.success("成功点赞（踩）");
    }

    //用户修改对评论（回复）的态度
    @UserLoginToken
    @PutMapping("/user/topic/comment/commentlikes")
    public AjaxResponse updateCommentLikes(@RequestParam(name = "itemid") String itemid, @RequestParam(name = "likes") int likes,
                                           @RequestParam(name = "sort") int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1)
            return AjaxResponse.error(500, "sorts参数值非法，要求是1（评论）或0（回复）");

        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        CommentlikesKey commentlikesKey = new CommentlikesKey();
        commentlikesKey.setItemid(itemid);
        commentlikesKey.setUserid(userid);
        Commentlikes commentlikes = commentlikesDao.selectByPrimaryKey(commentlikesKey);
        if (commentlikes == null)
            return AjaxResponse.error(500, "不存在用户：" + userid + "对评论（回复）：" + itemid + "的点赞（踩）记录）");
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likes参数值非法，要求为0或1");
        if (likes == commentlikes.getLikedstatus())
            return AjaxResponse.error(500, "用户：" + userid + "未对评论（回复）：" + itemid + "的点赞（踩）作出修改");
        commentlikes.setLikedstatus(likes);

        AjaxResponse ajaxResponse;
        if (sort == 1)
            //表示是对评论点赞（踩）
            ajaxResponse = commentService.updateLikesById(itemid, likes);
        else
            //表示是对评论的回复点赞（踩）
            ajaxResponse = replyService.updateLikesById(itemid, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.updateCommentLikes(commentlikes);
        return AjaxResponse.success("成功修改点赞（踩）为踩（点赞）");
    }

    //用户取消对评论（回复）的赞（踩）
    @UserLoginToken
    @DeleteMapping("/user/topic/comment/commentlikes")
    public AjaxResponse deleteCommentLikes(@RequestParam(name = "itemid") String itemid,
                                           @RequestParam(name = "sort") int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1)
            return AjaxResponse.error(500, "sorts参数值非法，要求是1（评论）或0（回复）");


        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        CommentlikesKey commentlikesKey = new CommentlikesKey();
        commentlikesKey.setItemid(itemid);
        commentlikesKey.setUserid(userid);
        Commentlikes commentlikes = commentlikesDao.selectByPrimaryKey(commentlikesKey);
        if (commentlikes == null)
            return AjaxResponse.error(500, "不存在用户：" + userid + "对评论（回复）：" + itemid + "的点赞（踩）记录）");

        int likes=commentlikes.getLikedstatus();
        AjaxResponse ajaxResponse;
        if (sort == 1)
            //表示是对评论点赞（踩）
            ajaxResponse = commentService.deleteLikesById(itemid, likes);
        else
            //表示是对评论的回复点赞（踩）
            ajaxResponse = replyService.deleteLikesById(itemid, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.deleteCommentlikes(commentlikesKey);
        return AjaxResponse.success("取消点赞（踩）成功");
    }

}
