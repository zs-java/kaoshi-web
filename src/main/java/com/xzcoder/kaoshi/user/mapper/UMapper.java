package com.xzcoder.kaoshi.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * UMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface UMapper {

    /**
     * 根据用户名密码判断用户是否存在
     * 存在：返回用户信息
     * 不存在：返回null
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public SysUserCustom getUserByUnameAndPwd(@Param("username") String username,
                                              @Param("password") String password) throws Exception;

    /**
     * 将用户登录次数+1
     *
     * @param id
     * @throws Exception
     */
    public void updateLoginCountAddById(Integer id) throws Exception;

}
