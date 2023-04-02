package com.xzcoder.kaoshi.po;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 考试信息拓展信息表
 *
 * @author 七友
 */
public class ExamKsDataCustom extends ExamKsDataWithBLOBs {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 拓展信息

	//考试分类信息
	private ExamKsClassifyCustom ksClassifyCustom;
	//试卷信息
	private ExamSjDataCustom sjDataCustom;
	private List<ExamKsUserCustom> userInfoList;


	private Integer pageSizeInt;
	private Integer userSignupInt;
	private Integer passingAgainInt;
	private Integer qsnRandomInt;
	private Integer optionRandomInt;
	private Integer publishRightkeyInt;
	private Integer resultRankInt;
	private Integer timeTypeInt;
	private Integer selectInt;
	private Integer reviewInt;


	/*
	 * 将java.util.Date转换成指定格式的字符串的getter方法
	 */
	// 考试开始时间
	public String getBeginTimeString() {
		return this.getBeginTime() == null ? null : df.format(this.getBeginTime());
	}
	// 考试结束时间
	public String getEndTimeString() {
		return this.getEndTime() == null ? null : df.format(this.getEndTime());
	}
	// 用户报名开始时间
	public String getSignupBeginTimeString() {
		return this.getSignupBeginTime() == null ? null : df.format(this.getSignupBeginTime());
	}
	// 用户报名结束时间
	public String getSignupEndTimeString() {
		return this.getSignupEndTime() == null ? null : df.format(this.getSignupEndTime());
	}
	// 考试结果发布时间
	public String getResultPublishTimeString() {
		return this.getResultPublishTime() == null ? null : df.format(this.getResultPublishTime());
	}
	// 添加时间
	public String getInsDateString() {
		return this.getInsDate() == null ? null : df.format(this.getInsDate());
	}
	// 更新时间
	public String getUpdDateString() {
		return this.getUpdDate() == null ? null : df.format(this.getUpdDate());
	}
	//发布时间的毫秒值
	public long getResultPublishTimeLong() {
		return this.getResultPublishTime() == null ? 0 : this.getResultPublishTime().getTime();
	}

	/*
	 * getter and setter
	 */
	public ExamKsClassifyCustom getKsClassifyCustom() {
		return ksClassifyCustom;
	}
	public void setKsClassifyCustom(ExamKsClassifyCustom ksClassifyCustom) {
		this.ksClassifyCustom = ksClassifyCustom;
	}
	public ExamSjDataCustom getSjDataCustom() {
		return sjDataCustom;
	}
	public void setSjDataCustom(ExamSjDataCustom sjDataCustom) {
		this.sjDataCustom = sjDataCustom;
	}
	public Integer getPageSizeInt() {
		return pageSizeInt;
	}
	public void setPageSizeInt(Integer pageSizeInt) {
		this.pageSizeInt = pageSizeInt;
	}
	public Integer getUserSignupInt() {
		return userSignupInt;
	}
	public void setUserSignupInt(Integer userSignupInt) {
		this.userSignupInt = userSignupInt;
	}
	public Integer getPassingAgainInt() {
		return passingAgainInt;
	}
	public void setPassingAgainInt(Integer passingAgainInt) {
		this.passingAgainInt = passingAgainInt;
	}
	public Integer getQsnRandomInt() {
		return qsnRandomInt;
	}
	public void setQsnRandomInt(Integer qsnRandomInt) {
		this.qsnRandomInt = qsnRandomInt;
	}
	public Integer getOptionRandomInt() {
		return optionRandomInt;
	}
	public void setOptionRandomInt(Integer optionRandomInt) {
		this.optionRandomInt = optionRandomInt;
	}
	public Integer getPublishRightkeyInt() {
		return publishRightkeyInt;
	}
	public void setPublishRightkeyInt(Integer publishRightkeyInt) {
		this.publishRightkeyInt = publishRightkeyInt;
	}
	public Integer getResultRankInt() {
		return resultRankInt;
	}
	public void setResultRankInt(Integer resultRankInt) {
		this.resultRankInt = resultRankInt;
	}
	public Integer getTimeTypeInt() {
		return timeTypeInt;
	}
	public void setTimeTypeInt(Integer timeTypeInt) {
		this.timeTypeInt = timeTypeInt;
	}
	public Integer getSelectInt() {
		return selectInt;
	}
	public void setSelectInt(Integer selectInt) {
		this.selectInt = selectInt;
	}
	public List<ExamKsUserCustom> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(List<ExamKsUserCustom> userInfoList) {
		this.userInfoList = userInfoList;
	}
	public Integer getReviewInt() {
		return reviewInt;
	}
	public void setReviewInt(Integer reviewInt) {
		this.reviewInt = reviewInt;
	}
}
