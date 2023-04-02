package com.xzcoder.kaoshi.po;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class ExercisesCustom extends Exercises implements Comparable<ExercisesCustom> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String classifyName;
	private String knowledgeName;

	public String getDayDateString() {
		return getDayDate() == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(getDayDate());
	}

	public Integer getTime() {
		if(getUpdDate() == null) {
			return 0;
		}
		long diff = getUpdDate().getTime() - getInsDate().getTime();
		int time = (int)(diff / (1000 * 60));
		return time == 0 ? time + 1 : time;
	}

	public String getRightPercent() {
		if(getTotalCount() == 0)
			return "0%";

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		String str = numberFormat.format((float)getRightCount()/(float)getTotalCount()*100);;
		return str + "%";
	}

	public Double getRightPercentInt() {
		if(getTotalCount() == 0)
			return 0.0;

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);

		String str = numberFormat.format((float)getRightCount()/(float)getTotalCount()*100);;
		return Double.parseDouble(str);
	}


	public String getBeginTimeString() {
		return sdf.format(this.getInsDate());
	}


	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	@Override
	public int compareTo(ExercisesCustom o) {
		if(this.getRightPercentInt() > o.getRightPercentInt())
			return 1;
		else
			return -1;
	}

}
