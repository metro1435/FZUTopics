package com.fzutopic.utils;

import com.auth0.jwt.JWT;

import javax.servlet.http.HttpServletRequest;

public interface TokenUtil {

    static String getUserIdByRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        return String.valueOf(JWT.decode(token).getAudience().get(0));
    }

    static String getAdminIdByRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        return String.valueOf(JWT.decode(token).getAudience().get(0));
    }
}