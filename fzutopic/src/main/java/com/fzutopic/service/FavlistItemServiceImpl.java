package com.fzutopic.service;

import com.fzutopic.dao.FavlistItemDao;
import com.fzutopic.model.FavlistItemExample;
import com.fzutopic.model.FavlistItemKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 1309
 */
@Slf4j
@Service
@RestController
public class FavlistItemServiceImpl implements FavlistItemService{

    @Resource
    private FavlistItemDao favlistItemDao;
    public boolean getfavstatus(String collectedid, String userid) {
        FavlistItemExample example = new FavlistItemExample();
        FavlistItemExample.Criteria criteria = example.createCriteria();
        criteria.andCollectedidEqualTo(collectedid);
        List<FavlistItemKey> favlistItemKeys = favlistItemDao.selectByExample(example);
        for (FavlistItemKey favlistItemKey : favlistItemKeys) {
            if(favlistItemKey.getFavlistid().substring(0, 9).equals(userid))
                return true;
        }
        return false;
    }
}
