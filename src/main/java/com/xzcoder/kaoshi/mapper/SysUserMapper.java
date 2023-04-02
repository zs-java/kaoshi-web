package com.xzcoder.kaoshi.mapper;

import com.xzcoder.kaoshi.po.SysUser;
import com.xzcoder.kaoshi.po.SysUserExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * SysUserMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}
