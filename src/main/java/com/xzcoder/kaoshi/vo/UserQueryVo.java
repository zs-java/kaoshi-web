package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * UserQueryVo
 * 用户信息组合查询条件包装类
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class UserQueryVo {

    //用户信息
    private SysUserCustom userCustom;
    //用户性别，1：男，2：女，-1：无要求
    private Integer userGender;
    //组别信息
    private SysGroupCustom groupCustom;
    //角色信息
    private SysRoleCustom roleCustom;
    //分页
    private PageBean pageBean;
    //查询分组id以及该分组的子分组
    private Integer[] groupIds;
    // NOT IN
    private Integer[] withOutIds = null;
    // IN
    private Integer[] withIds = null;

    private Integer ksId;
    private String username;
    private Integer groupId = -1;


    public Integer[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Integer[] groupIds) {
        this.groupIds = groupIds;
    }

    public SysUserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(SysUserCustom userCustom) {
        this.userCustom = userCustom;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public SysGroupCustom getGroupCustom() {
        return groupCustom;
    }

    public void setGroupCustom(SysGroupCustom groupCustom) {
        this.groupCustom = groupCustom;
    }

    public SysRoleCustom getRoleCustom() {
        return roleCustom;
    }

    public void setRoleCustom(SysRoleCustom roleCustom) {
        this.roleCustom = roleCustom;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Integer[] getWithOutIds() {
        return withOutIds;
    }

    public void setWithOutIds(Integer[] withOutIds) {
        this.withOutIds = withOutIds;
    }

    public Integer[] getWithIds() {
        return withIds;
    }

    public void setWithIds(Integer[] withIds) {
        this.withIds = withIds;
    }

    public Integer getKsId() {
        return ksId;
    }

    public void setKsId(Integer ksId) {
        this.ksId = ksId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
