package com.xzcoder.kaoshi.po;

import java.text.SimpleDateFormat;

/**
 * @author 七友
 *
 */
public class ExamSjDataCustom extends ExamSjDataWithBLOBs {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ExamSjClassifyCustom sjClassifyCustom;

	public String getInsDateString() {
		return this.getInsDate() == null ? null : df.format(this.getInsDate());
	}

	public String getUpdDateString() {
		return this.getUpdDate() == null ? null : df.format(this.getUpdDate());
	}

	public ExamSjClassifyCustom getSjClassifyCustom() {
		return sjClassifyCustom;
	}

	public void setSjClassifyCustom(ExamSjClassifyCustom sjClassifyCustom) {
		this.sjClassifyCustom = sjClassifyCustom;
	}
}
