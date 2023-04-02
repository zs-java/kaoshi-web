package com.xzcoder.kaoshi.vo;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsMonitor;

/**
 * KsMonitorQueryVo
 * 考试监控的组合条件查询包装类
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsMonitorQueryVo {

	//考试监控信息
	private ExamKsMonitor ksMonitor;
	//pageBean
	private PageBean pageBean;


	/*
	 * GETTER AND SETTER
	 */
	public ExamKsMonitor getKsMonitor() {
		return ksMonitor;
	}
	public void setKsMonitor(ExamKsMonitor ksMonitor) {
		this.ksMonitor = ksMonitor;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
