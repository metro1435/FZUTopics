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
public class TopicServiceImpl implements TopicService{
    @Resource
    TopicDao topicDao;

    @Resource
    TopicTagDao topicTagDao;

    //打印全部话题，一组16个,按热度降序，221701401负责
    public PageInfo<Topic> getTopics() {
        TopicExample topicExample=new TopicExample();
        topicExample.setOrderByClause("heats desc");
        PageHelper.startPage(1,16);
        List<Topic> topics=topicDao.selectByExampleWithBLOBs(topicExample);
        return PageInfo.of(topics);
    }

    //根据topicid找话题，221701401负责
    public List<Topic> getTopicByID(String id) {
        TopicExample topicExample=new TopicExample();
        TopicExample.Criteria criteria=topicExample.createCriteria();
        criteria.andTopicidEqualTo(id);
        return topicDao.selectByExampleWithBLOBs(topicExample);
    }


    //根据tagid找话题,一组16,按热度降序，221701401负责
    public PageInfo<Topic> getTopicsByTag(String tagid) {
        TopicTagExample topicTagExample=new TopicTagExample();
        TopicTagExample.Criteria criteria=topicTagExample.createCriteria();
        criteria.andTagidEqualTo(tagid);
        PageHelper.startPage(1,16);
        List<TopicTagKey> topicsByTag = topicTagDao.selectByExample(topicTagExample);
        List<Topic> topicList = getTopicListByTagList(topicsByTag);
        return PageInfo.of(topicList);
    }

    //遍历TopicTag的list，找出每个topicid对应的topic信息，拼接成list返回，221701401负责
    public List<Topic> getTopicListByTagList (List<TopicTagKey> topicTagKeys) {
        List<Topic> resTopicList = new ArrayList<Topic>();
        for (TopicTagKey tmpTopicTagKey : topicTagKeys) {
            String tmpTopicId = tmpTopicTagKey.getTopicid();
            List<Topic> tmpTopicList = getTopicByID(tmpTopicId);
            resTopicList.addAll(tmpTopicList);
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
        TopicExample topicExample=new TopicExample();
        topicExample.setOrderByClause("heats desc");
        TopicExample.Criteria criteria=topicExample.createCriteria();
        criteria.andTitleEqualTo(title);
        PageHelper.startPage(1,16);
        //这里是模糊搜索
        List<Topic> test=topicDao.selectByTitleLike(title);
        return PageInfo.of(test);
        //这里是精确搜索
        //List<Topic> topics=topicDao.selectByExampleWithBLOBs(topicExample);
        //return PageInfo.of(topics);
    }

    //浏览量+1，221701401负责
    public List<Topic> updateViews(List<Topic> topic) {
        for (Topic value : topic) {
            value.setViews(value.getViews() + 1);
            topicDao.updateByPrimaryKey(value);
        }
        return topic;
    }

    //新增，对应赞、踩+1的情况,status：0为踩，1为赞，221701401负责
    public boolean insertLikesById(String topicid, int status) {
        int success=0;
        List<Topic> topics=getTopicByID(topicid);
        for (Topic topic : topics) {
            if (status == 1) topic.setLikes(topic.getLikes() + 1);
            else if (status == 0) topic.setUnlikes(topic.getUnlikes() + 1);
            success=topicDao.updateByPrimaryKey(topic);
        }
        if (success!=0) return true;
        else return false;
    }

    //修改，对应一方+1一方-1的情况,status：0为修改为踩，1为修改为赞，221701401负责
    public boolean updateLikesById(String topicid, int status) {
        int success=0;
        List<Topic> topics=getTopicByID(topicid);
        for (Topic topic : topics) {
            int likes=topic.getLikes(),unlikes=topic.getUnlikes();
            if (status == 1) {
                if (unlikes == 0) return false;
                topic.setLikes(likes + 1);
                topic.setUnlikes(unlikes - 1);
            }
            else if (status == 0) {
                if (likes == 0) return false;
                topic.setUnlikes(unlikes + 1);
                topic.setLikes(likes - 1);
            }
            success=topicDao.updateByPrimaryKey(topic);
        }
        if (success!=0) return true;
        else return false;

    }

    //删除，对应赞、踩-1的情况,status：0为踩，1为赞，221701401负责
    public boolean deleteLikesById(String topicid, int status) {
        int success=0;
        List<Topic> topics=getTopicByID(topicid);
        for (Topic topic : topics) {
            int likes=topic.getLikes(),unlikes=topic.getUnlikes();
            if (status == 1) {
                if (likes == 0) return false;
                topic.setLikes(likes - 1);
            }
            else if (status == 0) {
                if (unlikes == 0) return false;
                topic.setUnlikes(unlikes - 1);
            }
            success=topicDao.updateByPrimaryKey(topic);
        }
        if (success!=0) return true;
        else return false;

    }

}
