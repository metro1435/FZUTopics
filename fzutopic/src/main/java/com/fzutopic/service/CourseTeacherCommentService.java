package com.fzutopic.service;

import com.fzutopic.model.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public interface CourseTeacherCommentService {
    /**
     * 修改课程教师评论表踩赞总数，用户首次踩赞时调用
     *
     * @param commentid 评论表主键
     * @param status    0：用户点赞 1：用户踩
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse insertLikesById(String commentid, int status);

    /**
     * 修改课程教师评论表踩赞总数，用户修改踩赞为赞踩时调用
     *
     * @param commentid 评论表主键
     * @param status    0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse updateLikesById(String commentid, int status);

    /**
     * 修改课程教师评论表踩赞总数，用户取消踩赞时调用
     *
     * @param commentid 评论表主键
     * @param status    0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    AjaxResponse deleteLikesById(String commentid, int status);


}
