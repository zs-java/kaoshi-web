package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 试题等级分类拓展类
 * @author 七友
 *
 */
public class ExamStLevelCustom extends ExamStLevel {

	private List<ExamStLevelCustom> children;

	private Boolean open = true;


	public Integer getId() {
		return this.getLevelId();
	}
	public String getText() {
		return this.getName();
	}


	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public List<ExamStLevelCustom> getChildren() {
		return children;
	}
	public void setChildren(List<ExamStLevelCustom> children) {
		this.children = children;
	}
}
