package com.xzcoder.kaoshi.common.po;

import net.sf.json.JSONObject;

/**
 * ExerInfo
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ExerInfo {

	private Integer stId;//当前刷题题目id
	private Integer exerId;//刷题编号
	private JSONObject qsnData;//试题的json信息
	private String rightKey;//试题正确答案的json信息
	private Boolean isSubmit = false;//该试题是否提交，默认未提交
	private Integer[] luanxuArr;//乱序后的选项编号数组
	private Integer type;//试题的类型



	public Integer getStId() {
		return stId;
	}
	public void setStId(Integer stId) {
		this.stId = stId;
	}
	public Integer getExerId() {
		return exerId;
	}
	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}
	public JSONObject getQsnData() {
		return qsnData;
	}
	public void setQsnData(JSONObject qsnData) {
		this.qsnData = qsnData;
	}
	public String getRightKey() {
		return rightKey;
	}
	public void setRightKey(String rightKey) {
		this.rightKey = rightKey;
	}
	public Boolean getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}
	public Integer[] getLuanxuArr() {
		return luanxuArr;
	}
	public void setLuanxuArr(Integer[] luanxuArr) {
		this.luanxuArr = luanxuArr;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}
