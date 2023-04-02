package com.xzcoder.kaoshi.user.service;

import com.xzcoder.kaoshi.po.ExamWrongCustom;

/**
 * WrongService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface WrongService {

    /**
     * 根据用户id获取该用户错题的详细信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public ExamWrongCustom getUserWrongQsnByUserId(Integer userId) throws Exception;

    /**
     *
     * @param userId
     * @param questionId
     * @throws Exception
     */
//	public void updateUserWrongQsn(Integer questionId, SysUserCustom user) throws Exception;

    /**
     * 获取该用户的错题数量
     * @param user
     * @return
     * @throws Exception
     */
//	public Integer getUserWrongCount(SysUserCustom user) throws Exception;

    /**
     * 添加错题
     * @param user
     * @param questionId
     * @throws Exception
     */
//	public void insertUserWrong(SysUserCustom user, Integer questionId) throws Exception;

}
