package com.xzcoder.kaoshi.po;

import java.util.List;

/**
 * 试题知识点分类拓展类
 * @author 七友
 *
 */
public class ExamStKnowledgeCustom extends ExamStKnowledge {

	private Boolean open = true;

	private List<ExamStKnowledgeCustom> children;


	public Integer getId() {
		return this.getClassifyId();
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
	public List<ExamStKnowledgeCustom> getChildren() {
		return children;
	}
	public void setChildren(List<ExamStKnowledgeCustom> children) {
		this.children = children;
	}
}
