package com.xzcoder.kaoshi.po;

import java.util.Date;

public class Exercises {
    private Integer id;

    private Integer userId;

    private Date dayDate;

    private String stClassifys;

    private String stLevels;

    private String stKnowledges;

    private Integer totalCount;

    private Integer rightCount;

    private Date insDate;

    private Date updDate;

    private String uuid;

    private String stIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public String getStClassifys() {
        return stClassifys;
    }

    public void setStClassifys(String stClassifys) {
        this.stClassifys = stClassifys == null ? null : stClassifys.trim();
    }

    public String getStLevels() {
        return stLevels;
    }

    public void setStLevels(String stLevels) {
        this.stLevels = stLevels == null ? null : stLevels.trim();
    }

    public String getStKnowledges() {
        return stKnowledges;
    }

    public void setStKnowledges(String stKnowledges) {
        this.stKnowledges = stKnowledges == null ? null : stKnowledges.trim();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRightCount() {
        return rightCount;
    }

    public void setRightCount(Integer rightCount) {
        this.rightCount = rightCount;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getStIds() {
        return stIds;
    }

    public void setStIds(String stIds) {
        this.stIds = stIds == null ? null : stIds.trim();
    }
}
