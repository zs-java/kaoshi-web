package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * SysModule的扩展类
 * @author 七友
 *
 */
public class SysModuleCustom extends SysModule {

	//扩展属性

	//每个模块都有多个功能
	private List<SysFunction> functions;


	public List<SysFunction> getFunctions() {
		return functions;
	}
	public void setFunctions(List<SysFunction> functions) {
		this.functions = functions;
	}
}
