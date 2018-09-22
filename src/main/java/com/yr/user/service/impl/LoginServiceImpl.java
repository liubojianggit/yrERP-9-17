package com.yr.user.service.impl;

import com.yr.user.dao.LoginDao;
import com.yr.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Qualifier("loginDaoImpl")
    @Autowired
    private LoginDao loginDao;


/* 查看用户的状态 */

    @Override
    public boolean userstates( Integer states) {
        int s=loginDao.userstates(states);
        if(s==1){
            return true;
        }
        return false;
    }


/* 验证用户密码是否错误 */

    @Override
    public boolean getMatchCount(String name, String password) {
        int t = loginDao.getMatchCount(name, password);
        if (t == 1) {
            return true;
        }
        return false;
    }

/* 验证用户是否为空*/

    @Override
    public boolean All(String name) {
        if (name !=null && name.trim().equals("")){
            return true;
        }
        return false;
    }

/* 验证密码是否为空*/

    @Override
    public boolean Alls(String passwrod) {
        if (passwrod !=null && passwrod.trim().equals("")){
            return true;
        }
        return false;
    }
}

