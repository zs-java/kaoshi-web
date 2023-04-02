package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.UserQueryVo;

/**
 * UserService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface UserService {

	/**
	 * 通过组合添加查询用户信息
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<SysUserCustom> findUsersByUserQueryVo(UserQueryVo userQueryVo) throws Exception;

	/**
	 * 获取所有用户信息
	 * @return
	 * @throws Exception
	 */
	public List<SysUserCustom> getAllUsers() throws Exception;

	/**
	 * 新建用户
	 * @param userCustom
	 * @throws Exception
	 */
	public void insertUser(SysUserCustom userCustom) throws Exception;

	/**
	 * 批量删除用户
	 * @param ids
	 */
	public void deleteUserList(Integer[] ids) throws Exception;

	/**
	 * 通过id删除用户
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUserById(Integer userId) throws Exception;

	/**
	 * 根据id查询用户信息
	 * @param userId
	 * @return
	 */
	public SysUserCustom getUserById(Integer userId) throws Exception;

	/**
	 * 根据 id更新用户信息
	 * @param userId
	 * @param userCustom
	 */
	public void updateUserById(Integer userId, SysUserCustom userCustom) throws Exception;

	/**
	 * 根据分组id删除用户
	 * @param groupId
	 * @throws Exception
	 */
	//public void deleteUserByGroupId(Integer groupId) throws Exception;

	/**
	 * 将groupId与groupids数组中匹配的用户删除
	 * @param groupIds
	 * @throws Exception
	 */
	//public void deleteUserByGroupIdArray(Integer[] groupIds) throws Exception;

	/**
	 * 根据id集合查询用户信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<SysUserCustom> findUserListByIds(Integer[] ids, Integer[] groupIds) throws Exception;

	public List<SysUserCustom> findUserNotExamByKsId(UserQueryVo vo, PageBean pageBean) throws Exception;

}
