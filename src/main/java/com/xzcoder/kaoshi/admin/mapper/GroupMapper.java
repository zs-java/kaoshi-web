package com.xzcoder.kaoshi.admin.mapper;

import java.util.List;

import com.xzcoder.kaoshi.po.SysGroupCustom;

/**
 * GroupMapper
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface GroupMapper {

	/**
	 * 根据父分组id查询父分组下的所有子分组
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<SysGroupCustom> findGroupsByGroupPid(Integer id) throws Exception;

	/**
	 * 获取所有分组信息
	 * @return
	 * @throws Exception
	 */
	public List<SysGroupCustom> getAllGroups() throws Exception;

	/**
	 * 根据父分组id查询父分组下的所有的子分组id的集合
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getChildGroupsIdByPid(Integer pid) throws Exception;

	/**
	 * 添加分组
	 * @param group
	 * @throws Exception
	 */
	public void insertGroup(SysGroupCustom group) throws Exception;

	/**
	 * 通过id重命名分组
	 * @param group
	 * @throws Exception
	 */
	public void updateGroupNameByGroupId(SysGroupCustom group) throws Exception;

	/**
	 * 根据id删除分组
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteGroupById(Integer groupId) throws Exception;

	/**
	 * 根据分组id集合删除分组
	 * @param groupIds
	 * @throws Exception
	 */
	public void deleteGroupByIdArray(Integer[] groupIds) throws Exception;
}
