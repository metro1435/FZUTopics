package com.fzutopic.service;


import com.fzutopic.model.User;

public interface UserService {

    //获取所有用户
    User getUser(String id);

    User findByUser(User user);



}
