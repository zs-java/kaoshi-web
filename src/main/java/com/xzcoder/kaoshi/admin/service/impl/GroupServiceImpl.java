package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.GroupMapper;
import com.xzcoder.kaoshi.admin.mapper.UserMapper;
import com.xzcoder.kaoshi.admin.service.GroupService;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.SysGroupCustom;

/**
 * GroupServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public SysGroupCustom getAllGroupsTree() throws Exception {
		//根分组的父分组id为0，所以先获取父分组集合
		List<SysGroupCustom> groups = groupMapper.findGroupsByGroupPid(0);
		//填充父分组集合的所有子节点
		fullGoups(groups);
		//只有一个父分组，父分组集合中get(0)获取父分组
		SysGroupCustom rootGroup = groups.get(0);
		return rootGroup;
	}

	/**
	 * 通过递归查询并填充父分组的子分组
	 * @param groups
	 * @throws Exception
	 */
	private void fullGoups(List<SysGroupCustom> groups) throws Exception {
		for (int i = 0; i < groups.size(); i++) {
			SysGroupCustom group = groups.get(i);
			int groupId = group.getGroupId();
			List<SysGroupCustom> childGroups = groupMapper.findGroupsByGroupPid(groupId);
			group.setChildGroups(childGroups);
			if(childGroups != null) {
				fullGoups(group.getChildGroups());
			}
		}
	}

	public Integer[] getChildGroupsIdByPid(Integer pid) throws Exception {
		List<Integer> pids = new ArrayList<>();
		pids.add(pid);
		fullChildGroupIdByPid(pids);
		Integer[] arr = new Integer[pids.size()];
		arr = pids.toArray(arr);
		return arr;
	}

	private void fullChildGroupIdByPid(List<Integer> pids) throws Exception {
		for (int i = 0; i < pids.size(); i++) {
			Integer pid = pids.get(i);
			List<Integer> cids = groupMapper.getChildGroupsIdByPid(pid);
			if(cids != null && cids.size() > 0)
				pids.addAll(cids);
				fullChildGroupIdByPid(cids);
		}
	}

	@Override
	public List<SysGroupCustom> getAllGroups() throws Exception {
		return groupMapper.getAllGroups();
	}

	@Override
	public Integer insertGroup(Integer groupPid, String newGroupName) throws Exception {

		//将新增分组信息封装到group对象
		SysGroupCustom group = new SysGroupCustom();
		group.setGroupPid(groupPid);
		group.setGroupName(newGroupName);

		groupMapper.insertGroup(group);

		return group.getGroupId();
	}

	@Override
	public void updateGroupNameByGroupId(Integer groupId, String newName)
			throws Exception {

		//将分组id和新名称封装成group对象
		SysGroupCustom group = new SysGroupCustom();
		group.setGroupId(groupId);
		group.setGroupName(newName);

		groupMapper.updateGroupNameByGroupId(group);
	}

	@Override
	public void deleteGroupByGroupId(Integer groupId, Integer groupPid) throws Exception {
		if(groupId == 1)
			throw new CustomException("不能删除根分组！");

		//获取该分组的子分组id
		Integer[] groupIds = getChildGroupsIdByPid(groupId);

		//先将该分组以及子分组对应的用户移动到默认分组
		userMapper.updateUserGroupIdByGroupIdArray(groupPid, groupIds);
		userMapper.updateUserGroupIdByGroupId(groupPid, groupId);

		//将该分组的子分组删除
		if(groupIds != null && groupIds.length > 0)
			groupMapper.deleteGroupByIdArray(groupIds);

		//删除该分组
		groupMapper.deleteGroupById(groupId);
	}

}
