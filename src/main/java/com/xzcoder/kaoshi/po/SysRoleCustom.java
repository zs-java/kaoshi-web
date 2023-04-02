package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 角色信息拓展类
 * @author 七友
 *
 */
public class SysRoleCustom extends SysRole {

	/**
	 * 封装该角色对应权限的所有功能
	 */
	private List<SysFunctionCustom> functions;

	public SysRoleCustom() {
		super();
	}

	public SysRoleCustom(Integer roleId, String roleName, String des) {
		this.setRoleId(roleId);
		this.setRoleName(roleName);
		this.setDes(des);
	}

//	/**
//	 * 该角色对应的所有功能的id的集合
//	 * @return
//	 */
//	private Integer[] functionIds;

	public List<SysFunctionCustom> getFunctions() {
		return functions;
	}

	public void setFunctions(List<SysFunctionCustom> functions) {
		this.functions = functions;
	}
}
