package com.xzcoder.kaoshi.po;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserExerData implements Comparable<UserExerData> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private String uuid;
	//用户信息
	private SysUserCustom user;
	//当天日期
	private Date dayDate;
	//刷题总数
	private Integer totalCount;
	//正确数
	private Integer rightCount;
	//总时长
	private Integer time;

	public String getRightPercent() {
		if(totalCount == 0)
			return "0%";
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		String str = numberFormat.format((float)rightCount/(float)totalCount*100);;
		return str + "%";
	}

	public Double getRightPercentDouble() {
		if(totalCount == 0)
			return 0.0;
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		String str = numberFormat.format((float)rightCount/(float)totalCount*100);;
		return Double.parseDouble(str);
	}


	public String getDateString() {
		return dayDate == null ? null : sdf.format(dayDate);
	}

	public SysUserCustom getUser() {
		return user;
	}
	public void setUser(SysUserCustom user) {
		this.user = user;
	}
	public Date getDayDate() {
		return dayDate;
	}
	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
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
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public int compareTo(UserExerData o) {
		if(this.getRightPercentDouble() < o.getRightPercentDouble())
			return 1;
		else
			return -1;
	}
}
