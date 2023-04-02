package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;

/**
 * StKnowledgeMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StKnowledgeMapper {

	/**
	 * 获取所有试题知识点信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStKnowledgeCustom> getAllStKnowledge() throws Exception;

	/**
	 * 获取所有试题知识点id集合
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getAllStKnowledgeIds() throws Exception;

	/**
	 * 重命名试题知识点分类信息
	 * @param stKnowledgeCustom
	 * @throws Exception
	 */
	public void updateStKnowledgeNameById(ExamStKnowledgeCustom stKnowledgeCustom) throws Exception;

	/**
	 * 添加试题知识点分类信息
	 * @param stKnowledgeCustom
	 * @throws Exception
	 */
	public void insertStKnowledge(ExamStKnowledgeCustom stKnowledgeCustom) throws Exception;

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void deleteStKnowledgeById(Integer id) throws Exception;

	/**
	 * 根据id集合删除记录
	 * @param idArray
	 * @throws Exception
	 */
	public void deleteStKnowledgeByIdArray(Integer[] idArray) throws Exception;

	/**
	 * 根据pid获取id集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildIdByPid(Integer pid) throws Exception;

	/**
	 * 根据pid获取试题知识点的子节点
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<ExamStKnowledgeCustom> getStKnowledgesByPid(Integer pid) throws Exception;

}
