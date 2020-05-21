package com.fzutopic.controller;


import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.*;
import com.fzutopic.model.*;
import com.fzutopic.service.*;
import com.fzutopic.utils.TokenUtil;
import com.fzutopic.view.LikesSort;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Resource(name = "courseTeacherCommentServiceImpl")
    CourseTeacherCommentServiceImpl courseTeacherCommentService;

    @Resource
    TopiclikesDao topiclikesDao;

    @Resource
    CommentlikesDao commentlikesDao;

    @Resource
    ReplyDao replyDao;

    @Resource
    CommentDao commentDao;

    @Resource
    TopicDao topicDao;

    @Resource
    CtcommentDao ctcommentDao;

    /**
     * 获取用户对话题的踩赞记录
     * likes:-1未踩赞过 0：踩 1：赞
     *
     * @param topicid
     * @param httpServletRequest
     * @return
     */
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/user/topic/topiclikes/{topicid}")
    public AjaxResponse getTopicLikes(@PathVariable(name = "topicid") String topicid,
                                      HttpServletRequest httpServletRequest) {
        if (topicDao.selectByPrimaryKey(topicid) == null)
            return AjaxResponse.error(500, "不存在话题：" + topicid);

        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        TopiclikesKey key = new TopiclikesKey();
        key.setTopicid(topicid);
        key.setUserid(userid);
        Topiclikes topiclikes = topiclikesDao.selectByPrimaryKey(key);
        int likes;
        if (topiclikes == null)
            likes = -1;
        else {
            likes = topiclikes.getLikedstatus();
        }
        return AjaxResponse.success(likes);
    }

    //用户首次对话题点赞（踩）
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/user/topic/topiclikes")
    public AjaxResponse postToplicLikes(@RequestBody Topiclikes topiclikes, HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        String topicid = topiclikes.getTopicid();
        topiclikes.setUserid(userid);

        if (topiclikesDao.selectByPrimaryKey(topiclikes) != null)
            return AjaxResponse.error(500, "用户：" + userid + "非首次对话题：" + topicid + "点踩赞");
        int likes = topiclikes.getLikedstatus();
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likedStatus参数值非法要求为0或1");

        AjaxResponse ajaxResponse = topicService.insertLikesById(topicid, likes);
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.postTopicLikes(topiclikes);
        return AjaxResponse.success("话题点赞（踩）成功" + "   " + ajaxResponse.getMessage());
    }

    //用户取消对话题的点赞或踩
    @UserLoginToken
    @CrossOrigin
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
        return AjaxResponse.success("取消话题点赞（踩）成功。" + ajaxResponse.getMessage());
    }

    //用户修改对话题的态度
    @UserLoginToken
    @CrossOrigin
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
        return AjaxResponse.success("成功修改点赞（踩）为踩（点赞）。" + ajaxResponse.getMessage());
    }

    /**
     *获取用户对评论（回复）的踩赞记录
     * likes:likes:-1未踩赞过 0：踩 1：赞
     *
     * @param id
     * @param sort 0：表示是对话题评论回复踩赞 1:表示是对话题评论踩赞 2：表示对课程（教师）评论踩赞
     * @param httpServletRequest
     * @return AjaxResponse
     */
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/user/topic/comment/commentlikes/{id}/{sort}")
    public AjaxResponse getCommentLikes(@PathVariable(name = "id") String id, @PathVariable(name = "sort")
            int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1 && sort != 2)
            return AjaxResponse.error(500, "sorts参数值非法，要求是0、1、2）");

        int likes=-1;
        if(sort==1){
            if(commentDao.selectByPrimaryKey(id)==null)
                return AjaxResponse.error(500,"不存在话题评论："+id);
        }else if(sort==0){
            if(replyDao.selectByPrimaryKey(id)==null)
                return AjaxResponse.error(500,"不存在回复："+id);
        }else{
            if(ctcommentDao.selectByPrimaryKey(id)==null)
                return AjaxResponse.error(500,"不存在课程评论："+id);
        }

        CommentlikesKey commentlikesKey =new CommentlikesKey();
        commentlikesKey.setItemid(id);
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        commentlikesKey.setUserid(userid);

        Commentlikes commentlikes=commentlikesDao.selectByPrimaryKey(commentlikesKey);
        if(commentlikes!=null)
            likes=commentlikes.getLikedstatus();
        return AjaxResponse.success(likes);
    }

    /**
     * 用户首次对评论（回复）点踩赞
     * sort  0：表示是对话题评论回复踩赞 1:表示是对话题评论踩赞 2：表示对课程（教师）评论踩赞
     *
     * @param httpServletRequest
     * @return AjaxResponse
     */
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/user/topic/comment/commentlikes")
    public AjaxResponse postCommentLikes(@RequestBody LikesSort likesSort, HttpServletRequest httpServletRequest) {
        int sort = likesSort.getSort();
        int likes = likesSort.getLikes();
        String itemid = likesSort.getItemid();

        if (sort != 0 && sort != 1 && sort != 2)
            return AjaxResponse.error(500, "sorts参数值非法，要求是0、1、2）");

        Commentlikes commentlikes = new Commentlikes();
        commentlikes.setItemid(itemid);
        commentlikes.setLikedstatus(likes);
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        commentlikes.setUserid(userid);
        if (commentlikesDao.selectByPrimaryKey(commentlikes) != null)
            return AjaxResponse.error(500, "用户：" + userid + "非首次对此评论（回复）点赞（踩）");
        if (likes != 1 && likes != 0)
            return AjaxResponse.error(500, "likesStatus参数值非法，要求为0或1");

        String message = "";

        AjaxResponse ajaxResponse;
        if (sort == 1) {
            //表示是对话题评论踩赞
            message += "对话题评论";
            ajaxResponse = commentService.insertLikesById(itemid, likes);
        } else if (sort == 0) {
            //表示是对话题评论的回复踩赞
            message += "对话题话题评论的回复";
            ajaxResponse = replyService.insertLikesById(itemid, likes);
        } else {
            //表示对课程（教师）评论踩赞
            message += "对课程（教师）评论";
            ajaxResponse = courseTeacherCommentService.insertLikesById(itemid, likes);
        }
        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.postCommentLikes(commentlikes);
        message += "成功点赞（踩）。" + ajaxResponse.getMessage();

        return AjaxResponse.success(message);
    }


    /**
     * 用户修改对评论（回复）的态度
     *
     * @param itemid             被修改踩赞的评论（回复）id
     * @param likes              0：修改为踩 1：修改为赞
     * @param sort               0：表示是对话题评论回复踩赞 1:表示是对话题评论踩赞 2：表示对课程（教师）评论踩赞
     * @param httpServletRequest
     * @return AjaxResponse
     */
    @UserLoginToken
    @CrossOrigin
    @PutMapping("/user/topic/comment/commentlikes")
    public AjaxResponse updateCommentLikes(@RequestParam(name = "itemid") String itemid, @RequestParam(name = "likes") int likes,
                                           @RequestParam(name = "sort") int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1 && sort != 2)
            return AjaxResponse.error(500, "sorts参数值非法，要求是0、1、2）");

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

        String message = "";
        AjaxResponse ajaxResponse;
        if (sort == 1) {
            message += "对话题评论";
            //表示是对话题评论修改踩赞
            ajaxResponse = commentService.updateLikesById(itemid, likes);
        } else if (sort == 0) {
            //表示是对评论的回复修改踩赞
            ajaxResponse = replyService.updateLikesById(itemid, likes);
            message += "对话题评论的回复";
        } else {
            //表示对课程（教师）评论修改踩赞
            ajaxResponse = courseTeacherCommentService.updateLikesById(itemid, likes);
            message += "对课程（教师）评论";
        }

        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.updateCommentLikes(commentlikes);

        message += "成功修改点赞（踩）为踩（点赞）。" + ajaxResponse.getMessage();
        return AjaxResponse.success(message);
    }

    /**
     * 用户取消对评论（回复）的赞（踩）
     *
     * @param itemid             被取消踩赞的评论（回复）id
     * @param sort               0：表示是对话题评论回复踩赞 1:表示是对话题评论踩赞 2：表示对课程（教师）评论踩赞
     * @param httpServletRequest
     * @return AjaxResponse
     */
    @UserLoginToken
    @CrossOrigin
    @DeleteMapping("/user/topic/comment/commentlikes")
    public AjaxResponse deleteCommentLikes(@RequestParam(name = "itemid") String itemid,
                                           @RequestParam(name = "sort") int sort, HttpServletRequest httpServletRequest) {
        if (sort != 0 && sort != 1 && sort != 2)
            return AjaxResponse.error(500, "sorts参数值非法，要求是0、1、2）");


        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        CommentlikesKey commentlikesKey = new CommentlikesKey();
        commentlikesKey.setItemid(itemid);
        commentlikesKey.setUserid(userid);
        Commentlikes commentlikes = commentlikesDao.selectByPrimaryKey(commentlikesKey);
        if (commentlikes == null)
            return AjaxResponse.error(500, "不存在用户：" + userid + "对评论（回复）：" + itemid + "的点赞（踩）记录）");

        int likes = commentlikes.getLikedstatus();
        AjaxResponse ajaxResponse;
        String message = "";
        if (sort == 1) {
            //表示是对话题评论取消踩赞
            ajaxResponse = commentService.deleteLikesById(itemid, likes);
            message += "对话题评论";
        } else if (sort == 0) {
            //表示是对评论的回复取消踩赞
            ajaxResponse = replyService.deleteLikesById(itemid, likes);
            message += "对话题评论的回复";
        } else {
            //表示是对课程（教师）评论取消踩赞
            ajaxResponse = courseTeacherCommentService.deleteLikesById(itemid, likes);
            message += "对课程（教师）评论";
        }

        int code = ajaxResponse.getCode();
        if (code != 200) return ajaxResponse;
        likesService.deleteCommentlikes(commentlikesKey);
        message += "取消点赞（踩）成功。" + ajaxResponse.getMessage();
        return AjaxResponse.success(message);
    }

    /**
     * 获取待审核的回复
     * 一页十条
     *
     * @return AjaxResponse
     */
    @UserLoginToken
    @GetMapping("/admin/reply/unaudited")
    public AjaxResponse getUnaditedReply() {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andAuditstatusEqualTo(0);
        PageHelper.startPage(1, 16);
        List<Reply> unauditedReplies = replyDao.selectByExample(replyExample);
        PageInfo<Reply> replyPageInfo = PageInfo.of(unauditedReplies);
        return AjaxResponse.success(replyPageInfo);
    }


    /**
     * 审核回复（选择通过或不通过）
     *
     * @param replyid     回复的id
     * @param auditstatus 0：审核不通过 1：审核通过
     * @return
     */
    @PutMapping("/admin/reply/unaudited")
    public AjaxResponse updateReplyStatus(@RequestParam(name = "replyid") String replyid,
                                          @RequestParam(name = "auditstatus") int auditstatus) {
        Reply reply = replyDao.selectByPrimaryKey(replyid);
        if (reply == null || reply.getAuditstatus() == 1)
            return AjaxResponse.error(500, "错误，待审核回复：" + replyid + " 不存在，或者已经审核通过");
        if (auditstatus == 0) {
            replyDao.deleteByPrimaryKey(replyid);
            return AjaxResponse.success("审核不通过成功");
        } else {
            reply.setAuditstatus(1);
            replyDao.updateByPrimaryKey(reply);
            String commentid = reply.getCommentid();
            Comment comment = commentDao.selectByPrimaryKey(commentid);
            comment.setIsreply(1);

            //设置被回复的评论中isreply=1
            commentDao.updateByPrimaryKey(comment);
            String topicid = comment.getTopicid();
            Topic topic = topicDao.selectByPrimaryKey(topicid);
            int commentCount = topic.getCommentcount();
            topic.setCommentcount(commentCount + 1);
            //更新话题评论数+1
            topicDao.updateByPrimaryKey(topic);
            return AjaxResponse.success("审核通过成功");
        }
    }
}
