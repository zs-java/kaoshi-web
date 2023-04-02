package com.xzcoder.kaoshi.user.service;

import java.math.BigDecimal;
import java.util.List;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * KsUserService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface KsUserService {

    /**
     * 将用户考试信息状态修改为正在审核
     *
     * @param id
     * @throws Exception
     */
    public void updateKsUserSignUpById(Integer id) throws Exception;

    /**
     * 分页、条件查询正在报名的用户考试信息集合
     *
     * @param ksUserQueryVo
     * @param pageBean
     * @return
     * @throws Exception
     */
    public List<ExamKsUserCustom> findSignupingKsUserListByVo(KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception;

    /**
     * 根据id审核用户考试信息， 报名通过
     *
     * @param id
     * @throws Exception
     */
    public void updateSignupTrueKsUserById(Integer id, SysUserCustom user) throws Exception;

    /**
     * 根据用户编号获取考试未读信息数量
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getKsUserCountNotReadById(Integer userId) throws Exception;

    /**
     * 根据用户编号将该用户的所有考试信息设置为已读
     *
     * @param userId
     * @throws Exception
     */
    public void updateKsUserReadedByUserId(Integer userId) throws Exception;

    /**
     * 根据编号查询用户考试的详细信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ExamKsUserCustom getKsUserById(Integer id) throws Exception;

    /**
     * 将用户考试状态修改为正在考试中
     *
     * @param id
     * @throws Exception
     */
    public void updateGoExamStateById(Integer id) throws Exception;

    /**
     * 根据id修改用户考试答题信息
     *
     * @param ksUserCustom
     * @throws Exception
     */
    public void updateKsUserAnswerById(Integer kuId, String gdStr, SysUserCustom user) throws Exception;

    /**
     * 交卷
     *
     * @param kuId
     * @param gdStr
     * @param score
     * @param ksUuid
     * @param user
     * @throws Exception
     */
    public void updateKsUserSubmit(Integer kuId, String gdStr, Integer[] wrongStIds, BigDecimal score, String ksUuid, SysUserCustom user) throws Exception;

    /**
     * 获取当前用户考试倒计时时间
     *
     * @param kuId
     * @return
     * @throws Exception
     */
    public Integer getCountDownTime(Integer kuId) throws Exception;

    /**
     * 分页、组合条件查询用户成绩信息集合
     *
     * @param ksUserQueryVo
     * @param pageBean
     * @return
     * @throws Exception
     */
    public List<ExamKsUserCustom> findResultListByVo(KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception;

    /**
     * 获取用户的未读成绩信息数量
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getNotReadResultByUserId(Integer userId) throws Exception;

    /**
     * 将满足条件的信息更新为已评分
     *
     * @param userId
     * @throws Exception
     */
    public void updateKsUserPublishResultByUserId(Integer userId) throws Exception;

    /**
     * 将该用户的所有成绩设置为已读
     *
     * @param userId
     * @throws Exception
     */
    public void updateResultReadedById(Integer userId) throws Exception;

    /**
     * 根据用户id查询该用户的考试数量
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getKsUserCountByUserId(Integer userId) throws Exception;

    /**
     * 根据用户id查询该用户已经做过的题目数量
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getUserDoQsnCountByUserId(Integer userId) throws Exception;

    /**
     * 根据考试id分页、组合条件查询成绩信息
     *
     * @param vo
     * @param pageBean
     * @return
     * @throws Exception
     */
    public List<ExamKsUserCustom> findKsUserByKsId(KsUserQueryVo vo, PageBean pageBean) throws Exception;

    public void addUsersToExam(Integer ksId, Integer[] userIds, SysUserCustom user) throws Exception;

}
