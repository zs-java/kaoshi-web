package com.xzcoder.kaoshi.po;

import java.text.SimpleDateFormat;

/**
 * 试题信息包装类
 * @author 七友
 *
 */
public class ExamStMainCustom extends ExamStMain {

	//试题类型信息
	private ExamStTypeCustom stTypeCustom;
	//试题分类信息
	private ExamStClassifyCustom stClassifyCustom;
	//试题难度信息
	private ExamStLevelCustom stLevelCustom;
	//试题知识点信息
	private ExamStKnowledgeCustom stKnowledgeCustom;
	//插入的用户信息
	private SysUserCustom userCustom;

	/*
	 * getter AND setter ......
	 */
	public String getInsDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return this.getInsDate()!=null?df.format(this.getInsDate()):null;
	}
	public String getUpdDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return this.getUpdDate()!=null?df.format(this.getUpdDate()):null;
	}


	public ExamStTypeCustom getStTypeCustom() {
		return stTypeCustom;
	}
	public void setStTypeCustom(ExamStTypeCustom stTypeCustom) {
		this.stTypeCustom = stTypeCustom;
	}
	public ExamStClassifyCustom getStClassifyCustom() {
		return stClassifyCustom;
	}
	public void setStClassifyCustom(ExamStClassifyCustom stClassifyCustom) {
		this.stClassifyCustom = stClassifyCustom;
	}
	public ExamStLevelCustom getStLevelCustom() {
		return stLevelCustom;
	}
	public void setStLevelCustom(ExamStLevelCustom stLevelCustom) {
		this.stLevelCustom = stLevelCustom;
	}
	public ExamStKnowledgeCustom getStKnowledgeCustom() {
		return stKnowledgeCustom;
	}
	public void setStKnowledgeCustom(ExamStKnowledgeCustom stKnowledgeCustom) {
		this.stKnowledgeCustom = stKnowledgeCustom;
	}
	public SysUserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(SysUserCustom userCustom) {
		this.userCustom = userCustom;
	}
}
