package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.ExamStPanduanCustom;

/**
 * StPanduanMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StPanduanMapper {

	/**
	 * 添加判断题
	 * @param stPanduanCustom
	 * @throws Exception
	 */
	public void insertStPanduan(ExamStPanduanCustom stPanduanCustom) throws Exception;

	/**
	 * 根据id获取判断题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStPanduanCustom getStPanduanById(Integer questionId) throws Exception;

	/**
	 * 更新单选题信息
	 * @param stPanduanCustom
	 * @throws Exception
	 */
	public void updateStPanduan(ExamStPanduanCustom stPanduanCustom) throws Exception;

	/**
	 * 删除判断题
	 * @param questionId
	 * @throws Exception
	 */
	public void deleteStPanduan(Integer questionId) throws Exception;
}
