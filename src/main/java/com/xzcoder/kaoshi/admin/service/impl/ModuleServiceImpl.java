package com.xzcoder.kaoshi.admin.service.impl;

import com.xzcoder.kaoshi.admin.service.ModuleService;
import com.xzcoder.kaoshi.admin.mapper.ModuleMapper;
import com.xzcoder.kaoshi.po.SysModuleCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ModuleServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<SysModuleCustom> findAllModuleAndFunction() throws Exception {
		return moduleMapper.findAllModuleAndFunction();
	}

	@Override
	public List<SysModuleCustom> findModuleAndFunctionByUserId(Integer user_id)
			throws Exception {
		return moduleMapper.findModuleAndFunctionByUserId(user_id);
	}

}
