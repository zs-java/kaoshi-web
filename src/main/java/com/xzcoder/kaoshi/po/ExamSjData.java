package com.xzcoder.kaoshi.po;

import java.math.BigDecimal;
import java.util.Date;

public class ExamSjData {
    private Integer sjId;

    private String title;

    private Integer sjClassifyId;

    private String des;

    private BigDecimal totalScore;

    private Integer count;

    private Boolean review;

    private Boolean del;

    private String insUser;

    private Date insDate;

    private String updUser;

    private Date updDate;

    private Integer visable;

    public Integer getSjId() {
        return sjId;
    }

    public void setSjId(Integer sjId) {
        this.sjId = sjId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSjClassifyId() {
        return sjClassifyId;
    }

    public void setSjClassifyId(Integer sjClassifyId) {
        this.sjClassifyId = sjClassifyId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
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

	public Integer getVisable() {
		return visable;
	}

	public void setVisable(Integer visable) {
		this.visable = visable;
	}
}
