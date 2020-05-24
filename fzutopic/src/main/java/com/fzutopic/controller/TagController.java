package com.fzutopic.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.dao.CommentDao;
import com.fzutopic.model.*;
import com.fzutopic.service.TagServiceImpl;
import com.fzutopic.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class TagController {
    @Resource(name = "tagServiceImpl")
    TagServiceImpl tagService;

    //获取8个热度高的标签的接口，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/tag")
    public AjaxResponse getTag() {
        List<Tag> tag = tagService.getTag();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }

    //提交标签，221701401负责
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/topic/tag")
    public @ResponseBody AjaxResponse insertTag(@RequestBody List<TopicTagKey> tags) {
        boolean isExist=tagService.TopicTagisExist(tags);
        if (isExist) AjaxResponse.error(400,"该话题已存在该标签记录");
        tagService.insertTag(tags);
        return AjaxResponse.success("插入成功");
    }

    //获取所有标签，无限制，221701401负责
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/allTag")
    public AjaxResponse getAllTag() {
        List<Tag> tag = tagService.getAllTag();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }

    //管理员获取标签列表（一次40个），221701309负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/tag")
    public AjaxResponse getTagList() {
        List<Tag> tag = tagService.getTagList();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }

    //管理员新增标签，221701309负责
    @AdminLoginToken
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CrossOrigin
    @PostMapping("/admin/new/tag")
    public @ResponseBody AjaxResponse createTag(@RequestBody Tag tag){
        return AjaxResponse.success(tagService.createTag(tag));
    }


    //管理员删除标签,221701309负责
    @AdminLoginToken
    @CrossOrigin
    @DeleteMapping("/admin/tag")
    public @ResponseBody AjaxResponse deleteTag(@RequestParam String tagid){
        tagService.deleteTag(tagid);
        return AjaxResponse.success(tagid);
    }




}
