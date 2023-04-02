package com.xzcoder.kaoshi.user.service;

import java.util.List;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * UserService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface UserService {

    /**
     * 判断用户名密码是否正确
     * 正确：返回该用户信息
     * 错误：返回null
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public SysUserCustom getUserByUnameAndPwd(String username, String password) throws Exception;

    /**
     * 根据用户id和用户考试状态查询用户考试信息
     *
     * @param ksUserQueryVo
     * @return
     * @throws Exception
     */
    public List<ExamKsUserCustom> findKsUserListByUidAndState(KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception;

}
