package com.fzutopic.controller;

import com.fzutopic.annotation.AdminLoginToken;
import com.fzutopic.annotation.PassToken;
import com.fzutopic.annotation.UserLoginToken;
import com.fzutopic.model.Adminuser;
import com.fzutopic.model.AjaxResponse;
import com.fzutopic.model.User;
import com.fzutopic.service.AdminuserService;
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
    private AdminuserService adminuserService;
    @Resource
    private TokenService tokenService;

    //登录402
    @PassToken
    @CrossOrigin
    @PostMapping("/login")
    public @ResponseBody
    AjaxResponse login(@RequestBody User user) {
        if (!user.getUserid().substring(0, 5).equals("admin")) {
            User uservo = userService.findByUser(user);
            if (uservo == null) {
                //账号不存在
                return AjaxResponse.error(400, "账号不存在");
            } else if (!uservo.getPassword().equals(user.getPassword())) {
                //密码错误
                return AjaxResponse.error(401, "密码错误");
            } else {
                //验证正确
                String token = tokenService.getToken(uservo);
                return AjaxResponse.success(uservo, token);
            }
        } else {
            Adminuser adminvo = adminuserService.findByUser(user);
            if (adminvo == null) {
                //账号不存在
                return AjaxResponse.error(400, "管理员账号不存在");
            } else if (!adminvo.getPassword().equals(user.getPassword())) {
                //密码错误
                return AjaxResponse.error(401, "密码错误");
            } else {
                //验证正确
                String token = tokenService.getToken(adminvo);
                return AjaxResponse.success(adminvo, token);
            }

        }
    }

    //退出402
    @AdminLoginToken
    @CrossOrigin
    @GetMapping(value = "/logout")
    public AjaxResponse logout(HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getAdminIdByRequest(httpServletRequest);
        return AjaxResponse.success(userid);
    }

    //通过token获取userid402
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/token")
    public AjaxResponse getIdByToken(HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getAdminIdByRequest(httpServletRequest);
        return AjaxResponse.success(userid);
    }

    //通过token获取adminid402
    @AdminLoginToken
    @CrossOrigin
    @GetMapping(value = "/admin/token")
    public AjaxResponse getAdminidByToken(HttpServletRequest httpServletRequest) {
        String adminid = TokenUtil.getAdminIdByRequest(httpServletRequest);
        return AjaxResponse.success(adminid);
    }

    //通过token获取nickname402
    @UserLoginToken
    @CrossOrigin
    @GetMapping(value = "/token/nickname")
    public AjaxResponse getNameByToken(HttpServletRequest httpServletRequest) {
        String userid = TokenUtil.getAdminIdByRequest(httpServletRequest);
        User user = userService.getUser(userid);
        return AjaxResponse.success(user.getNickname());
    }
}
