package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStLevelCustom;

/**
 * StLevelMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StLevelMapper {

	/**
	 * 获取所有试题难度分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStLevelCustom> getAllStLevel() throws Exception;

	/**
	 * 获取所有试题难度id集合
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getAllStLevelIds() throws Exception;

	/**
	 * 修改试题难度分类名称
	 * @param stClassifyCustom
	 * @throws Exception
	 */
	public void updateStLevelNameById(ExamStLevelCustom stLevelCustom) throws Exception;

	/**
	 * 添加试题难度分类信息
	 * @param stLevelCustom
	 * @throws Exception
	 */
	public void insertStLevel(ExamStLevelCustom stLevelCustom) throws Exception;

	/**
	 * 通过id删除试题难度分类信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteStLevelById(Integer id) throws Exception;

	/**
	 * 通过id集合删除试题难度分类信息
	 * @param idArray
	 * @throws Exception
	 */
	public void deleteStLevelByIdArray(Integer[] idArray) throws Exception;

	/**
	 * 根据节点id获取节点下一层节点的id集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildIdByPid(Integer id) throws Exception;

	/**
	 * 根据pid获取试题难度的子节点
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<ExamStLevelCustom> getStLevelByPid(Integer pid) throws Exception;
}
