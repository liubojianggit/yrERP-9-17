package com.yr.user.service;

import com.yr.entitys.user.User;

public interface LoginService {

    //查看用户的状态
    boolean userstates(Integer  states);

    //验证用户和密码是否正确
   boolean getMatchCount(String name , String password);

    //判断用户不能为空
   boolean All(String name );

    //判断密码不能为空
   boolean Alls(String passwrod);

}
