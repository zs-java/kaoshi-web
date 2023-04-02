package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.vo.RoleQueryVo;

/**
 * RoleService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface RoleService {

	/**
	 * 获取所有角色信息
	 * @return
	 * @throws Exception
	 */
	public List<SysRoleCustom> getAllRoles() throws Exception;

	/**
	 * 条件查询角色信息
	 * @return
	 * @throws Exception
	 */
	public List<SysRoleCustom> findRolesByRoleQueryVo(RoleQueryVo roleQueryVo) throws Exception;

	/**
	 * 添加角色并添加角色对应的功能
	 * @param roleName
	 * @param des
	 * @param functionIds
	 * @throws Exception
	 */
	public void insertRole(String roleName, String des, Integer[] functionIds) throws Exception;

	/**
	 * 根据id删除角色
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteRoleByRoleId(Integer roleId) throws Exception;

	/**
	 * 根据id查询角色信息
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public SysRoleCustom findRoleCustomByRoleId(Integer roleId) throws Exception;

	/**
	 * 修改角色信息
	 * @param roleCustom
	 * @throws Exception
	 */
	public void updateRole(Integer roleId, String roleName, String des, Integer[] functionIds) throws Exception;
}
