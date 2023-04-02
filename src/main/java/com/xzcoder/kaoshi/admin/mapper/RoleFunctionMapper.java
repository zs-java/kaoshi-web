package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.SysRoleFunctionCustom;

/**
 * RoleFunctionMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface RoleFunctionMapper {

	/**
	 * 查询角色与功能的多对多关系记录
	 * @param roleFunctionCustom
	 * @throws Exception
	 */
	public void insertRoleFunction(SysRoleFunctionCustom roleFunctionCustom) throws Exception;

	/**
	 * 通过角色id删除记录
	 * @throws Exception
	 */
	public void deleteRoleFunctionByRoleId(Integer roleId) throws Exception;

}
