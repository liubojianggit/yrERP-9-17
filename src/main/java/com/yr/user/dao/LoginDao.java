package com.yr.user.dao;

import com.yr.entitys.user.User;

public interface LoginDao {

    //查看用户状态
    int userstates( Integer states);


    //验证用户名密码是否正确
    int getMatchCount(String name, String password);

}
