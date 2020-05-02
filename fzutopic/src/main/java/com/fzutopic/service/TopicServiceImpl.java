package com.fzutopic.service;

import com.fzutopic.dao.TagDao;
import com.fzutopic.dao.TopicDao;
import com.fzutopic.dao.TopicTagDao;
import com.fzutopic.model.Tag;
import com.fzutopic.model.Topic;
import com.fzutopic.model.TopicTagExample;
import com.fzutopic.model.TopicTagKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RestController
public class TopicServiceImpl implements TopicService{
    @Resource
    private TopicDao topicDao;
    /*@Resource
    private TagDao tagDao;
    @Resource
    private TopicTagDao topicTagDao;*/

    public Topic createTopic(Topic topic){
        topicDao.insert1(topic);
        /*String id = topic.getTopicid();

        TopicTagExample topicTagExample=new TopicTagExample();
        TopicTagExample.Criteria criteria=topicTagExample.createCriteria();
        criteria.andTopicidEqualTo(id);
        List<TopicTagKey> topicTag = topicTagDao.selectByExample(topicTagExample);

        for(int i=0;i<topicTag.size();i++)
        {
            TopicTagKey tagitem = topicTag.get(i);
            String tagid = tagitem.getTagid();
            Tag tag = tagDao.selectByPrimaryKey(tagid);

            int count =tag.getTimes()+1;
            tag.setTimes(count);
            tagDao.updateByPrimaryKey(tag);
        }*/

        return topic;
    }

}
