package com.xzcoder.kaoshi.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.RoleFunctionMapper;
import com.xzcoder.kaoshi.admin.mapper.RoleMapper;
import com.xzcoder.kaoshi.admin.service.RoleService;
import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.po.SysRoleFunctionCustom;
import com.xzcoder.kaoshi.vo.RoleQueryVo;

/**
 * RoleServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;

	@Override
	public List<SysRoleCustom> getAllRoles() throws Exception {
		return roleMapper.getAllRoles();
	}

	@Override
	public List<SysRoleCustom> findRolesByRoleQueryVo(RoleQueryVo roleQueryVo) throws Exception {

		Integer totalRecored = roleMapper.findRoleCountByRoleQueryVo(roleQueryVo);
		PageBean page = roleQueryVo.getPageBean();
		page.setTotalRecored(totalRecored);
		roleQueryVo.setPageBean(page);

		return roleMapper.findRolesByRoleQueryVo(roleQueryVo);
	}

	@Override
	public void insertRole(String roleName, String des, Integer[] functionIds)
			throws Exception {
		if(roleName == null)
			throw new CustomException("添加角色时，角色名称不能为空！");

		//判断角色名称是否存在
		if(roleMapper.findRoleCountByRoleName(roleName) != 0)
			throw new CustomException("角色名称已经存在！");

		//获取排序的最大值
		Integer maxSort = roleMapper.getMAXRoleSort();

		//将角色信息封装成role
		SysRoleCustom roleCustom = new SysRoleCustom();
		roleCustom.setRoleName(roleName);
		roleCustom.setDes(des);
		roleCustom.setSort(maxSort + 1);

		//添加角色
		roleMapper.insertRole(roleCustom);

		//获取刚刚添加的角色的id
		Integer roleId = roleCustom.getRoleId();

		//为刚刚创建的角色添加功能
		for (Integer functionId : functionIds) {
			roleFunctionMapper.insertRoleFunction(new SysRoleFunctionCustom(roleId, functionId));
		}
	}

	@Override
	public void deleteRoleByRoleId(Integer roleId) throws Exception {
		//判断角色是否可以删除（非学员或超级管理员）
		if(roleId == 1 || roleId == 2)
			throw new CustomException("学员或超级管理员无法删除！");

		//删除角色对应的功能记录
		roleFunctionMapper.deleteRoleFunctionByRoleId(roleId);

		//删除角色
		roleMapper.deleteRoleByRoleId(roleId);
	}

	@Override
	public SysRoleCustom findRoleCustomByRoleId(Integer roleId)
			throws Exception {
		return roleMapper.findRoleCustomByRoleId(roleId);
	}

	@Override
	public void updateRole(Integer roleId, String roleName, String des, Integer[] functionIds) throws Exception {
		//验证roleId、roleName是否为空
		if(roleId == null)
			throw new ThrowsException("角色id不能为空！");
		if(roleName == null)
			throw new CustomException("角色名称不能为空！");

		//删除该角色与功能之间的对应关系记录
		roleFunctionMapper.deleteRoleFunctionByRoleId(roleId);
		//插入信息角色与该能之间的对应关系记录
		for (Integer functionId : functionIds) {
			roleFunctionMapper.insertRoleFunction(new SysRoleFunctionCustom(roleId, functionId));
		}

		//将角色信息封装成role对象
		SysRoleCustom roleCustom = new SysRoleCustom(roleId, roleName, des);

		//修改角色信息
		roleMapper.updateRole(roleCustom);
	}

}
