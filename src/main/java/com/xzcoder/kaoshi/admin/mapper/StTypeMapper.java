package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStTypeCustom;

/**
 * StTypeMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StTypeMapper {

	/**
	 * 获取所有试题类型信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStTypeCustom> getAllStTypes() throws Exception;

}
