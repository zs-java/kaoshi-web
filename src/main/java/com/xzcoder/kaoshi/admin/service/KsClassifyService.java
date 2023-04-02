package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;

/**
 * KsClassifyService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsClassifyService {

	/**
	 * 获取所有考试分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsClassifyCustom> getAllKsClassify() throws Exception;

	/**
	 * 重命名考试分组信息
	 * @param id
	 * @throws Exception
	 */
	public void renameKsClassifyNameById(Integer id, String newName) throws Exception;

	/**
	 * 添加考试分类信息
	 * 返回自增主键
	 * @param pid
	 * @param newName
	 * @return
	 * @throws Exception
	 */
	public Integer insertKsClassify(Integer pid, String newName) throws Exception;

	/**
	 * 根据id删除该考试分类以及该考试分类下的所有子分类
	 * @param id
	 * @throws Exception
	 */
	public void deleteKsClassify(Integer id) throws Exception;

	/**
	 * 获取所有考试分类的树形信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsClassifyCustom> getAllKsClassifyTree() throws Exception;

}
