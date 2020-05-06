package com.fzutopic.service;


import com.fzutopic.dao.TagDao;
import com.fzutopic.dao.TopicTagDao;
import com.fzutopic.model.Tag;
import com.fzutopic.model.TagExample;
import com.fzutopic.model.TopicTagExample;
import com.fzutopic.model.TopicTagKey;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class TagServiceImpl implements TagService{
    @Resource
    private TagDao tagDao;

    @Resource
    private TopicTagDao topicTagDao;

    //按热度降序获取tag的方法，限制8个，221701401负责
    public List<Tag> getTag(){
        TagExample tagExample=new TagExample();
        tagExample.setOrderByClause("times desc");
        tagExample.setLimit(8);
        return tagDao.selectByExample(tagExample);
    }

    //获取所有标签，221701401负责
    public List<Tag> getAllTag(){
        return tagDao.select();
    }

    //topic——tag是否存在，221701401
    public boolean TopicTagisExist(List<TopicTagKey> topicTagKeys) {
        TopicTagExample topicTagExample=new TopicTagExample();
        TopicTagExample.Criteria criteria=topicTagExample.createCriteria();
        for (TopicTagKey topicTagKey:topicTagKeys) {
            criteria.andTagidEqualTo(topicTagKey.getTagid());
            criteria.andTopicidEqualTo(topicTagKey.getTopicid());
            List<TopicTagKey> res=topicTagDao.selectByExample(topicTagExample);
            if (res.size()!=0) return true;
        }
        return false;
    }

    //插入标签，221701401负责
    public boolean insertTag(List<TopicTagKey> topicTagKeys) {
        int cnt=0;
        for (TopicTagKey topicTagKey:topicTagKeys) {
            boolean times=updateTimes(topicTagKey.getTagid());
            if (times) {
                boolean insert=topicTagDao.insertTopicTag(topicTagKey);
                if (!insert) return false;
            }
            else return false;
            cnt++;
        }
        if (cnt!=topicTagKeys.size()) return false;
        else return true;
    }

    //更新标签使用次数，221701401负责
    public boolean updateTimes(String tagid) {
        Tag tag=tagDao.selectByPrimaryKey(tagid);
        int times=tag.getTimes()+1;
        tag.setTimes(times);
        int flag=tagDao.updateByPrimaryKeySelective(tag);
        if (flag!=0) return true;
        else return false;
    }


    //管理员获取标签列表（一次40个），221701309负责
    public List<Tag> getTagList(){
        TagExample tagExample=new TagExample();
        //tagExample.setOrderByClause("times desc");
        tagExample.setLimit(40);
        return tagDao.selectByExample(tagExample);
    }

    //管理员新增标签，221701309负责
    public Tag createTag(Tag tag){
        tagDao.insert1(tag);
        return tag;
    }

    //管理员删除标签（指定id）,221701309
    public void deleteTag(String tagid){
        tagDao.deleteByPrimaryKey(tagid);
    }
}
