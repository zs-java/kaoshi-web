package com.xzcoder.kaoshi.admin.mapper;

import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.vo.KsUserQueryVo;
import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysGroupCustom;

/**
 * KsUserMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsUserMapper {

	/**
	 * 添加默认的考试用户信息
	 * @param ksUserCustom
	 * @throws Exception
	 */
	public void insertKsUserDefault(ExamKsUserCustom ksUserCustom) throws Exception;

	/**
	 * 删除考试用户对应信息
	 * @param ksId
	 * @param updUser
	 * @param updDate
	 * @throws Exception
	 */
	public void deleteKsUser(@Param("ksId") Integer ksId,
                             @Param("updUser") String updUser,
                             @Param("updDate") Date updDate) throws Exception;

	/**
	 * 根据用户id和用户考试状态查询用户考试信息
	 * @param ksUserQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsUserCustom> findKsUserByUidAndState(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 根据id和考试状态查询信息数量
	 * @param ksUserQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getKsUserCountByUidAndState(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 将用户考试信息状态修改为正在审核
	 * @param id
	 * @throws Exception
	 */
	public void updateKsUserSignUpById(Integer id) throws Exception;

	/**
	 * 分页、条件查询正在报名中的用户考试信息集合
	 * @param ksUserQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsUserCustom> findSignupingKsUserListByVo(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 条件查询正在报名中的用户考试数量
	 * @param ksUserQueryVo
	 * @return
	 * @throws Exception
	 */
	public Integer getSignupingKsUserByVo(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 根据id审核用户考试信息， 报名通过
	 * @param id
	 * @throws Exception
	 */
	public void updateSignupTrueKsUserById(@Param("id") Integer id, @Param("updUser") String updUser) throws Exception;

	/**
	 * 根据用户编号查询维未读的考试信息数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getNotReadExamByUserId(Integer userId) throws Exception;

	/**
	 * 根据用户编号将该用户的所有考试信息设置为已读
	 * @param userId
	 * @throws Exception
	 */
	public void updateKsUserReadedByUserId(Integer userId) throws Exception;

	/**
	 * 根据id查找用户考试信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExamKsUserCustom getKsUserById(Integer id) throws Exception;

	/**
	 * 根据id更新用户考试状态
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	public void updateKsUserStateById(@Param("id") Integer id, @Param("state") Integer state) throws Exception;

	/**
	 * 根据id修改用户考试答题信息
	 * @param ksUserCustom
	 * @throws Exception
	 */
	public void updateKsUserAnswerById(ExamKsUserCustom ksUserCustom) throws Exception;

	/**
	 * 设置开始考试时间为现在
	 * @param id
	 * @throws Exception
	 */
	public void updateKsUserBeginTimeNowById(Integer id) throws Exception;

	/**
	 * 交卷
	 * @param ksUserCustom
	 * @throws Exception
	 */
	public void updateKsUserSubmit(ExamKsUserCustom ksUserCustom) throws Exception;

	/**
	 * 根据考试id查询该考试报名成功总人数
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer findTotalCountByKsId(Integer ksId) throws Exception;

	/**
	 * 根据考试id 查询未参加考试的人数
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer findNotJoinCountByKsId(Integer ksId) throws Exception;

	/**
	 * 根据考试id 查询正在考试的人数
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer findInExamCountByKsId(Integer ksId) throws Exception;

	/**
	 * 根据考试id 查询已经交卷的考试人数
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer findExamOverCountByKsId(Integer ksId) throws Exception;

	/**
	 * 分页、组合条件查询该用户的考试成绩信息集合
	 * @param ksUserQueryVo
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsUserCustom> findExamResultByUserId(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 分页、组合条件查询该用户的考试成绩信息数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getExamResultCountByUserId(KsUserQueryVo ksUserQueryVo) throws Exception;

	/**
	 * 获取用户的未读成绩信息数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getNotReadResultByUserId(Integer userId) throws Exception;

	/**
	 * 将满足条件的信息更新为已评分
	 * @param userId
	 * @throws Exception
	 */
	public void updateKsUserPublishResultByUserId(Integer userId) throws Exception;

	/**
	 * 将该用户的所有成绩设置为已读
	 * @param userId
	 * @throws Exception
	 */
	public void updateResultReadedById(Integer userId) throws Exception;

	/**
	 * 根据用户id查询该用户的考试数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getKsUserCountByUserId(Integer userId) throws Exception;

	/**
	 * 根据用户id查询该用户已经做过的题目数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer getUserDoQsnCountByUserId(Integer userId) throws Exception;

	/**
	 * 根据考试id查询已经完成考试的用户的平均分
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Double getKsUserScoreAvgByKsId(Integer ksId) throws Exception;

	/**
	 * 根据考试id查询已经完成考试并及格的学生人数
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public Integer getPassUserCountByKsId(Integer ksId) throws Exception;

	public List<ExamKsUserCustom> findKsUserByKsId(KsUserQueryVo vo) throws Exception;

	public Integer getKsUserCountByKsId(KsUserQueryVo vo) throws Exception;

	public void deleteReadlyByUserId(Integer userId) throws Exception;

	/**
	 * 根据考试id获取所有用户答题信息
	 * @param ksId
	 * @return
	 * @throws Exception
	 */
	public List<ExamKsUserCustom> getOverKsUserByKsId(@Param("ksId") Integer ksId, @Param("groupId") Integer groupId) throws Exception;

	public List<SysGroupCustom> getKsUserGroupsByKsId(Integer ksId) throws Exception;
}
