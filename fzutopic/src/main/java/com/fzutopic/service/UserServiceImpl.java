package com.fzutopic.service;

import com.fzutopic.dao.UserDao;
import com.fzutopic.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
@RestController
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    //获取所有用户，搭建框架时测试用
    public User getUser(String id){
//        ArrayList<User> users=new ArrayList<>();
//        for(int i=1;i<maxGetNum;i++)//假设同时查找maxGetNum个user
//        {
//            if(userDao.selectByPrimaryKey(i)!=null)
//            {
//                users.add(userDao.selectByPrimaryKey(i));
//            }
//        }
        return userDao.selectByPrimaryKey(id);
    }

}