package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.vo.StMainQueryVo;
import com.xzcoder.kaoshi.po.ExamStMainCustom;

/**
 * StMainMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StMainMapper {

	/**
	 * 组合条件查询试题信息
	 *
	 * @param stMainQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamStMainCustom> findStListByStQueryVo(
            StMainQueryVo stMainQueryVo) throws Exception;

	/**
	 * 组合条件查询总记录数
	 * @param stMainQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getStCountByStQueryVo(StMainQueryVo stMainQueryVo)
			throws Exception;

	/**
	 * 获取当前试题表的最大排序值
	 *
	 * @return
	 * @throws Exception
	 */
	public Integer getStMainMaxSort() throws Exception;

	/**
	 * 添加试题主要信息记录
	 *
	 * @param stMainCustom
	 * @throws Exception
	 */
	public void insertStMain(ExamStMainCustom stMainCustom) throws Exception;

	/**
	 * 根据试题编号获取试题信息
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public ExamStMainCustom getStMainById(Integer questionId) throws Exception;

	/**
	 * 更新试题信息
	 * @param stMainCustom
	 * @throws Exception
	 */
	public void updateStMainById(ExamStMainCustom stMainCustom) throws Exception;

	/**
	 * 根据试题id获取试题类型的typeId
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	public Integer getStTypeIdById(Integer questionId) throws Exception;

	/**
	 * 根据id删除试题信息
	 * @param questionId
	 * @throws Exception
	 */
	public void deleteStMain(Integer questionId) throws Exception;

	/**
	 * 批量删除试题信息
	 * @param ids
	 * @throws Exception
	 */
	public void deleteStMainList(Integer[] ids) throws Exception;

	/**
	 * 组合条件查询可以使用的试题信息集合
	 * 可以使用：编号不在传入的试题编号数组中
	 * @param stMainQueryVo
	 * @return
	 */
	public List<ExamStMainCustom> findStUseableByStQueryVo(
            StMainQueryVo stMainQueryVo);

	/**
	 * 组合条件查询可以使用的试题数量
	 * 可以使用：编号不在传入的试题编号数组中
	 * @param stMainQueryVo
	 * @return
	 */
	public Integer getStUseableCountByStQueryVo(StMainQueryVo stMainQueryVo) throws Exception;

	/**
	 * 根据试题分类、知识点、难度等级查询试题id集合
	 * @param stMainQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<Integer> findStIdListByVo(StMainQueryVo stMainQueryVo) throws Exception;

	public List<ExamStMainCustom> findStListByIds(StMainQueryVo vo) throws Exception;

	/**
	 * 根据试题id获取试题可见性
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer getStVisableById(Integer id) throws Exception;

}
