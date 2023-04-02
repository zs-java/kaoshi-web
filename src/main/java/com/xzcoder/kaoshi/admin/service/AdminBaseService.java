package com.xzcoder.kaoshi.admin.service;

import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * AdminBaseService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface AdminBaseService {

	/**
	 * 通过用户名密码判断管理员登录是否成功
	 * 验证成功返回查找到的user对象
	 * 验证失败返回null
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public SysUserCustom login(String username, String password) throws Exception;

}
