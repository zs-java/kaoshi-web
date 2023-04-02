package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;

/**
 * SjClassifyMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SjClassifyMapper {

	/**
	 * 获取所有试卷分类信息
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjClassifyCustom> getAllSjClassify() throws Exception;

	/**
	 * 重命名试卷分类信息
	 * @param sjClassifyCustom
	 * @throws Exception
	 */
	public void updateSjClassifyNameById(ExamSjClassifyCustom sjClassifyCustom) throws Exception;

	/**
	 * 添加试卷分类信息
	 * @param sjClassifyCustom
	 * @throws Exception
	 */
	public void insertSjClassify(ExamSjClassifyCustom sjClassifyCustom) throws Exception;

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public void deleteSjClassifyById(Integer id) throws Exception;

	/**
	 * 根据id集合删除记录
	 * @param ids
	 * @throws Exception
	 */
	public void deleteSjClassifyByIdArray(Integer[] ids) throws Exception;

	/**
	 * 根据pid获取id集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildIdByPid(Integer pid) throws Exception;

	/**
	 * 根据pid获取试卷分类信息集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjClassifyCustom> getSjClassifyByPid(Integer pid) throws Exception;
}
