package com.xzcoder.kaoshi.admin.service;

import java.util.Date;

import com.xzcoder.kaoshi.po.StWrong;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * StWrongService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StWrongService {

	public StWrong getUserWrongs(Integer userId, Integer pc, Integer ps) throws Exception;

	public void updateUserWrong(Integer questionId, SysUserCustom user) throws Exception;

	public void insertUserWrong(Integer userId, Integer questionId) throws Exception;

	public Integer getUserWrongCount(SysUserCustom user) throws Exception;

	public void deleteWrongBeforeDate(Date date) throws Exception;

}
