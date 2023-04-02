package com.xzcoder.kaoshi.po;

import java.util.Date;

public class ExamKsData {
    private Integer ksId;

    private Integer sjId;

    private Integer ksClassifyId;

    private String name;

    private Integer passScore;

    private Integer maxTimes;

    private Boolean pageSize;

    private Date beginTime;

    private Date endTime;

    private Integer totalTime;

    private Boolean userSignupFlg;

    private Date signupBeginTime;

    private Date signupEndTime;

    private Boolean passingAgainFlg;

    private Boolean qsnRandomFlg;

    private Boolean timeType;

    private Integer disableTime;

    private Integer disableSubmit;

    private Date resultPublishTime;

    private Boolean publishRightkeyFlg;

    private Boolean resultRankFlg;

    private Boolean review;

    private String pic;

    private String uuid;

    private Boolean del;

    private String insUser;

    private Date insDate;

    private String updUser;

    private Date updDate;

    private Byte selectFlg;

    private Boolean optionRandomFlg;

    private Integer credit;

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

    public Integer getKsClassifyId() {
        return ksClassifyId;
    }

    public void setKsClassifyId(Integer ksClassifyId) {
        this.ksClassifyId = ksClassifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPassScore() {
        return passScore;
    }

    public void setPassScore(Integer passScore) {
        this.passScore = passScore;
    }

    public Integer getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(Integer maxTimes) {
        this.maxTimes = maxTimes;
    }

    public Boolean getPageSize() {
        return pageSize;
    }

    public void setPageSize(Boolean pageSize) {
        this.pageSize = pageSize;
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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Boolean getUserSignupFlg() {
        return userSignupFlg;
    }

    public void setUserSignupFlg(Boolean userSignupFlg) {
        this.userSignupFlg = userSignupFlg;
    }

    public Date getSignupBeginTime() {
        return signupBeginTime;
    }

    public void setSignupBeginTime(Date signupBeginTime) {
        this.signupBeginTime = signupBeginTime;
    }

    public Date getSignupEndTime() {
        return signupEndTime;
    }

    public void setSignupEndTime(Date signupEndTime) {
        this.signupEndTime = signupEndTime;
    }

    public Boolean getPassingAgainFlg() {
        return passingAgainFlg;
    }

    public void setPassingAgainFlg(Boolean passingAgainFlg) {
        this.passingAgainFlg = passingAgainFlg;
    }

    public Boolean getQsnRandomFlg() {
        return qsnRandomFlg;
    }

    public void setQsnRandomFlg(Boolean qsnRandomFlg) {
        this.qsnRandomFlg = qsnRandomFlg;
    }

    public Boolean getTimeType() {
        return timeType;
    }

    public void setTimeType(Boolean timeType) {
        this.timeType = timeType;
    }

    public Integer getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Integer disableTime) {
        this.disableTime = disableTime;
    }

    public Integer getDisableSubmit() {
        return disableSubmit;
    }

    public void setDisableSubmit(Integer disableSubmit) {
        this.disableSubmit = disableSubmit;
    }

    public Date getResultPublishTime() {
        return resultPublishTime;
    }

    public void setResultPublishTime(Date resultPublishTime) {
        this.resultPublishTime = resultPublishTime;
    }

    public Boolean getPublishRightkeyFlg() {
        return publishRightkeyFlg;
    }

    public void setPublishRightkeyFlg(Boolean publishRightkeyFlg) {
        this.publishRightkeyFlg = publishRightkeyFlg;
    }

    public Boolean getResultRankFlg() {
        return resultRankFlg;
    }

    public void setResultRankFlg(Boolean resultRankFlg) {
        this.resultRankFlg = resultRankFlg;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
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

    public Byte getSelectFlg() {
        return selectFlg;
    }

    public void setSelectFlg(Byte selectFlg) {
        this.selectFlg = selectFlg;
    }

    public Boolean getOptionRandomFlg() {
        return optionRandomFlg;
    }

    public void setOptionRandomFlg(Boolean optionRandomFlg) {
        this.optionRandomFlg = optionRandomFlg;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
