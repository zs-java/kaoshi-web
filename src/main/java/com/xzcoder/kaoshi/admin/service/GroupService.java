package com.xzcoder.kaoshi.admin.service;

import java.util.List;

import com.xzcoder.kaoshi.po.SysGroupCustom;

/**
 * GroupService
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public interface GroupService {

	/**
	 * 返回分组的根节点，节点中包含了子节点
	 * @return
	 * @throws Exception
	 */
	public SysGroupCustom getAllGroupsTree() throws Exception;

	/**
	 * 获取所有分组信息
	 * @return
	 * @throws Exception
	 */
	public List<SysGroupCustom> getAllGroups() throws Exception;

	/**
	 * 根据父分组id查询子分组id的集合
	 * 用于查询用户时，将选中分组的子分组也进行查询
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public Integer[] getChildGroupsIdByPid(Integer pid) throws Exception;

	/**
	 * 添加分组，添加成功返回新增的分组的id
	 * @param group
	 * @throws Exception
	 */
	public Integer insertGroup(Integer groupPid, String newGroupName) throws Exception;

	/**
	 * 根据分组id进行重命名
	 * @param group
	 * @throws Exception
	 */
	public void updateGroupNameByGroupId(Integer groupId, String newName) throws Exception;

	/**
	 * 删除分组
	 * @param groupId
	 * @throws Exception
	 */
	public void deleteGroupByGroupId(Integer groupId, Integer groupPid) throws Exception;
}
