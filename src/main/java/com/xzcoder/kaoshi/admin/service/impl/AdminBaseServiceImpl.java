package com.xzcoder.kaoshi.admin.service.impl;

import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.UserMapper;
import com.xzcoder.kaoshi.admin.service.AdminBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.RoleMapper;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.SysUser;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * AdminBaseServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class AdminBaseServiceImpl implements AdminBaseService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public SysUserCustom login(String username, String password) throws Exception {
		//将用户名、密码信息封装成user对象
		SysUserCustom formUser = new SysUserCustom();
		formUser.setUsername(username);
		formUser.setPassword(password);
		//查询用户集合
		List<SysUser> userList = userMapper.findUserListByUsernameAndPassword(formUser);
		if(userList.size()==1) {
			//登陆成功
			SysUserCustom user = new SysUserCustom();
			BeanUtils.copyProperties(userList.get(0), user);

			//登陆成功后登陆次数增加1
			userMapper.updateLoginCountByUserId(user.getId());
			user.setLoginCount(user.getLoginCount()+1);

			//查询用户是否存在私有题库的权限，设置到用户对象中
			user.setReadPrivateSt(roleMapper.getReadPrivateStRoleByUserId(user.getId()));

			//省略封装过程

			return user;
		} else if(userList.size() == 0) {
			return null;	//该用户名、密码不存在匹配用户
		} else {
			throw new ThrowsException("数据库异常，用户名重复！");
		}
	}

}
