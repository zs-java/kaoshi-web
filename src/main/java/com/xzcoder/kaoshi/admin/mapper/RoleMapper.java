package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.vo.RoleQueryVo;

/**
 * RoleMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface RoleMapper {

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
	 * 条件查询role总数
	 * @param roleQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer findRoleCountByRoleQueryVo(RoleQueryVo roleQueryVo) throws Exception;

	/**
	 * 获取角色排序当前最大值
	 * @return
	 * @throws Exception
	 */
	public Integer getMAXRoleSort() throws Exception;

	/**
	 * 添加角色
	 * @param roleCustom
	 * @throws Exception
	 */
	public void insertRole(SysRoleCustom roleCustom) throws Exception;

	/**
	 * 根据id删除角色
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteRoleByRoleId(Integer roleId) throws Exception;

	/**
	 * 根据id查询role信息
	 * @param roleId
	 * @throws Exception
	 */
	public SysRoleCustom findRoleCustomByRoleId(Integer roleId) throws Exception;


	/**
	 * 根据角色名称查询角色总数
	 * 用于添加角色前判断角色名称是否存在
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	public Integer findRoleCountByRoleName(String roleName) throws Exception;

	/**
	 * 修改角色信息
	 * @param roleCustom
	 * @throws Exception
	 */
	public void updateRole(SysRoleCustom roleCustom) throws Exception;

	/**
	 * 根据用户id查询该用户是否有查看私有题库的权限
	 * 1：有权限 0：无权限
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getReadPrivateStRoleByUserId(Integer userId) throws Exception;

}
