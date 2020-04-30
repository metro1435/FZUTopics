package com.fzutopic.controller;

import com.fzutopic.dao.CommentDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Comment;
import com.fzutopic.model.Tag;
import com.fzutopic.service.TagServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class TagController {
    @Resource(name = "tagServiceImpl")
    TagServiceImpl tagService;

    //获取8个热度高的标签的接口，221701401负责
    @GetMapping("/tag")
    public AjaxResponse getTag() {
        List<Tag> tag = tagService.getTag();
        if (tag.isEmpty()) return AjaxResponse.error(404,"没有找到tag");
        return AjaxResponse.success(tag);
    }
}
