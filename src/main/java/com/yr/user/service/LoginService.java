package com.yr.user.service;

import com.yr.entitys.user.User;

import java.util.List;

public interface LoginService {
    //验证登录用户名是否存在
    List<User> queryLoginUserName(User loginUser);
    //验证登录用户名跟密码
    User queryLoginUser(User loginUser);
}
