package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CommentDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Tag;
import com.fzutopic.model.TopicTagKey;
import com.fzutopic.service.TagServiceImpl;
import com.fzutopic.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class TagController {
    @Resource(name = "tagServiceImpl")
    TagServiceImpl tagService;

    //获取8个热度高的标签的接口，221701401负责
    @UserLoginToken
    @GetMapping("/tag")
    public AjaxResponse getTag() {
        List<Tag> tag = tagService.getTag();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }

    //提交标签，221701401负责
    @UserLoginToken
    @PostMapping("/topic/tag")
    public @ResponseBody AjaxResponse insertTag(@RequestBody List<TopicTagKey> tags) {
        boolean flag=tagService.insertTag(tags);
        return AjaxResponse.success(flag);
    }

    //获取所有标签，无限制，221701401负责
    @UserLoginToken
    @GetMapping("/allTag")
    public AjaxResponse getAllTag() {
        List<Tag> tag = tagService.getAllTag();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }
}
