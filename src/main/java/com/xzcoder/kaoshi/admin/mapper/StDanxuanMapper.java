package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;

/**
 * StDanxuanMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StDanxuanMapper {

	/**
	 * 添加单选题信息
	 * @param stDanxuanCustom
	 * @throws Exception
	 */
	public void insertStDanxuan(ExamStDanxuanCustom stDanxuanCustom) throws Exception;

	/**
	 * 根据id查询单选题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStDanxuanCustom getStDanxuanById(Integer questionId) throws Exception;

	/**
	 * 更新单选题信息
	 * @param stDanxuanCustom
	 * @throws Exception
	 */
	public void updateStDanxuan(ExamStDanxuanCustom stDanxuanCustom) throws Exception;

	/**
	 * 删除试题
	 * @param questionId
	 * @throws Exception
	 */
	public void deleteStDanxuan(Integer questionId) throws Exception;

}
