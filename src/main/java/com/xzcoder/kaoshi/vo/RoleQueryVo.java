package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.SysRoleCustom;

/**
 * RoleQueryVo
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class RoleQueryVo {

	private SysRoleCustom roleCustom;

	private PageBean pageBean;

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public SysRoleCustom getRoleCustom() {
		return roleCustom;
	}

	public void setRoleCustom(SysRoleCustom roleCustom) {
		this.roleCustom = roleCustom;
	}
}
