package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;

/**
 * StKnowledgeService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StKnowledgeService {

	/**
	 * 获取所有试题知识点信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStKnowledgeCustom> getAllStKnowledge() throws Exception;

	/**
	 * 重命名试题知识点分组
	 * @param id
	 * @param newName
	 * @throws Exception
	 */
	public void renameStKnowledge(Integer id, String newName) throws Exception;

	/**
	 * 添加试题知识点分组节点
	 * 返回自增主键
	 * @param pid
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public Integer insertStKnowledge(Integer pid, String newName) throws Exception;

	/**
	 * 删除试题知识点分组节点
	 * @param id
	 * @throws Exception
	 */
	public void deleteStKnowledgeById(Integer id) throws Exception;

	/**
	 * 获取所有试题知识点树形结构
	 * @return
	 * @throws Exception
	 */
	public List<ExamStKnowledgeCustom> getAllStKnowledgeTree() throws Exception;

}
