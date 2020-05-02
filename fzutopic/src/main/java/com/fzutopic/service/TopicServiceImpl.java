package com.fzutopic.service;

import com.fzutopic.dao.TopicDao;
import com.fzutopic.dao.TopicTagDao;
import com.fzutopic.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RestController
public class TopicServiceImpl implements TopicService {
    @Resource
    TopicDao topicDao;

    @Resource
    TopicTagDao topicTagDao;

    //打印全部话题，一组16个,按热度降序，221701401负责
    public PageInfo<Topic> getTopics() {
        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause("heats desc");
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andAuditstatusEqualTo(1);
        PageHelper.startPage(1, 16);
        List<Topic> topics = topicDao.selectByExampleWithBLOBs(topicExample);
        return PageInfo.of(topics);
    }

    //根据topicid找话题，221701401负责
    public List<Topic> getTopicByID(String id) {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andAuditstatusEqualTo(1);
        criteria.andTopicidEqualTo(id);
        return topicDao.selectByExampleWithBLOBs(topicExample);
    }


    //根据tagid找话题,一组16,按热度降序，221701401负责
    public PageInfo<Topic> getTopicsByTag(String tagid) {
        TopicTagExample topicTagExample = new TopicTagExample();
        TopicTagExample.Criteria criteria = topicTagExample.createCriteria();
        criteria.andTagidEqualTo(tagid);
        PageHelper.startPage(1, 16);
        List<TopicTagKey> topicsByTag = topicTagDao.selectByExample(topicTagExample);
        List<Topic> topicList = getTopicListByTagList(topicsByTag);
        return PageInfo.of(topicList);
    }

    //遍历TopicTag的list，找出每个topicid对应的topic信息，拼接成list返回，221701401负责
    public List<Topic> getTopicListByTagList(List<TopicTagKey> topicTagKeys) {
        List<Topic> resTopicList = new ArrayList<Topic>();
        for (TopicTagKey tmpTopicTagKey : topicTagKeys) {
            String tmpTopicId = tmpTopicTagKey.getTopicid();
            List<Topic> tmpTopic = getTopicByID(tmpTopicId);
            resTopicList.addAll(tmpTopic);
        }
        resTopicList.sort(new Comparator<Topic>() {
            @Override
            public int compare(Topic o1, Topic o2) {
                return o2.getHeats() - o1.getHeats();
            }
        });
        return resTopicList;
    }

    //根据title找话题，模糊搜索使用sql的like，221701401负责
    public PageInfo<Topic> getTopicsByTitle(String title) {
        /*TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause("heats desc");
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andTitleEqualTo(title);
        criteria.andAuditstatusEqualTo(1);*/
        PageHelper.startPage(1, 16);
        //这里是模糊搜索
        List<Topic> test = topicDao.selectByTitleLike(title);
        return PageInfo.of(test);
        //这里是精确搜索
        //List<Topic> topics=topicDao.selectByExampleWithBLOBs(topicExample);
        //return PageInfo.of(topics);
    }

    //浏览量+1，221701401负责
    public List<Topic> updateViews(List<Topic> topics) {
        for (Topic topic : topics) {
            int view = topic.getViews() + 1;
            topic.setViews(view);
            //修改热度
            int heats = (25 * view + 40 * topic.getCommentcount()
                    + 25 * topic.getLikes() - 5 * topic.getUnlikes()) / 100;
            topic.setHeats(heats);
            topicDao.updateByPrimaryKey(topic);
        }
        return topics;
    }

    /**
     * 修改话题表点赞和踩的总数以及热度，用户首次点赞（踩）时调用此方法
     *
     * @param topicid 话题ID主键
     * @param status  0：用户点赞 1：用户踩
     * @return AjaxResponse
     * @Author 呼叫哆啦A梦
     */
    public AjaxResponse insertLikesById(String topicid, int status) {
        return changeRecord(topicid, status, 0);
    }


    /**
     * 修改话题表点赞和踩的总数以及热度，用户修改态度时调用此方法
     *
     * @param topicid 话题ID主键
     * @param status  0：表示要修改为踩，1：表示要修改为赞
     * @return AjaxResponse
     * @author 呼叫哆啦A梦
     */
    public AjaxResponse updateLikesById(String topicid, int status) {
        return changeRecord(topicid, status, 1);
    }

    /**
     * 修改话题表点赞和踩的总数以及热度，用户取消点赞（踩）时调用此方法
     *
     * @param topicid 话题ID主键
     * @param status  0：表示原来是踩，1：表示原来是赞
     * @return AjaxResponse
     */
    public AjaxResponse deleteLikesById(String topicid, int status) {
        return changeRecord(topicid, status, 2);
    }

    /**
     * @param topicid 话题主键
     * @param status
     * @param sort    0：标识用户首次踩赞 1：用户修改踩赞 2：用户取消踩赞
     * @return AjaxResponse
     */
    private AjaxResponse changeRecord(String topicid, int status, int sort) {
        Topic topic = topicDao.selectByPrimaryKey(topicid);
        if (topic == null)
            return AjaxResponse.error(500, "不存在话题:" + topicid);
        int likes, unlikes;
        likes = topic.getLikes();
        unlikes = topic.getUnlikes();
        String message = "旧likes unlikes分别为 " + likes + "  " + unlikes;
        if (sort == 0) {
            //用户首次踩赞
            if (status == 1) likes += 1;
            else unlikes += 1;
        } else if (sort == 1) {
            //用户修改踩赞
            if (status == 0) {
                likes -= 1;
                unlikes += 1;
            } else {
                likes += 1;
                unlikes -= 1;
            }
        } else {
            //用户取消踩赞
            if (status == 1) likes -= 1;
            else unlikes -= 1;
        }

        if (likes < 0 || unlikes < 0) return AjaxResponse.error(500, "修改点赞（踩）总数失败，出现小于0");
        topic.setLikes(likes);
        topic.setUnlikes(unlikes);
        //修改热度
        int heats = (25 * topic.getViews() + 40 * topic.getCommentcount() + 25 * likes - 5 * unlikes) / 100;
        topic.setHeats(heats);
        topicDao.updateByPrimaryKey(topic);

        message += "  新的likes  unlikes分别为：" + likes + "   " + unlikes;
        return AjaxResponse.success(message);
    }

}
