package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamStClassifyCustom;

/**
 * StClassifyService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StClassifyService {

	/**
	 * 获取所有试题分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStClassifyCustom> getAllStClassify() throws Exception;

	/**
	 * 重命名试题分类名称
	 * @param classifyId
	 * @param newName
	 * @throws Exception
	 */
	public void renameStClassifyById(Integer classifyId, String newName) throws Exception;

	/**
	 * 添加试题分类信息，返回生成的主键
	 * @param pid
	 * @param newName
	 * @throws Exception
	 */
	public Integer insertStClassify(Integer pid, String newName) throws Exception;

	/**
	 * 根据节点id删除节点，并删除该节点的所有子节点
	 * @param id
	 * @throws Exception
	 */
	public void deleteStClassifyById(Integer id) throws Exception;

	/**
	 * 获取试题分类树形信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamStClassifyCustom> getAllStClassifyTree() throws Exception;
}
