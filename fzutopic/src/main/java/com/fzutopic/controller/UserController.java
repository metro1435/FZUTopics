package com.fzutopic.controller;

import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.User;
import com.fzutopic.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class UserController {


    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //获取一篇Article，使用GET方法，此为框架搭建时的测试代码
    @GetMapping("/article/{id}")
    public AjaxResponse getArticle(@PathVariable String id) {
        User user = userService.getUser(id);
        return AjaxResponse.success(user);
    }
}
