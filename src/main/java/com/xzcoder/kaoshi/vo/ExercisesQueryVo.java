package com.xzcoder.kaoshi.vo;

import java.util.Date;

import com.xzcoder.kaoshi.common.po.PageBean;

/**
 * ExercisesQueryVo
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ExercisesQueryVo {

    private String username;
    private Integer groupId = 0;
    private Date dayDate;
    private Integer[] groupIds;
    private String date;

    private Integer type = 0;

    private Integer userId;

    private PageBean pageBean;

    private String dayDateString;


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

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Integer[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Integer[] groupIds) {
        this.groupIds = groupIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDayDateString() {
        return dayDateString;
    }

    public void setDayDateString(String dayDateString) {
        this.dayDateString = dayDateString;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
