package com.xzcoder.kaoshi.po;

public class ExamWrong {
    private Integer id;

    private Integer userId;

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

    public String getStIds() {
        return stIds;
    }

    public void setStIds(String stIds) {
        this.stIds = stIds == null ? null : stIds.trim();
    }
}
