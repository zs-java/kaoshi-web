package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.SysUser;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.UserQueryVo;

/**
 * UserMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface UserMapper {

	/**
	 * 判断用户名密码是否正确
	 * 正确返回对应用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> findUserListByUsernameAndPassword(SysUser user) throws Exception;

	/**
	 * 用户登录成功后，将登陆次数增加1
	 * @param userId
	 * @throws Exception
	 */
	public void updateLoginCountByUserId(Integer userId) throws Exception;

	/**
	 * 获取所有用户的拓展信息
	 * @return
	 * @throws Exception
	 */
	public List<SysUserCustom> getAllUsers() throws Exception;

	/**
	 * 组合条件查询用户信息
	 * @param userQueryVo
	 * @return
	 */
	public List<SysUserCustom> findUsersByUserQueryVo(UserQueryVo userQueryVo) throws Exception;

	/**
	 * 组合条件查询用户数量
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getUsersCountByUserQueryVo(UserQueryVo userQueryVo) throws Exception;

	/**
	 * 添加用户
	 * @param userCustom
	 * @throws Exception
	 */
	public void insertUser(SysUserCustom userCustom) throws Exception;

	/**
	 * 批量删除用户
	 * @param ids
	 * @throws Exception
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
	 * @throws Exception
	 */
	public SysUserCustom getUserById(Integer userId) throws Exception;

	/**
	 * 根据id更新用户信息
	 * @param userCustom
	 * @throws Exception
	 */
	public void updateUserById(SysUserCustom userCustom) throws Exception;

	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Integer getUserCountByUsername(String username) throws Exception;

	/**
	 * 根据分组id删除用户
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteUserByGroupId(Integer groupId) throws Exception;

	/**
	 * 将groupId与groupids数组中匹配的用户删除
	 * @param groupIds
	 * @throws Exception
	 */
	public void deleteUserByGroupIdArray(Integer[] groupIds) throws Exception;

	/**
	 * 将用户的groupId与groupids数组相匹配的用户移动到指定的分组
	 * @param groupIds
	 * @throws Exception
	 */
	public void updateUserGroupIdByGroupIdArray(
            @Param("setGroupId") Integer setGroupId,
            @Param("groupIds") Integer[] groupIds) throws Exception;

	public void updateUserGroupIdByGroupId(
            @Param("setGroupId") Integer setGroupId,
            @Param("groupId") Integer groupId) throws Exception;

	/**
	 * 根据id集合查询用户信息
	 * @param ids
	 * @throws Exception
	 */
	public List<SysUserCustom> findUserListByIds(@Param("ids") Integer[] ids, @Param("groupIds") Integer[] groupIds) throws Exception;

	/**
	 * 根据考试id查询无需考试的用户 信息集合
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SysUserCustom> findUserNotInExamByVo(UserQueryVo vo) throws Exception;

	/**
	 * 根据考试id查询不用参加该考试的数量
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Integer getUserNotInExamByVo(UserQueryVo vo) throws Exception;
}
