package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStLevelCustom;

/**
 * StLevelService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StLevelService {

	/**
	 * 获取所有试题难度分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStLevelCustom> getAllStLevel() throws Exception;

	/**
	 * 对考试难度分类进行重命名
	 * @param levelId
	 * @param newName
	 * @throws Exception
	 */
	public void renameStLevelById(Integer levelId, String newName) throws Exception;

	/**
	 * 添加考试难度分类信息
	 * 返回自动生成的主键
	 * @param pid
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public Integer insertStLevel(Integer pid, String newName) throws Exception;

	/**
	 * 根据id删除该节点以及该节点下所有子节点信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteStLevel(Integer id) throws Exception;

	/**
	 * 获取所有试题等级树形结构
	 * @return
	 * @throws Exception
	 */
	public List<ExamStLevelCustom> getAllStLevelTree() throws Exception;

}
