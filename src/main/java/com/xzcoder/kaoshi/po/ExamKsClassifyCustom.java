package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 考试分类信息扩展类
 * @author 七友
 *
 */
public class ExamKsClassifyCustom extends ExamKsClassify {

	private List<ExamKsClassifyCustom> children;

	private Boolean open = true;

	public Integer getId() {
		return this.getClassifyId();
	}
	public String getText() {
		return this.getName();
	}


	public List<ExamKsClassifyCustom> getChildren() {
		return children;
	}
	public void setChildren(List<ExamKsClassifyCustom> children) {
		this.children = children;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
