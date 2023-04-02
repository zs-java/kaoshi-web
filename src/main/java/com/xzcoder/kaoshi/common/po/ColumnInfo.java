package com.xzcoder.kaoshi.common.po;

/**
 * ColumnInfo
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ColumnInfo {

	private String field;
	private String column;
	public ColumnInfo(String field, String column) {
		super();
		this.field = field;
		this.column = column;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
}
