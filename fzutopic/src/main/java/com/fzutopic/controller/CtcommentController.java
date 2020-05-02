package com.fzutopic.controller;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.service.CtcommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class CtcommentController {
    @Resource(name="ctcommentServiceImpl")
    CtcommentServiceImpl ctcommentService;

    //通过课程（教师）id获取课程（教师）评论列表
    @GetMapping("/course/comment/{commentitemid}")
    public @ResponseBody AjaxResponse getctcomments(@PathVariable String commentitemid){
        return AjaxResponse.success(ctcommentService.getctcomments(commentitemid));
    }

    //实现新增课程（教师）评论
    @PostMapping("/course/comment")
    public @ResponseBody AjaxResponse createctcomment(@RequestBody Ctcomment ctcomment){
        return AjaxResponse.success(ctcommentService.createctcomment(ctcomment));
    }
}
