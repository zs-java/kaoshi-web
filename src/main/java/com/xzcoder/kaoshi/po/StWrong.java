package com.xzcoder.kaoshi.po;

public class StWrong {

	private Integer userId;

	private String dlData;
	private String xtData;

	private Integer pc;
	private Integer ps;
	private Integer tr;

	public Integer getTp() {
		// 通过总记录数和每页记录数来计算总页数
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}
	public Integer getStartIndex() {
		return (pc - 1) * ps;
	}



	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDlData() {
		return dlData;
	}
	public void setDlData(String dlData) {
		this.dlData = dlData;
	}
	public String getXtData() {
		return xtData;
	}
	public void setXtData(String xtData) {
		this.xtData = xtData;
	}
	public Integer getPc() {
		return pc;
	}
	public void setPc(Integer pc) {
		this.pc = pc;
	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	public Integer getTr() {
		return tr;
	}
	public void setTr(Integer tr) {
		this.tr = tr;
	}
}
