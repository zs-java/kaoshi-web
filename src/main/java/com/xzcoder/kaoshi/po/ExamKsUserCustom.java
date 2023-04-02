package com.xzcoder.kaoshi.po;

import java.text.SimpleDateFormat;

public class ExamKsUserCustom extends ExamKsUserWithBLOBs implements Comparable<ExamKsUserCustom> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private ExamKsDataCustom ksDataCustom;
	private SysUserCustom userCustom;
	//是否 已读，已读：1 未读：0
	private Integer readStatus;


	public String getInsDateString() {
		return this.getInsDate() == null ? null : df.format(this.getInsDate());
	}
	public String getUpdDateString() {
		return this.getUpdDate() == null ? null : df.format(this.getUpdDate());
	}
	public String getSignupTimeString() {
		return this.getSignupTime() == null ? null : df.format(this.getSignupTime());
	}
	public String getBeginTimeString() {
		return getBeginTime() == null ? null : df.format(getBeginTime());
	}
	public String getEndTimeString() {
		return getEndTime() == null ? null : df.format(getEndTime());
	}


	public ExamKsDataCustom getKsDataCustom() {
		return ksDataCustom;
	}
	public void setKsDataCustom(ExamKsDataCustom ksDataCustom) {
		this.ksDataCustom = ksDataCustom;
	}
	public SysUserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(SysUserCustom userCustom) {
		this.userCustom = userCustom;
	}
	public Integer getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	@Override
	public int compareTo(ExamKsUserCustom ksUser) {
		if(this.getKsDataCustom().getKsId() > ksUser.getKsDataCustom().getKsId())
			return -1;
		else if (this.getKsDataCustom().getKsId() ==  ksUser.getKsDataCustom().getKsId())
			if(this.getKsDataCustom().getBeginTime().getTime() > ksUser.getKsDataCustom().getBeginTime().getTime())
				return -1;
			else
				return 1;
		else
			return 1;
	}

}
