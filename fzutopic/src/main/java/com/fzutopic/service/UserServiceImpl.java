package com.fzutopic.service;

import com.fzutopic.dao.UserDao;
import com.fzutopic.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Service
@RestController
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    //获取所有用户
    public User getUser(String id){
        return userDao.selectByPrimaryKey(id);
    }

    public User findByUser(User user){
        return userDao.selectByPrimaryKey(user.getUserid());
    }

}