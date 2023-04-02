package com.xzcoder.kaoshi.mapper;

import com.xzcoder.kaoshi.po.SysRole;
import com.xzcoder.kaoshi.po.SysRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * SysRoleMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
