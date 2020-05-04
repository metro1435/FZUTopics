package com.fzutopic.service;

import com.fzutopic.model.Adminuser;
import com.fzutopic.model.User;

public interface AdminuserService {

    Adminuser findByUser(User user);

    Adminuser getAdmin(String adminid);
}
