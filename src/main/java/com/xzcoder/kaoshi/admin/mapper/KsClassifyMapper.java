package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;

/**
 * KsClassifyMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsClassifyMapper {

	/**
	 * 获取所有考试分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsClassifyCustom> getAllKsClassify() throws Exception;

	/**
	 * 重命名考试分类信息
	 * @param ksClassifyCustom
	 * @throws Exception
	 */
	public void updateKsClassifyNameById(ExamKsClassifyCustom ksClassifyCustom) throws Exception;

	/**
	 * 添加考试分类信息
	 * @param ksClassifyCustom
	 * @throws Exception
	 */
	public void insertKsClassify(ExamKsClassifyCustom ksClassifyCustom) throws Exception;

	/**
	 * 根据id删除考试分类信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteKsClassifyById(Integer id) throws Exception;

	/**
	 * 根据id集合批量删除考试分类信息
	 * @param idArray
	 * @throws Exception
	 */
	public void deleteKsClassifyByIdArray(Integer[] idArray) throws Exception;

	/**
	 * 根据pid获取id集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildIdByPid(Integer pid) throws Exception;

	/**
	 * 根据pid获取考试分类信息
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsClassifyCustom> getKsClassifyByPid(Integer pid) throws Exception;

}
