package com.fzutopic.service;

import com.fzutopic.dao.CtcommentDao;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.Ctcomment;
import com.fzutopic.model.CtcommentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    //实现通过课程（教师）id获取课程（教师）评论列表,1403
    public List<Ctcomment> getctcomments(String commentitemid){
        CtcommentExample example=new CtcommentExample();
        CtcommentExample.Criteria criteria=example.createCriteria();
        criteria.andCommentitemidEqualTo(commentitemid);
        example.setOrderByClause("commentid desc");
        return ctcommentDao.selectByExampleWithBLOBs(example);
    }

    //实现新增课程（教师）评论,1403
    public Ctcomment createctcomment(Ctcomment ctcomment){
        ctcommentDao.insertByCtcomment(ctcomment);
        ctcomment.setCommentid(ctcomment.getCommentid());
        ctcomment.setText(ctcomment.getText());
        ctcomment.setLikes(ctcomment.getLikes());
        ctcomment.setUnlikes(ctcomment.getUnlikes());
        ctcomment.setTime(ctcomment.getTime());
        ctcomment.setIsanony(ctcomment.getIsanony());
        ctcomment.setUserid(ctcomment.getUserid());
        ctcomment.setCommentitemid(ctcomment.getCommentid());
        ctcomment.setAuditstatus(ctcomment.getAuditstatus());
        return ctcomment;
    }

    //获取待审核课程（教师）评论列表，1403负责
   public PageInfo<Ctcomment> getUnauditedCtcomments(int page){
        CtcommentExample example=new CtcommentExample();
        CtcommentExample.Criteria criteria=example.createCriteria();
        criteria.andAuditstatusEqualTo(0);
        PageHelper.startPage(page,16);
        List<Ctcomment> ctcomments=ctcommentDao.selectByExampleWithBLOBs(example);
        return new PageInfo<>(ctcomments);
   }

    //管理员审核课程（教师）评论通过，1403负责
    public Ctcomment updateCtcommentstatus(String ctcommentid){
        Ctcomment ctcomment=ctcommentDao.selectByPrimaryKey(ctcommentid);
        ctcomment.setAuditstatus(1);
        ctcommentDao.updateByPrimaryKeyWithBLOBs(ctcomment);
        return ctcomment;
    }

    //管理员审核课程（教师）评论不通过直接删除，1403负责
    public int deleteunauditedCtcomment(String ctcommentid){
        return ctcommentDao.deleteByPrimaryKey(ctcommentid);
    }
}
