package com.xzcoder.kaoshi.po;

public class ExamKsUserWithBLOBs extends ExamKsUser {
    private String dXtmix;

    private String daanData;

    private String rdaanData;

    private String fenScore;

    public String getdXtmix() {
        return dXtmix;
    }

    public void setdXtmix(String dXtmix) {
        this.dXtmix = dXtmix == null ? null : dXtmix.trim();
    }

    public String getDaanData() {
        return daanData;
    }

    public void setDaanData(String daanData) {
        this.daanData = daanData == null ? null : daanData.trim();
    }

    public String getRdaanData() {
        return rdaanData;
    }

    public void setRdaanData(String rdaanData) {
        this.rdaanData = rdaanData == null ? null : rdaanData.trim();
    }

    public String getFenScore() {
        return fenScore;
    }

    public void setFenScore(String fenScore) {
        this.fenScore = fenScore == null ? null : fenScore.trim();
    }
}
