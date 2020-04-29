package com.fzutopic.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fzutopic.model.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String getToken(User user) {
        // 存储id，无过期时间，以 password 作为 token 的密钥
        return JWT.create().withAudience(String.valueOf(user.getUserid()))
                  .sign(Algorithm.HMAC256(user.getPassword()));
    }
}
