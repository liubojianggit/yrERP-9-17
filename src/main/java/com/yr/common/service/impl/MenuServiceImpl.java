package com.yr.common.service.impl;

import com.yr.common.dao.MenuDao;
import com.yr.common.dao.impl.MenuDaoImpl;
import com.yr.common.service.BaseService;
import com.yr.common.service.MenuService;
import com.yr.entitys.bo.menuBO.MenuBO;
import com.yr.entitys.menu.Menu;
import com.yr.entitys.user.Permission;
import com.yr.user.service.PermissionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {
    @Autowired
    private MenuDao menuDaoImp;
    @Autowired
    @Qualifier("permissionServiceImpl")
    private PermissionService permissionService;

    /**
     * 返回固定格式，固定字段名的json字符串到页面，用来动态显示主页面右边的二级菜单
     */
    @Override
    public String query() {
        List<MenuBO> menuTurnMenuBOList = new ArrayList<MenuBO>();
        for (Menu menu : menuDaoImp.query()) {
            //这里new对象，要使用自动注入吗？？
            MenuBO menuBO = new MenuBO();
            menuBO.setId(menu.getId());
            menuBO.setTitle(menu.getName());
            menuBO.setIcon(menu.getPic());
            menuBO.setPid(menu.getPid());
            menuBO.setHref(menu.getUrl());
            menuBO.setMethod(menu.getMethod());
            menuTurnMenuBOList.add(menuBO);
        }

        List<MenuBO> menuBOList = new ArrayList<MenuBO>();
        for (MenuBO menuBO : menuTurnMenuBOList) {
            if (menuBO.getPid() == 0){
                List<MenuBO> subMenuList = new ArrayList<MenuBO>();
                for (MenuBO menuBO1 : menuTurnMenuBOList) {
                    if (menuBO1.getPid() == menuBO.getId()){
                        subMenuList.add(menuBO1);
                    }
                }
                menuBO.setChildren(subMenuList);
                menuBOList.add(menuBO);
            }
        }
        String menuJsonStr = JSONArray.fromObject(menuBOList).toString();
        String contentManagement = "{\"contentManagement\":"+menuJsonStr+"}";

        System.out.println(contentManagement);
        return contentManagement;
    }

    /**
     * 添加
     * @param menu
     */
    public void add(Menu menu){
        ((MenuDaoImpl)menuDaoImp).add(menu);
        Permission permission = new Permission();
        permission.setMethod(menu.getMethod());
        permission.setName(menu.getName());
        permission.setUrl(menu.getUrl());
        permission.setSupId(0);
        permissionService.add(permission); //添加权限
    }
}
