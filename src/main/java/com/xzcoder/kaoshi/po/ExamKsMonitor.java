package com.xzcoder.kaoshi.po;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * 考试监控po包装类
 * @author 七友
 *
 */
public class ExamKsMonitor extends ExamKsDataWithBLOBs {

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//考试总人数
	private Integer totalCount;
	//未参加考试人数
	private Integer notJoinCount;
	//正在考试人数
	private Integer inExamCount;
	//交卷人数
	private Integer examOverCount;

	//平均分
	private Double scoreAvg;
	//及格人数
	private Integer passUserCount;
	private Double totalScore;

	public String getPassPercent() {
		if(examOverCount == 0)
			return "无";

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		String str = numberFormat.format((float)passUserCount/(float)examOverCount*100);;
		return str + "%";
	}

	/*
	 * 用户前端combobox标识
	 */
	public Integer getId() {
		return this.getKsId();
	}
	public String getText() {
		return this.getName();
	}

	/*
	 * DateFormat getter
	 */
	public String getBeginTimeString() {
		return getBeginTime() == null ? null : df.format(getBeginTime());
	}
	public String getEndTimeString() {
		return getEndTime() == null ? null : df.format(getEndTime());
	}


	/*
	 * GETTER AND SETTER
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getNotJoinCount() {
		return notJoinCount;
	}
	public void setNotJoinCount(Integer notJoinCount) {
		this.notJoinCount = notJoinCount;
	}
	public Integer getInExamCount() {
		return inExamCount;
	}
	public void setInExamCount(Integer inExamCount) {
		this.inExamCount = inExamCount;
	}
	public Integer getExamOverCount() {
		return examOverCount;
	}
	public void setExamOverCount(Integer examOverCount) {
		this.examOverCount = examOverCount;
	}
	public Double getScoreAvg() {
		return scoreAvg;
	}
	public void setScoreAvg(Double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}
	public Integer getPassUserCount() {
		return passUserCount;
	}
	public void setPassUserCount(Integer passUserCount) {
		this.passUserCount = passUserCount;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
}
