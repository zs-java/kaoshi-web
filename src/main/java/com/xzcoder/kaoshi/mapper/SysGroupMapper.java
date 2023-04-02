package com.xzcoder.kaoshi.mapper;

import com.xzcoder.kaoshi.po.SysGroup;
import com.xzcoder.kaoshi.po.SysGroupExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * SysGroupMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface SysGroupMapper {
    int countByExample(SysGroupExample example);

    int deleteByExample(SysGroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    List<SysGroup> selectByExample(SysGroupExample example);

    SysGroup selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByExample(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
}
