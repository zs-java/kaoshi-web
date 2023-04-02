package com.xzcoder.kaoshi.po;

import java.math.BigDecimal;
import java.util.Date;

public class ExamKsUser {
    private Integer id;

    private Integer ksId;

    private Integer sjId;

    private Integer userId;

    private Date signupTime;

    private Integer signupState;

    private Date reviewTime;

    private Integer times;

    private Date beginTime;

    private Date endTime;

    private BigDecimal score;

    private Integer state;

    private Boolean readFlg;

    private Boolean wrongFlg;

    private Boolean del;

    private String insUser;

    private Date insDate;

    private String updUser;

    private Date updDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKsId() {
        return ksId;
    }

    public void setKsId(Integer ksId) {
        this.ksId = ksId;
    }

    public Integer getSjId() {
        return sjId;
    }

    public void setSjId(Integer sjId) {
        this.sjId = sjId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }

    public Integer getSignupState() {
        return signupState;
    }

    public void setSignupState(Integer signupState) {
        this.signupState = signupState;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getReadFlg() {
        return readFlg;
    }

    public void setReadFlg(Boolean readFlg) {
        this.readFlg = readFlg;
    }

    public Boolean getWrongFlg() {
        return wrongFlg;
    }

    public void setWrongFlg(Boolean wrongFlg) {
        this.wrongFlg = wrongFlg;
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
}
