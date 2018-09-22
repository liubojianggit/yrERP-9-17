package com.yr.entitys.bo.user;

import com.yr.entitys.user.Permission;

public class PermissionBo {
    private Permission permission;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "PermissionsBO{" +
                "permission=" + permission +
                '}';
    }
}
