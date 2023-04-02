package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.po.UserExerData;
import com.xzcoder.kaoshi.vo.ExercisesQueryVo;

/**
 * ExercisesService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface ExercisesService {

	/**
	 * 添加用户刷题记录信息
	 * @param classifyIds
	 * @param knowledgeIds
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int insertExercises(Integer[] classifyIds, Integer[] knowledgeIds, SysUserCustom user) throws Exception;

	/**
	 * 根据刷题编号获取下一题的json数据
	 * @param exerId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getNextQuestion(Integer exerId) throws Exception;

	/**
	 * 更新用户刷题记录信息
	 * @param exercisesCustom
	 * @throws Exception
	 */
	public void updateExercises(Integer exerId, Integer questionId, Boolean flag, SysUserCustom user) throws Exception;

	/**
	 * 根据id获取用户刷题信息
	 * @param exerId
	 * @return
	 * @throws Exception
	 */
	public ExercisesCustom getExercisesById(Integer exerId) throws Exception;

	/**
	 * 获取该用户的用户刷题记录
	 * @param userCustom
	 * @return
	 * @throws Exception
	 */
	public List<ExercisesCustom> getExersByUser(SysUserCustom userCustom) throws Exception;

	public List<ExercisesCustom> findExersByUserPage(SysUserCustom user, PageBean pb) throws Exception;

	/**
	 * 分页、组合条件查询刷题信息
	 * @param vo
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<UserExerData> findUserExerDataByVo(ExercisesQueryVo vo, PageBean pageBean) throws Exception;

	/**
	 * 根据用户 id分页查询用户刷题记录
	 * @param exercisesQueryVo
	 * @param pageBean
	 * @return
	 * @throws Exception
	 */
	public List<ExercisesCustom> findExercisesListByVo(ExercisesQueryVo exercisesQueryVo, PageBean pageBean) throws Exception;

	/**
	 * 根据用户id获取用户刷题总数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getExerTotalCountByUserId(Integer userId) throws Exception;


}
