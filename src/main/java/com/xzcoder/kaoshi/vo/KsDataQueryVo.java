package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;

/**
 * KsDataQueryVo
 * 考试信息组合条件查询vo类
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsDataQueryVo {

    //考试信息
    private ExamKsDataCustom ksDataCustom;
    //考试分类信息
    private ExamKsClassifyCustom ksClassifyCustom;
    //分页信息
    private PageBean pageBean;

    public ExamKsDataCustom getKsDataCustom() {
        return ksDataCustom;
    }

    public void setKsDataCustom(ExamKsDataCustom ksDataCustom) {
        this.ksDataCustom = ksDataCustom;
    }

    public ExamKsClassifyCustom getKsClassifyCustom() {
        return ksClassifyCustom;
    }

    public void setKsClassifyCustom(ExamKsClassifyCustom ksClassifyCustom) {
        this.ksClassifyCustom = ksClassifyCustom;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}
