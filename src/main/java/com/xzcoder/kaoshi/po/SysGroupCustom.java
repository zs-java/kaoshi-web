package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 用户分组拓展类
 * @author 七友
 *
 */
public class SysGroupCustom extends SysGroup {

	//拓展字段

	//分组的子分组
	private List<SysGroupCustom> childGroups;

	@Override
	public String toString() {
		return "SysGroupCustom [childGroups=" + childGroups + ", getGroupId()="
				+ getGroupId() + ", getGroupPid()=" + getGroupPid()
				+ ", getGroupName()=" + getGroupName() + ", getSort()="
				+ getSort() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public List<SysGroupCustom> getChildGroups() {
		return childGroups;
	}

	public void setChildGroups(List<SysGroupCustom> childGroups) {
		this.childGroups = childGroups;
	}



}
