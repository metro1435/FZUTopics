package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Topic;
import com.fzutopic.service.FavlistItemService;
import com.fzutopic.service.TopicServiceImpl;
import com.fzutopic.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class TopicController {
    @Resource(name = "topicServiceImpl")
    TopicServiceImpl topicService;
    @Resource
    private FavlistItemService favlistItemService;

    //获取所有话题，1组16个，热度倒序，使用pagehelper分页，221701401负责
    //前端操作可参考 https://blog.csdn.net/ftlnnl/article/details/104972751
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/topic/page/{page}")
    public AjaxResponse getTopic(@PathVariable(name="page") int page) {
        PageInfo<Topic> topics = topicService.getTopics(page);
        if (topics.getList().isEmpty()) return AjaxResponse.error(404,"话题库没有话题");
        return AjaxResponse.success(topics);
    }

    //获取指定id的话题，考虑到使用情况，内置了浏览量加1的操作，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/topic/topicid/{topicid}")
    public  @ResponseBody AjaxResponse getTopicById(@PathVariable(name="topicid") String topicid) {
        if (topicid.isEmpty() || topicid.length()!=24) return AjaxResponse.error(400,"id为空或不合规");
        List<Topic> topic = topicService.getTopicByID(topicid);
        if (topic.size()==0) return AjaxResponse.error(404,"没有找到该id对应话题");
        //else这一行是浏览量加1.不需要的话注释掉即可
        else topic=topicService.updateViews(topic);
        return AjaxResponse.success(topic);
    }

    //获取指定tag的话题，1组16个，热度倒序，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/topic/{tagid}/page/{page}")
    public  @ResponseBody AjaxResponse getTopicByTag(@PathVariable(name="tagid") String tagid,
                                                     @PathVariable(name="page") int page) {
        if (tagid.isEmpty() || tagid.length()>5) return AjaxResponse.error(400,"tagid为空或不合规定");
        PageInfo<Topic> topics = topicService.getTopicsByTag(tagid,page);
        if (topics.getList().isEmpty()) return AjaxResponse.error(404,"找不到tag对应的话题");
        return AjaxResponse.success(topics);
    }

    //获取指定title的话题,模糊搜索->sql like,221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/topic/title/{title}/page/{page}")
    public  @ResponseBody AjaxResponse getTopicByTitle(@PathVariable(name="title") String title,
                                                       @PathVariable(name="page") int page) {
        if (title.isEmpty()) return AjaxResponse.error(400,"输入搜索的值为空");
        PageInfo<Topic> topic = topicService.getTopicsByTitle(title,page);
        if (topic.getList().isEmpty()) return AjaxResponse.error(404,"没有找到该title对应话题");
        return AjaxResponse.success(topic);
    }

    //新增话题，221701309负责
    @UserLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @PostMapping("/user/topic")
    public @ResponseBody AjaxResponse createTopic(@RequestBody Topic topic,
                                                  HttpServletRequest httpServletRequest){
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        topic.setUserid(userid);
        return AjaxResponse.success(topicService.createTopic(topic));
    }

    //获取用户对topicid话题的收藏状态402
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/user/topic/favstatus/{topicid}")
    public AjaxResponse gettopicfavstatus(HttpServletRequest httpServletRequest,
                                          @PathVariable String topicid) {
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        boolean favstatus  = favlistItemService.getfavstatus(topicid, userid);
        return AjaxResponse.success(favstatus);
    }
    /*//测试用的接口
    @GetMapping("/test/{topicid}")
    public  @ResponseBody AjaxResponse test(@PathVariable(name="topicid") String topicid) {
        //return AjaxResponse.success(topicService.updateLikesById(topicid,1));
        //return AjaxResponse.success(topicService.insertLikesById(topicid,1));
        return AjaxResponse.success(topicService.deleteLikesById(topicid,0));
    }*/

    //管理员获取待审核话题列表，1403负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/topic/unaudited/page/{page}")
    public AjaxResponse getunauditedTopics(@PathVariable(name="page") int page){
        PageInfo<Topic> unauditedtopics=topicService.getunauditedTopics(page);
        return AjaxResponse.success(unauditedtopics);
    }

    //管理员查看某个待审核话题，1403负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/topic/unaudited/{topicid}")
    public AjaxResponse checkunauditedTopic(@PathVariable String topicid){
        if(topicService.checkunauditedTopic(topicid)==null)
            return AjaxResponse.error(400,"该话题不在待审核列表");
        else
            return AjaxResponse.success(topicService.checkunauditedTopic(topicid));
    }

    //管理员审核话题，1403负责
    @AdminLoginToken
    @CrossOrigin
    @PutMapping("/admin/topic/unaudited")
    public AjaxResponse TopicAudit(@RequestParam String topicid,
                                          @RequestParam int status) {
        if (status == 1)
            return AjaxResponse.success(topicService.updateTopicstatus(topicid));
        else if (status==0)
            return AjaxResponse.success(topicService.deleteunauditedTopic(topicid));
        else
            return AjaxResponse.error(400,"审核状态异常");
    }

}
