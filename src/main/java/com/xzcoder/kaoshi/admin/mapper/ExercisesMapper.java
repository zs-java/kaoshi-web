package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;
import java.util.Map;

import com.xzcoder.kaoshi.vo.ExercisesQueryVo;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.UserExerData;

/**
 * ExercisesMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface ExercisesMapper {

	/**
	 * 添加用户刷题信息
	 * @param exercisesCustom
	 * @throws Exception
	 */
	public void insertExercises(ExercisesCustom exercisesCustom) throws Exception;

	/**
	 * 根据id获取用户刷题记录信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExercisesCustom getExercisesById(Integer id) throws Exception;

	/**
	 * 更新用户刷题记录信息
	 * @param exercisesCustom
	 * @throws Exception
	 */
	public void updateExercises(ExercisesCustom exercisesCustom) throws Exception;

	/**
	 * 根据用户id查询该用户的刷题记录信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ExercisesCustom> getExersByUserId(Integer userId) throws Exception;

	/**
	 * 组合条件分页查询刷题信息总览
	 * @return
	 * @throws Exception
	 */
	public List<UserExerData> findUserExerDatasByVo(ExercisesQueryVo exercisesQueryVo) throws Exception;

	/**
	 * 组合条件查询刷题信息数量
	 * @param exercisesQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getUserExerDatasCountByVo(ExercisesQueryVo exercisesQueryVo) throws Exception;

	/**
	 * 根据用户id分页查询用户刷题详细信息
	 * @param exercisesQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExercisesCustom> findExercisesListByVo(ExercisesQueryVo exercisesQueryVo) throws Exception;

	/**
	 * 根据用户id查询用户刷题记录数
	 * @param exercisesQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getExercisesCountByVo(ExercisesQueryVo exercisesQueryVo) throws Exception;

	/**
	 * 根据用户id获取用户刷题总数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getExerTotalCountByUserId(Integer userId) throws Exception;

	public Integer getExersCountByUserId(Integer userId) throws Exception;

	public List<ExercisesCustom> findExersByUserIdPage(Map<String, Object> map) throws Exception;

	public void deleteExersByUserId(Integer userId) throws Exception;

	public Integer getNotExerUsersCount(ExercisesQueryVo vo) throws Exception;

	public List<UserExerData> findNotExerUsers(ExercisesQueryVo vo) throws Exception;

}
