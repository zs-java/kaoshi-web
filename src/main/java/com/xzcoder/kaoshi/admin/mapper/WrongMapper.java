package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamWrongCustom;

/**
 * WrongMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface WrongMapper {

	/**
	 * 根据用户id获取用户错题集合
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ExamWrongCustom> getWrongListByUserId(Integer userId) throws Exception;

	/**
	 * 根据用户id修改试题集合
	 * @param wrongCustom
	 * @throws Exception
	 */
	public void updateStIdsByUserId(ExamWrongCustom wrongCustom) throws Exception;

	/**
	 * 插入用户错题记录
	 * @param wrongCustom
	 * @throws Exception
	 */
	public void insertWrong(ExamWrongCustom wrongCustom) throws Exception;

	/**
	 * 根据用户id删除错题记录
	 * @param userId
	 * @throws Exception
	 */
	public void deleteWrongByUserId(Integer userId) throws Exception;
}
