package com.xzcoder.kaoshi.mapper;

import com.xzcoder.kaoshi.po.SysRoleFunction;
import com.xzcoder.kaoshi.po.SysRoleFunctionExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * SysRoleFunctionMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SysRoleFunctionMapper {
    int countByExample(SysRoleFunctionExample example);

    int deleteByExample(SysRoleFunctionExample example);

    int insert(SysRoleFunction record);

    int insertSelective(SysRoleFunction record);

    List<SysRoleFunction> selectByExample(SysRoleFunctionExample example);

    int updateByExampleSelective(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);

    int updateByExample(@Param("record") SysRoleFunction record, @Param("example") SysRoleFunctionExample example);
}
