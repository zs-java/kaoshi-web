package com.xzcoder.kaoshi.po;

public class ExamStTiankongWithBLOBs extends ExamStTiankong {
    private String title;

    private String rightKey;

    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRightKey() {
        return rightKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey == null ? null : rightKey.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}
