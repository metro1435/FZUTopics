package com.fzutopic.controller;

import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.User;
import com.fzutopic.service.UserServiceImpl;
import com.fzutopic.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserController {


    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //221701426
    //获得个人信息
    @CrossOrigin
    @GetMapping("/user/setting")
    @UserLoginToken
    public User getInfo(HttpServletRequest httpServletRequest){
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        return userService.getUser(userid);
    }

    //221701426
    //修改个人昵称
    //修改个人头像（url）
    @CrossOrigin
    @PutMapping("/user/setting")
    @UserLoginToken
    public void updateNicknameIcon(@RequestBody User user,HttpServletRequest httpServletRequest){
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        user.setUserid(userid);
        userService.updateNicknameIcon(user);
    }
}
