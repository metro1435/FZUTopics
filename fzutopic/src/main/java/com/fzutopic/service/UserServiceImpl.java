package com.fzutopic.service;

import com.fzutopic.dao.UserDao;
import com.fzutopic.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    //221701426
    @Override
    public User getUser(String userid){
        return userDao.selectByPrimaryKey(userid);
    }

    //221701426
    @Override
    public void updateNicknameIcon(User user)
    {
        userDao.updateByPrimaryKeySelective(user);
    }

    public User findByUser(User user){
        return userDao.selectByPrimaryKey(user.getUserid());
    }

    @Override
    public List<User> getUserListByCollege(String college) {
        List<User> users = userDao.selectByCollege(college);
        return users;
    }
}