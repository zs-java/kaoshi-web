package com.xzcoder.kaoshi.po;

public class SysGroup {
    private Integer groupId;

    private Integer groupPid;

    private String groupName;

    private Integer sort;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupPid() {
        return groupPid;
    }

    public void setGroupPid(Integer groupPid) {
        this.groupPid = groupPid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
