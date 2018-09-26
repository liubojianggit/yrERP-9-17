package com.yr.user.dao;

import com.yr.entitys.user.User;

import java.util.List;

public interface LoginDao {
    List<User> queryLoginUserName(User loginUser);
    User queryLoginUser(User loginUser);
}
