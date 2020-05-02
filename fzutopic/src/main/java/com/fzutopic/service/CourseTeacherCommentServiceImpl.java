package com.fzutopic.service;

import com.fzutopic.dao.CtcommentDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Service
@RestController
public class CourseTeacherCommentServiceImpl implements CourseTeacherCommentService {
    @Resource
    CtcommentDao ctcommentDao;

    @Override
    public AjaxResponse insertLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 0);
    }

    @Override
    public AjaxResponse updateLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 1);
    }

    @Override
    public AjaxResponse deleteLikesById(String commentid, int status) {
        return changeRecord(commentid, status, 2);
    }

    /**
     * @param commentid 课程教师评论主键
     * @param status
     * @param sort      0：标识用户首次踩赞 1：用户修改踩赞 2：用户取消踩赞
     * @return AjaxResponse
     */
    private AjaxResponse changeRecord(String commentid, int status, int sort) {
        Ctcomment ctcomment = ctcommentDao.selectByPrimaryKey(commentid);
        if (ctcomment == null)
            return AjaxResponse.error(500, "不存在课程（教师）评论:" + commentid);
        int likes, unlikes;
        likes = ctcomment.getLikes();
        unlikes = ctcomment.getUnlikes();
        String message = "旧likes unlikes分别为 " + likes + "  " + unlikes;

        //根据sort和status值来修改likes和unlikes值
        if (sort == 0) {
            //用户首次踩赞
            if (status == 1) likes += 1;
            else unlikes += 1;
        } else if (sort == 1) {
            //用户修改踩赞
            if (status == 0) {
                //修改为踩
                likes -= 1;
                unlikes += 1;
            } else {
                //修改为赞
                likes += 1;
                unlikes -= 1;
            }
        } else {
            //用户取消踩赞
            if (status == 1) likes -= 1;
            else unlikes -= 1;
        }
        if (likes < 0 || unlikes < 0) return AjaxResponse.error(500, "修改点赞（踩）总数失败，出现小于0");
        ctcomment.setUnlikes(unlikes);
        ctcomment.setLikes(likes);
        ctcommentDao.updateByPrimaryKey(ctcomment);

        message += "  新的likes  unlikes分别为：" + likes + "   " + unlikes;
        return AjaxResponse.success(message);
    }
}
