package com.fzutopic.service;


import com.fzutopic.dao.TagDao;
import com.fzutopic.model.Tag;
import com.fzutopic.model.TagExample;
import lombok.extern.slf4j.Slf4j;
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

    //按热度降序获取tag的方法，限制8个，221701401负责
    public List<Tag> getTag(){
        TagExample tagExample=new TagExample();
        tagExample.setOrderByClause("times desc");
        tagExample.setLimit(8);
        return tagDao.selectByExample(tagExample);
    }
}
