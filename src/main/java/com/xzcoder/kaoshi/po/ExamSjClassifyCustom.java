package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 试卷分类拓展类
 * @author 七友
 *
 */
public class ExamSjClassifyCustom extends ExamSjClassify {

	private List<ExamSjClassifyCustom> children;

	private Boolean open = true;

	public Integer getId() {
		return this.getClassifyId();
	}
	public String getText() {
		return this.getName();
	}


	public List<ExamSjClassifyCustom> getChildren() {
		return children;
	}
	public void setChildren(List<ExamSjClassifyCustom> children) {
		this.children = children;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
