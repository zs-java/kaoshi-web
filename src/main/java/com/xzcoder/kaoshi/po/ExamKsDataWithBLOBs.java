package com.xzcoder.kaoshi.po;

public class ExamKsDataWithBLOBs extends ExamKsData {
    private String des;

    private String allowIp;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getAllowIp() {
        return allowIp;
    }

    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp == null ? null : allowIp.trim();
    }
}
