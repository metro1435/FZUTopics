package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.service.CourseTeacherCommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class CtcommentController {
    @Resource(name="courseTeacherCommentServiceImpl")
    CourseTeacherCommentServiceImpl ctcommentService;

    //通过课程（教师）id获取课程（教师）评论列表,1403
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/course/comment/{commentitemid}")
    public @ResponseBody AjaxResponse getctcomments(@PathVariable String commentitemid){
        List<Ctcomment> ctcomments=ctcommentService.getctcomments(commentitemid);
        return AjaxResponse.success(ctcomments);
    }

    //实现新增课程（教师）评论,1403
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/course/comment")
    public @ResponseBody AjaxResponse createctcomment(@RequestBody Ctcomment ctcomment){
        return AjaxResponse.success(ctcommentService.createctcomment(ctcomment));
    }
}
