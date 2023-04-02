package com.xzcoder.kaoshi.mapper;

import com.xzcoder.kaoshi.po.SysFunction;
import com.xzcoder.kaoshi.po.SysFunctionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * SysFunctionMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SysFunctionMapper {
    int countByExample(SysFunctionExample example);

    int deleteByExample(SysFunctionExample example);

    int deleteByPrimaryKey(Integer functionId);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    List<SysFunction> selectByExample(SysFunctionExample example);

    SysFunction selectByPrimaryKey(Integer functionId);

    int updateByExampleSelective(@Param("record") SysFunction record, @Param("example") SysFunctionExample example);

    int updateByExample(@Param("record") SysFunction record, @Param("example") SysFunctionExample example);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);
}
