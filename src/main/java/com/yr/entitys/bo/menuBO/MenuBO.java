package com.yr.entitys.bo.menuBO;

import java.util.List;

public class MenuBO {
    /*"title": "文章列表",
			"icon": "icon-text",
			"href": "page/news/newsList.html",
			"spread": false*/
    private Integer id;//id
    private String title;//名称
    private String icon;//图标
    private String href;//url
    private Integer pid;
    private Boolean spread;//是否展开
    private List<MenuBO> children;//子菜单集合
    private String method;//url的请求方式（要控制大写）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<MenuBO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuBO> children) {
        this.children = children;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MenuBO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", pid=" + pid +
                ", spread=" + spread +
                ", children=" + children +
                ", method='" + method + '\'' +
                '}';
    }
}
