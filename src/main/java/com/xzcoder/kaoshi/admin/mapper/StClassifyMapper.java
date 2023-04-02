package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStClassifyCustom;

/**
 * StClassifyMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StClassifyMapper {

	/**
	 * 获取所有试题分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStClassifyCustom> getAllStClassify() throws Exception;

	/**
	 * 获取所有试题分类id集合
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getAllStClassifyIds() throws Exception;

	/**
	 * 修改试题分类名称
	 * @param stClassifyCustom
	 * @throws Exception
	 */
	public void updateStClassifyNameById(ExamStClassifyCustom stClassifyCustom) throws Exception;

	/**
	 * 添加试题分类信息
	 * @param stClassifyCustom
	 * @throws Exception
	 */
	public void insertStClassify(ExamStClassifyCustom stClassifyCustom) throws Exception;

	/**
	 * 通过id删除记录
	 * @param classifyId
	 * @throws Exception
	 */
	public void deleteStClassifyById(Integer classifyId) throws Exception;

	/**
	 * 通过id集合删除记录
	 * @param classifyIds
	 * @throws Exception
	 */
	public void deleteStClassifyByIdArray(Integer[] classifyIds) throws Exception;

	/**
	 * 根据pid获取id集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildIdByPid(Integer pid) throws Exception;

	/**
	 * 根据pid获取试题分类信息集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<ExamStClassifyCustom> getStClassifysByPid(Integer pid) throws Exception;

}
