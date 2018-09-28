package com.yr.user.service.impl;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;
import com.yr.user.dao.RoleDao;
import com.yr.user.service.RoleService;
import com.yr.user.service.UserService;
import com.yr.util.JsonDateValueProcessor;
import com.yr.util.SerializeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;
    @Autowired
    private JedisManager jedisManager;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    /**
     * 分页的形式查询role表的数据
     * @param page
     */
    public String query(Page<RoleBo> page){
        page.setTotalRecord(roleDao.getCount(page));//查询总条数加入page中
        List<RoleBo> list = roleDao.query(page);//分页查询的数据
        //将对象转为json
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        String json = "{\"code\": 0,\"msg\": \"\",\"count\": "+page.getTotalRecord()+",\"data\":"+jsonArray+"}";
        return json;
    }

    /**
     * 添加角色信息
     * @param role
     */
    public void add(Role role){
        roleDao.add(role);
    }

    /**
     * 修改角色信息
     * @param role
     */
    public void update(Role role){
        roleDao.update(role);
    }

    /**
     * 删除角色信息
     * @param id
     */
    public void delete(Integer[] id){
        roleDao.delete(id);
    }

    /**
     * 根据id查询角色数据
     * @param id
     * @return Role
     */
    public Role getById(Integer id){
        return roleDao.getById(id);
    }

    /**
     * 给角色赋权限
     * @param id
     * @param permissionIds
     */
    public void setPermissions(Integer id,Integer[] permissionIds){
        roleDao.deletePermissions(id);
        roleDao.addPermissions(id,permissionIds);

        Jedis jedis = jedisManager.getJedis();
        //将角色字符串序列化后放入redis中
        List<Permission> permissions = userService.getPermissions(id);
        //将权限对象序列化后放入redis中
        jedis.set("permissions".getBytes(), SerializeUtil.serialize(permissions));
    }
}
