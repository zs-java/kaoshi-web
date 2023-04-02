package com.xzcoder.kaoshi.po;

import java.text.SimpleDateFormat;

import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;

/**
 * 用户信息拓展类
 * @author 七友
 *
 */
public class SysUserCustom extends SysUser {

	//拓展信息
	//Integer类型的性别信息，1：男 2：女
	private Integer intGender;

	//角色信息
	private SysRoleCustom roleCustom;
	//分组信息
	private SysGroupCustom groupCustom;

	//用户的登陆信息
	private UserLoginInfoBean loginInfo;

	//是否可以查看私有题库
	private Integer readPrivateSt;

	public String getRegDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return getRegDate()!=null?df.format(getRegDate()):null;
	}
	public String getBirthdayString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return getBirthday()!=null?df.format(getBirthday()):null;
	}
	public String getLastLoginDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return getLastLoginDate()!=null?df.format(getLastLoginDate()):null;
	}
	public SysRoleCustom getRoleCustom() {
		return roleCustom;
	}
	public void setRoleCustom(SysRoleCustom roleCustom) {
		this.roleCustom = roleCustom;
	}
	public SysGroupCustom getGroupCustom() {
		return groupCustom;
	}
	public void setGroupCustom(SysGroupCustom groupCustom) {
		this.groupCustom = groupCustom;
	}
	public Integer getIntGender() {
		return intGender;
	}
	public void setIntGender(Integer intGender) {
		this.intGender = intGender;
	}
	public UserLoginInfoBean getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(UserLoginInfoBean loginInfo) {
		this.loginInfo = loginInfo;
	}
	public Integer getReadPrivateSt() {
		return readPrivateSt;
	}
	public void setReadPrivateSt(Integer readPrivateSt) {
		this.readPrivateSt = readPrivateSt;
	}

}
