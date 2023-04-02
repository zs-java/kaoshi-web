package com.xzcoder.kaoshi.po;

import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.ThrowsException;

/**
 * 用户错题包装类
 *
 * @author 七友
 */
public class ExamWrongCustom extends ExamWrong {

    private String dlData;
    private String xtData;


    /**
     * 计算试题数量
     *
     * @return
     */
    public Integer getCount() {
        try {
            String stIds = getStIds();
            if (stIds == null || "".equals(stIds) || "[]".equals(stIds)) {
                return 0;
            } else {
                return CommonUtils.JSONArrToIntegerArray(stIds).length;
            }
        } catch (ThrowsException e) {
            throw new RuntimeException();
        }
    }


    public String getDlData() {
        return dlData;
    }

    public void setDlData(String dlData) {
        this.dlData = dlData;
    }

    public String getXtData() {
        return xtData;
    }

    public void setXtData(String xtData) {
        this.xtData = xtData;
    }

}
