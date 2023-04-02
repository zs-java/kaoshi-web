package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;
import com.xzcoder.kaoshi.po.ExamStLevelCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStTypeCustom;

/**
 * StMainQueryVo
 * 试题主表的组合条件查询信息包装类
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StMainQueryVo {

    //试题信息
    private ExamStMainCustom stMainCustom;

    //试题类型信息
    private ExamStTypeCustom stTypeCustom;

    //试题分类信息
    private ExamStClassifyCustom stClassifyCustom;

    //试题难度等级信息
    private ExamStLevelCustom stLevelCustom;

    //试题知识点信息
    private ExamStKnowledgeCustom stKnowledgeCustom;

    //试题id集合
    private Integer[] questionIds;

    //分页信息
    private PageBean pageBean;

    //分类id数组
    private Integer[] classifyIds;

    //难度id数组
    private Integer[] levelIds;

    //知识点id数组
    private Integer[] knowledgeIds;

    //可见性
    private Integer visable = -1;

    /*
     * getter AND setter ...
     */
    public ExamStMainCustom getStMainCustom() {
        return stMainCustom;
    }

    public void setStMainCustom(ExamStMainCustom stMainCustom) {
        this.stMainCustom = stMainCustom;
    }

    public ExamStTypeCustom getStTypeCustom() {
        return stTypeCustom;
    }

    public void setStTypeCustom(ExamStTypeCustom stTypeCustom) {
        this.stTypeCustom = stTypeCustom;
    }

    public ExamStClassifyCustom getStClassifyCustom() {
        return stClassifyCustom;
    }

    public void setStClassifyCustom(ExamStClassifyCustom stClassifyCustom) {
        this.stClassifyCustom = stClassifyCustom;
    }

    public ExamStLevelCustom getStLevelCustom() {
        return stLevelCustom;
    }

    public void setStLevelCustom(ExamStLevelCustom stLevelCustom) {
        this.stLevelCustom = stLevelCustom;
    }

    public ExamStKnowledgeCustom getStKnowledgeCustom() {
        return stKnowledgeCustom;
    }

    public void setStKnowledgeCustom(ExamStKnowledgeCustom stKnowledgeCustom) {
        this.stKnowledgeCustom = stKnowledgeCustom;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Integer[] getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(Integer[] questionIds) {
        this.questionIds = questionIds;
    }

    public Integer[] getClassifyIds() {
        return classifyIds;
    }

    public void setClassifyIds(Integer[] classifyIds) {
        this.classifyIds = classifyIds;
    }

    public Integer[] getLevelIds() {
        return levelIds;
    }

    public void setLevelIds(Integer[] levelIds) {
        this.levelIds = levelIds;
    }

    public Integer[] getKnowledgeIds() {
        return knowledgeIds;
    }

    public void setKnowledgeIds(Integer[] knowledgeIds) {
        this.knowledgeIds = knowledgeIds;
    }

    public Integer getVisable() {
        return visable;
    }

    public void setVisable(Integer visable) {
        this.visable = visable;
    }
}
