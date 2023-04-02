package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;

/**
 * StDuoxuanMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StDuoxuanMapper {

	/**
	 * 添加多选题
	 * @param stDuoxuanCustom
	 * @throws Exception
	 */
	public void insertStDuoxuan(ExamStDuoxuanCustom stDuoxuanCustom) throws Exception;

	/**
	 * 根据id查询多选题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStDuoxuanCustom getStDuoxuanById(Integer questionId) throws Exception;

	/**
	 * 更新多选题信息
	 * @param stDuoxuanCustom
	 * @throws Exception
	 */
	public void updateStDuoxuan(ExamStDuoxuanCustom stDuoxuanCustom) throws Exception;

	public void deleteStDuoxuan(Integer questionId) throws Exception;

}
