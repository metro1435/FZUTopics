package com.fzutopic.controller;

import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.service.CourseTeacherCommentServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class CtcommentController {
    @Resource(name="courseTeacherCommentServiceImpl")
    CourseTeacherCommentServiceImpl ctcommentService;

    //通过课程（教师）id获取课程（教师）评论列表,1403
    @UserLoginToken
    @CrossOrigin
    @GetMapping("/user/course/comment/{commentitemid}")
    public @ResponseBody AjaxResponse getctcomments(@PathVariable String commentitemid){
        List<Ctcomment> ctcomments=ctcommentService.getctcomments(commentitemid);
        return AjaxResponse.success(ctcomments);
    }

    //实现新增课程（教师）评论,1403
    @UserLoginToken
    @CrossOrigin
    @PostMapping("/user/course/comment")
    public @ResponseBody AjaxResponse createctcomment(@RequestBody Ctcomment ctcomment){
        return AjaxResponse.success(ctcommentService.createctcomment(ctcomment));
    }

    //管理员获取待审核课程（教师）评论列表，1403负责
    @AdminLoginToken
    @CrossOrigin
    @GetMapping("/admin/ctcomment/unaudited/page/{page}")
    public AjaxResponse getunauditedCtcomments(@PathVariable(name="page") int page){
        PageInfo<Ctcomment> unauditedCtcomments=ctcommentService.getUnauditedCtcomments(page);
        return AjaxResponse.success(unauditedCtcomments);
    }

    //管理员审核课程（教师）评论，1403负责
    @AdminLoginToken
    @CrossOrigin
    @PutMapping("/admin/ctcomment/unaudited")
    public AjaxResponse CtcommentAudit(@RequestParam String ctcommentid,
                                        @RequestParam int auditstatus) {
        if (auditstatus == 1)
            return AjaxResponse.success(ctcommentService.updateCtcommentstatus(ctcommentid));
        else if (auditstatus==0)
            return AjaxResponse.success(ctcommentService.deleteunauditedCtcomment(ctcommentid));
        else
            return AjaxResponse.error(400,"审核状态异常");
    }
}
