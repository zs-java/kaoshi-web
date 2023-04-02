package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.SysModuleCustom;

/**
 * ModuleService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface ModuleService {

	/**
	 * 获取所有模块与功能
	 * @return
	 * @throws Exception
	 */
	public List<SysModuleCustom> findAllModuleAndFunction() throws Exception;

	/**
	 * 通过 user_id获取该用户角色对应的功能
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public List<SysModuleCustom> findModuleAndFunctionByUserId(Integer user_id) throws Exception;
}
