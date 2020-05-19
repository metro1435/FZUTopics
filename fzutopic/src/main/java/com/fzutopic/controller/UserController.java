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
import java.util.List;

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
    public AjaxResponse updateNicknameIcon(@RequestBody User user,HttpServletRequest httpServletRequest){
        String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
        user.setUserid(userid);
        userService.updateNicknameIcon(user);
        return AjaxResponse.success("修改成功");
    }

    //通过userid获取nickname402
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/user/nickname/{userid}")
    public AjaxResponse getNameByUserId(@PathVariable String userid) {
        User user = userService.getUser(userid);
        return AjaxResponse.success(user.getNickname());
    }
    //通过College获取UserList
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/user/list/{college}")
    public AjaxResponse getUserListByCollege(@PathVariable String college) {
        List<User> user = userService.getUserListByCollege(college);
        return AjaxResponse.success(user);
    }

}
