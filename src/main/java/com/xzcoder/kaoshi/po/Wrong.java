package com.xzcoder.kaoshi.po;

import java.util.Date;
import java.util.List;

public class Wrong {

	private Integer userId;

	private Date insDate;
	private List<Integer> stIds;


	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public List<Integer> getStIds() {
		return stIds;
	}
	public void setStIds(List<Integer> stIds) {
		this.stIds = stIds;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
