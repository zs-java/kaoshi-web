package com.xzcoder.kaoshi.po;

public class ExamSjDataWithBLOBs extends ExamSjData {
    private String gdxt;

    private String sjxt;

    private String xzst;

    public String getGdxt() {
        return gdxt;
    }

    public void setGdxt(String gdxt) {
        this.gdxt = gdxt == null ? null : gdxt.trim();
    }

    public String getSjxt() {
        return sjxt;
    }

    public void setSjxt(String sjxt) {
        this.sjxt = sjxt == null ? null : sjxt.trim();
    }

    public String getXzst() {
        return xzst;
    }

    public void setXzst(String xzst) {
        this.xzst = xzst == null ? null : xzst.trim();
    }
}
