package com.fzutopic.service;


import com.fzutopic.dao.AdminuserDao;
import com.fzutopic.dao.UserDao;
import com.fzutopic.model.Adminuser;
import com.fzutopic.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Service
@RestController
public class AdminuserSeviceImpl implements AdminuserService {
    @Resource
    private AdminuserDao adminuserDao;

    public Adminuser findByUser(User user) {
        return adminuserDao.selectByPrimaryKey(user.getUserid());
    }

    public Adminuser getAdmin(String adminid){
        return adminuserDao.selectByPrimaryKey(adminid);
    }


}
