package com.xzcoder.kaoshi.po;

import java.util.Date;

public class ExamStMain {
    private Integer questionId;

    private Integer userId;

    private Integer stTypeId;

    private Integer stClassifyId;

    private Integer stLevelId;

    private Integer stKnowledgeId;

    private Integer sort;

    private Boolean del;

    private String insUser;

    private Date insDate;

    private String updUser;

    private Date updDate;

    private String title;

    private Integer visable;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStTypeId() {
        return stTypeId;
    }

    public void setStTypeId(Integer stTypeId) {
        this.stTypeId = stTypeId;
    }

    public Integer getStClassifyId() {
        return stClassifyId;
    }

    public void setStClassifyId(Integer stClassifyId) {
        this.stClassifyId = stClassifyId;
    }

    public Integer getStLevelId() {
        return stLevelId;
    }

    public void setStLevelId(Integer stLevelId) {
        this.stLevelId = stLevelId;
    }

    public Integer getStKnowledgeId() {
        return stKnowledgeId;
    }

    public void setStKnowledgeId(Integer stKnowledgeId) {
        this.stKnowledgeId = stKnowledgeId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public String getInsUser() {
        return insUser;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser == null ? null : insUser.trim();
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser == null ? null : updUser.trim();
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

	public Integer getVisable() {
		return visable;
	}

	public void setVisable(Integer visable) {
		this.visable = visable;
	}
}
