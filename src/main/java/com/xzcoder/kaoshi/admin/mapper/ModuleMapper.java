package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.SysModuleCustom;

/**
 * ModuleMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface ModuleMapper {

	public List<SysModuleCustom> findAllModuleAndFunction() throws Exception;

	public List<SysModuleCustom> findModuleAndFunctionByUserId(Integer user_id) throws Exception;
}
