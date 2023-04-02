package com.xzcoder.kaoshi.po;

import javax.validation.constraints.NotNull;

import com.xzcoder.kaoshi.admin.controller.validation.RoleAddValidGroup;


public class SysRole {
    private Integer roleId;

    @NotNull(message="{role.roleName.isNull}",groups={RoleAddValidGroup.class})
    private String roleName;

    private String des;

    private Integer sort;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
