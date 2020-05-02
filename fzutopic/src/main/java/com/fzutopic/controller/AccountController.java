package com.fzutopic.controller;

import com.fzutopic.annotation.PassToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.FavlistItemKey;
import com.fzutopic.model.User;
import com.fzutopic.service.FavlistItemService;
import com.fzutopic.service.TokenService;
import com.fzutopic.service.UserService;
import com.fzutopic.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AccountController {

    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private FavlistItemService favlistItemService;

    @PassToken
    @GetMapping("/login")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        User uservo = userService.findByUser(user);
        if(uservo == null) {
            //账号不存在
            return AjaxResponse.error(400,"账号不存在");
        } else if(!uservo.getPassword().equals(user.getPassword())) {
            //密码错误
            return AjaxResponse.error(401,"密码错误");
        } else {
            //验证正确
            String token = tokenService.getToken(uservo);
            return AjaxResponse.success(uservo, token);
        }
    }

   @UserLoginToken
   @GetMapping(value = "/logout")
   public AjaxResponse logout(HttpServletRequest httpServletRequest) {
       String userid = TokenUtil.getUserIdByRequest(httpServletRequest);
       return AjaxResponse.success(userid);
   }

}
