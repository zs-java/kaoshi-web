package com.xzcoder.kaoshi.admin.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.vo.SjDataQueryVo;

/**
 * SjDataMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SjDataMapper {

	/**
	 * 组合条件查询试卷信息集合
	 * @param sjDataQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamSjDataCustom> findSjDataListByVo(SjDataQueryVo sjDataQueryVo) throws Exception;

	/**
	 * 组合条件查询试卷总数
	 * @param sjDataQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer findSjDataCountByVo(SjDataQueryVo sjDataQueryVo) throws Exception;

	/**
	 * 添加试卷信息
	 * @param sjDataCustom
	 * @throws Exception
	 */
	public void insertSjData(ExamSjDataCustom sjDataCustom) throws Exception;

	/**
	 * 更新试卷信息
	 * @param sjDataCustom
	 * @throws Exception
	 */
	public void updateSjData(ExamSjDataCustom sjDataCustom) throws Exception;

	/**
	 * 根据试卷id获取试卷的详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExamSjDataCustom getSjDataById(Integer id) throws Exception;

	/**
	 * 根据id删除试卷信息（逻辑删除）
	 * 即修改del字段为1，并修改updUser、updDate
	 * @param id
	 * @param updUser
	 * @param updDate
	 * @throws Exception
	 */
	public void deleteSjDataById(@Param("id") Integer id,
                                 @Param("updUser") String updUser,
                                 @Param("updDate") Date updDate) throws Exception;

	/**
	 * 根据id集合逻辑删除试卷信息
	 * @param ids
	 * @param updUser
	 * @param updDate
	 * @throws Exception
	 */
	public void deleteSjDataList(@Param("ids") Integer[] ids,
                                 @Param("updUser") String updUser,
                                 @Param("updDate") Date updDate) throws Exception;

	/**
	 * 查询试卷可见等级
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer getSjVisableById(Integer id) throws Exception;

	/**
	 * 判断学生用户是否存在该试卷的考试
	 * 1：存在 0：不存在
	 * @param sjId
	 * @param userId
	 * @return
	 */
	public Integer getSjCountBySjIdAndUid(@Param("sjId") Integer sjId, @Param("userId") Integer userId);

}
