package com.fzutopic.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fzutopic.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    //设置过期时间
    private static final long EXPIRE_TIME = 7 * 24 * 3600 * 1000;

    public String getToken(User user) {
        Date expiresDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withAudience(String.valueOf(user.getUserid()))
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(user.getPassword()));
    }
}
