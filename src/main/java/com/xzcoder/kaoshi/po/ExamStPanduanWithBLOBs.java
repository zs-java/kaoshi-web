package com.xzcoder.kaoshi.po;

public class ExamStPanduanWithBLOBs extends ExamStPanduan {
    private String title;

    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}
