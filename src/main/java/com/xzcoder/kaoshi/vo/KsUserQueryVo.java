package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * KsUserQueryVo
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsUserQueryVo {

	private ExamKsUserCustom ksUserCustom;
	private SysUserCustom userCustom;
	private ExamKsDataCustom ksDataCustom;

	private String username;
	private Integer groupId = -1;
	private Integer state = -1;
	private Integer ksId;

	private Integer[] groupIds;

	private PageBean pageBean;


	public ExamKsUserCustom getKsUserCustom() {
		return ksUserCustom;
	}
	public void setKsUserCustom(ExamKsUserCustom ksUserCustom) {
		this.ksUserCustom = ksUserCustom;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public SysUserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(SysUserCustom userCustom) {
		this.userCustom = userCustom;
	}
	public ExamKsDataCustom getKsDataCustom() {
		return ksDataCustom;
	}
	public void setKsDataCustom(ExamKsDataCustom ksDataCustom) {
		this.ksDataCustom = ksDataCustom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getKsId() {
		return ksId;
	}
	public void setKsId(Integer ksId) {
		this.ksId = ksId;
	}
	public Integer[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(Integer[] groupIds) {
		this.groupIds = groupIds;
	}
}
