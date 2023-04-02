package com.xzcoder.kaoshi.common.po;

import java.util.ArrayList;
import java.util.List;

import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * ExamStResult
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ExamStResult {

	//题目id
	private Integer qsnId;
	private ExamStMainCustom stInfo;
	private List<SysUserCustom> rightUsers = new ArrayList<>();
	private List<SysUserCustom> errorUsers = new ArrayList<>();

	public double getRate() {
		double sum = getRightCount() + getErrorCount();
		double rate = getRightCount() / sum * 100;
		return Double.parseDouble(String.format("%.2f", rate));
	}

	public ExamStResult() {
	}
	public ExamStResult(Integer qsnId, ExamStMainCustom stInfo) {
		super();
		this.qsnId = qsnId;
		this.stInfo = stInfo;
	}

	public void addRight(SysUserCustom user) {
		rightUsers.add(user);
	}
	public void addError(SysUserCustom user) {
		errorUsers.add(user);
	}
	public Integer getQsnId() {
		return qsnId;
	}
	public void setQsnId(Integer qsnId) {
		this.qsnId = qsnId;
	}
	public Integer getRightCount() {
		return rightUsers.size();
	}
	public Integer getErrorCount() {
		return errorUsers.size();
	}
	public ExamStMainCustom getStInfo() {
		return stInfo;
	}
	public void setStInfo(ExamStMainCustom stInfo) {
		this.stInfo = stInfo;
	}
	public List<SysUserCustom> getRightUsers() {
		return rightUsers;
	}
	public void setRightUsers(List<SysUserCustom> rightUsers) {
		this.rightUsers = rightUsers;
	}
	public List<SysUserCustom> getErrorUsers() {
		return errorUsers;
	}
	public void setErrorUsers(List<SysUserCustom> errorUsers) {
		this.errorUsers = errorUsers;
	}
}
