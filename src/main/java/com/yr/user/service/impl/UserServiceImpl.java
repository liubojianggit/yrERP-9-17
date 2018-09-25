package com.yr.user.service.impl;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;
import com.yr.user.dao.UserDao;
import com.yr.user.service.UserService;
import com.yr.util.Md5Utils;
import com.yr.util.SerializeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;
    @Autowired
    private JedisManager jedisManager;


    /**
     * 分页的形式查询user表的数据
     * @param page
     */
    public void query(Page<UserBo> page){
        page.setTotalRecord(userDao.getCount(page));//查询总条数加入page中
        List<UserBo> list = userDao.query(page);//分页查询的数据
        page.setPageData(list);//将集合放入page中
    }

    /**
     * 添加用户信息
     * @param user
     */
    public void add(User user){
        user.setPassword(Md5Utils.getMd5Password(user.getPassword()));//将密码加密后置入user中
        userDao.add(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    public void update(User user){
        userDao.update(user);
    }

    /**
     * 删除用户信息
     * @param id
     */
    public void delete(Integer id){
        userDao.delete(id);
    }

    /**
     * 根据id查询用户数据
     * @param id
     * @return User
     */
    public User getById(Integer id){
        return userDao.getById(id);
    }

    /**
     * 根据用户id返回角色名集合
     * @param id
     * @return List<String>
     */
    public List<String> getRoles(Integer id){
        return userDao.getRoles(id);
    }

    /**
     * 根据用户id返回权限集合
     * @param id
     * @return List<Permission>
     */
    public List<Permission> getPermissions(Integer id){
        return userDao.getPermissions(id);
    }

    /**
     * 给用户赋角色
     * @param id
     * @param roleIds
     */
    public void setRoles(Integer id,Integer[] roleIds){
        userDao.deleteRoles(id);
        userDao.addRoles(id,roleIds);
        List<String> roles = getRoles(id);//获得角色对象
        Jedis jedis = jedisManager.getJedis();
        //将角色字符串序列化后放入redis中
        jedis.set("roles".getBytes(), SerializeUtil.serialize(roles));
    }

    /**
     * 根据用户名获得User对象
     * @param name
     * @return User
     */
    public User getByName(String name){
        return userDao.getByName(name);
    }

    /**
     * 部门删除时调用,根据部门编号删除用户
     */
    public void delete(String department){
        userDao.delete(department);
    }
}
