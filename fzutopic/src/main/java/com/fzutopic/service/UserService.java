package com.fzutopic.service;


import com.fzutopic.model.User;

public interface UserService {



    User findByUser(User user);

    //221701426
    //获得个人信息
    public User getUser(String userid);

    //221701426
    //修改个人昵称
    //修改个人头像（url）
    public void updateNicknameIcon(User user);

}
