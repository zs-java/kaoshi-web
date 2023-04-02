package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;

/**
 * SjDataQueryVo
 * 试卷组合条件查询包装类
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class SjDataQueryVo {

    //试卷信息
    private ExamSjDataCustom sjDataCustom;
    //试卷分类信息
    private ExamSjClassifyCustom sjClassifyCustom;
    //审核状态0：未审核 1：已审核 -1：不检查此列 //默认值为-1
    private Integer reviewStauts = -1;

    //可见性
    private Integer visable = -1;

    //分页信息
    private PageBean pageBean;

    public ExamSjClassifyCustom getSjClassifyCustom() {
        return sjClassifyCustom;
    }

    public void setSjClassifyCustom(ExamSjClassifyCustom sjClassifyCustom) {
        this.sjClassifyCustom = sjClassifyCustom;
    }

    public ExamSjDataCustom getSjDataCustom() {
        return sjDataCustom;
    }

    public void setSjDataCustom(ExamSjDataCustom sjDataCustom) {
        this.sjDataCustom = sjDataCustom;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Integer getReviewStauts() {
        return reviewStauts;
    }

    public void setReviewStauts(Integer reviewStauts) {
        this.reviewStauts = reviewStauts;
    }

    public Integer getVisable() {
        return visable;
    }

    public void setVisable(Integer visable) {
        this.visable = visable;
    }
}
