package com.xzcoder.kaoshi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xzcoder.kaoshi.admin.service.ModuleService;
import com.xzcoder.kaoshi.admin.service.RoleService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.SysFunctionCustom;
import com.xzcoder.kaoshi.po.SysModuleCustom;
import com.xzcoder.kaoshi.po.SysRoleCustom;
import com.xzcoder.kaoshi.vo.RoleQueryVo;

/**
 * RoleController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;

	@RequestMapping("roleManager.html")
	public String roleManagerHtml() throws Exception {
		return "admin/roleManager";
	}

	@RequestMapping("queryRoleList.json")
	public void queryRoleList(RoleQueryVo roleQueryVo,
			@RequestParam(value="page",defaultValue="1") String pageCode,
			@RequestParam(value="rows",defaultValue="15") String pageSize,
			HttpServletResponse response) throws Exception {

		//封装分页信息并设置到roleQueryVo中
		roleQueryVo.setPageBean(new PageBean(Integer.parseInt(pageCode), Integer.parseInt(pageSize)));

		List<SysRoleCustom> roles = roleService.findRolesByRoleQueryVo(roleQueryVo);

		JSONObject result = new JSONObject();
		JSONArray jsonArr = JSONArray.fromObject(roles);
		result.put("total", roleQueryVo.getPageBean().getTotalRecored());
		result.put("rows", jsonArr);

		ResponseUtils.write(response, result);
	}

	@RequestMapping("addRole.html")
	public String addRoleHtml(Model model) throws Exception {

		List<SysModuleCustom> modules = moduleService.findAllModuleAndFunction();

		model.addAttribute("modules", modules);

		return "admin/addRole";
	}

	@RequestMapping("addRole.action")
	public void addRole(
			@RequestParam(value="roleName",required=true) String roleName,
			@RequestParam(value="des",required=false) String des,
			@RequestParam("functionIds") Integer[] functionIds,
			HttpServletResponse response) throws Exception {

		try {
			roleService.insertRole(roleName, des, functionIds);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("deleteRole.action")
	public void deleteRole(HttpServletResponse response,
			@RequestParam(value="roleId",required=true) Integer roleId) throws Exception {

		try {
			roleService.deleteRoleByRoleId(roleId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("editRole.html")
	public String editRoleHtml(Model model, @RequestParam(value="roleId",required=true) Integer roleId) throws Exception {

		//查询该角色信息并保存到model
		SysRoleCustom roleCustom = roleService.findRoleCustomByRoleId(roleId);
		model.addAttribute("roleCustom", roleCustom);

		//抽取角色信息中的functions集合，转换成json数据，保存到model中
		List<SysFunctionCustom> functions = roleCustom.getFunctions();
		JSONArray funcJson = JSONArray.fromObject(functions);
		model.addAttribute("functionsJson", funcJson.toString());

		//查询所有模块与功能并保存到model中
		List<SysModuleCustom> modules = moduleService.findAllModuleAndFunction();
		model.addAttribute("modules", modules);

		return "admin/editRole";
	}

	@RequestMapping("updateRole.action")
	public void updateRole(HttpServletResponse response,
				@RequestParam(value="roleId",required=true) Integer roleId,
				@RequestParam(value="roleName",required=true) String roleName,
				@RequestParam(value="des",required=false) String des,
				@RequestParam("functionIds") Integer[] functionIds) throws Exception {

		try {
			roleService.updateRole(roleId, roleName, des, functionIds);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}


}
