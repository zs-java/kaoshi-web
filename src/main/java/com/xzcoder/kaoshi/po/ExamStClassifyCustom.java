package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 试题分类拓展类
 * @author 七友
 *
 */
public class ExamStClassifyCustom extends ExamStClassify {


	private List<ExamStClassifyCustom> childStClassifys;

	//拓展属性

	//ztree是否展开，默认为true(展开)
	private Boolean open = true;

	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public List<ExamStClassifyCustom> getChildStClassifys() {
		return childStClassifys;
	}
	public void setChildStClassifys(List<ExamStClassifyCustom> childStClassifys) {
		this.childStClassifys = childStClassifys;
	}
}
