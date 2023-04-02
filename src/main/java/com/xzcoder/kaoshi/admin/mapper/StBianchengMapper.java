package com.xzcoder.kaoshi.admin.mapper;

import com.xzcoder.kaoshi.po.ExamStBianchengCustom;

/**
 * StBianchengMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StBianchengMapper {

	public void insertStBiancheng(ExamStBianchengCustom stBianchengCustom) throws Exception;

	public ExamStBianchengCustom getBianchengById(Integer questionId) throws Exception;

	public void updateStBiancheng(ExamStBianchengCustom stBianchengCustom) throws Exception;

	public void deleteStBianchengById(Integer id) throws Exception;


}
