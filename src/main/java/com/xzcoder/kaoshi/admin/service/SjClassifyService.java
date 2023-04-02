package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;

/**
 * SjClassifyService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SjClassifyService {

	/**
	 * 获取所有试卷分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjClassifyCustom> getAllSjClassify() throws Exception;

	/**
	 * 重命名试卷分类信息
	 * @param id
	 * @param newName
	 * @throws Exception
	 */
	public void renameSjClassifyById(Integer id, String newName) throws Exception;

	/**
	 * 添加试卷分类信息
	 * 返回自增主键
	 * @param pid
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public Integer insertSjClassify(Integer pid, String newName) throws Exception;

	/**
	 * 根据id删除该节点以及该节点下的所有子节点
	 * @param id
	 * @throws Exception
	 */
	public void deleteSjClassifyById(Integer id) throws Exception;

	/**
	 * 获取所有试卷分类的树形信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjClassifyCustom> getAllSjClassifyTree() throws Exception;

}
