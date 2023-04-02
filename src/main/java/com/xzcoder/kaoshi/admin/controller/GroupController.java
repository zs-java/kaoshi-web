package com.xzcoder.kaoshi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xzcoder.kaoshi.admin.service.GroupService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.po.SysGroupCustom;

/**
 * GroupController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping("groupManager.html")
	public String groupManagerHtml() throws Exception {
		return "admin/groupManager";
	}

	@RequestMapping("getAllGroupTree.json")
	public void getAllRoleTree(HttpServletResponse response) throws Exception {
		List<SysGroupCustom> groupList = groupService.getAllGroups();
		String json = JSONConvertUtils.groupsToJSON(groupList);
		ResponseUtils.write(response, json);
	}

	@RequestMapping("addGroup.action")
	public void addGroup(@RequestParam(required=true) Integer groupPid,
						@RequestParam(required=true) String groupName,
			HttpServletResponse response) throws Exception {

		Integer newGroupId = groupService.insertGroup(groupPid, groupName);

		JSONObject json = new JSONObject();
		json.put("msg", "success");
		json.put("newGroupId", newGroupId);

		ResponseUtils.write(response, json);
	}

	@RequestMapping("renameGroup.action")
	public void renameGroup(HttpServletResponse response,
				@RequestParam(value="groupId",required=true) Integer groupId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		groupService.updateGroupNameByGroupId(groupId, newName);

		ResponseUtils.write(response, JSONConvertUtils.singleProptiesToJSON("msg", "success"));
	}

	@RequestMapping("deleteGroup.action")
	public void deleteGroup(HttpServletResponse response,
			@RequestParam(value="groupId",required=true) Integer groupId,
			@RequestParam(value="groupPid",required=true) Integer groupPid) throws Exception {
		groupService.deleteGroupByGroupId(groupId,groupPid);
		ResponseUtils.write(response, JSONConvertUtils.singleProptiesToJSON("msg", "success"));
	}

}
