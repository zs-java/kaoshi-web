package com.xzcoder.kaoshi.admin.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzcoder.kaoshi.po.StWrong;
import com.xzcoder.kaoshi.po.Wrong;

/**
 * StWrongMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface StWrongMapper {

	public List<Wrong> findWrongDateByUserId(StWrong stWrong) throws Exception;

	public Integer getWrongDateCountByUserId(StWrong stWrong) throws Exception;

	public List<Integer> findStIdsByUserIdAndDate(Wrong wrong) throws Exception;

	public void deleteWrongByUserIdQsnId(@Param("userId") Integer userId, @Param("qsnId") Integer qsnId) throws Exception;

	public void insertWrongByUserIdQsnId(@Param("userId") Integer userId, @Param("qsnId") Integer qsnId) throws Exception;

	public Integer getUserWrongsCount(Integer userId) throws Exception;

	public void deleteWrongByUserId(Integer userId) throws Exception;

	public void deleteWrongByQsnId(Integer questionId) throws Exception;

	public void deleteWrongByUserIdQsnIdNowDate(@Param("userId") Integer userId, @Param("qsnId") Integer qsnId);

	public void deleteWrongBeforeDate(Date date) throws Exception;


}
