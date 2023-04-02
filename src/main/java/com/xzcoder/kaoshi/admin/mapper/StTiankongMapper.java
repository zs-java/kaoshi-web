package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.ExamStTiankongCustom;

/**
 * StTiankongMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StTiankongMapper {

	/**
	 * 添加填空题
	 * @param stTiankongCustom
	 * @throws Exception
	 */
	public void insertStTiankong(ExamStTiankongCustom stTiankongCustom) throws Exception;

	/**
	 * 根据id查询填空题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStTiankongCustom getStTiankongById(Integer questionId) throws Exception;

	/**
	 * 更新填空题信息
	 * @param stTiankongCustom
	 * @throws Exception
	 */
	public void updateStTiankong(ExamStTiankongCustom stTiankongCustom) throws Exception;

	/**
	 * 根据id删除填空题
	 * @param questionId
	 * @throws Exception
	 */
	public void deleteStTiankong(Integer questionId) throws Exception;

}
