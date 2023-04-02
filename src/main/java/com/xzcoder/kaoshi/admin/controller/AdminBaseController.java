package com.xzcoder.kaoshi.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.AdminBaseService;
import com.xzcoder.kaoshi.admin.service.ModuleService;
import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.po.SysModuleCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * AdminBaseController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin")
public class AdminBaseController {

	@Autowired
	private AdminBaseService adminBaseService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 转发到登录页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login.html")
	public String loginHtml() throws Exception {
		return "admin/login";
	}

	/**
	 * 获取当前角色拥有的功能并转发到菜单页面
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("menu.html")
	public String menuHtml(HttpSession session, Model model) throws Exception {
		//获取当前已经登陆的用户信息
		SysUserCustom user = (SysUserCustom) session.getAttribute("user");
		//通过user_id查询 用户拥有的功能
		List<SysModuleCustom> moduleCustomList = moduleService.findModuleAndFunctionByUserId(user.getId());
		//将模块与功能信息保存到request域
		model.addAttribute("moduleCustomList", moduleCustomList);
		return "admin/menu";
	}

	/**
	 * 转发到欢迎页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("welcome.html")
	public String welcomeHtml() throws Exception {
		return "admin/welcome";
	}

	/**
	 * 用户登录
	 * @param request
	 * @param session
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login.action")
	public String login(HttpServletRequest request, HttpSession session, Model model, String username, String password) throws Exception {
		SysUserCustom user = adminBaseService.login(username, password);
		//如果用户不存在，保存错误信息转发到登陆页面
		if(user==null) {
			model.addAttribute("message", "用户名或密码错误！");
			return "forward:login.html";
		}

		//用户如果已经登陆已经登陆则重新设置用户的登陆信息，即将原先登录的用户挤下线
		ServletContext context = request.getSession().getServletContext();
		@SuppressWarnings("unchecked")
		Map<Integer, UserLoginInfoBean> map =  (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");

		//获取当前用户的真实ip
		String ip = CommonUtils.getIpAddress(request);
		//得到用户的浏览器名
		String userbrowser = CommonUtils.getBrowserInfoFromRequest(request);
		//得到用户的操作系统名
		String useros = CommonUtils.getOsInfoFromRequest(request);

		/*
		 * 判断用户信息是否存在
		 * 如果存在
		 * 重新设置用登陆信息，即将原先登陆的用户挤下线
		 * 如果不存在
		 * 添加user登陆信息
		 */
		map.put(user.getId(), new UserLoginInfoBean(user.getId(), ip, new Date(), userbrowser, useros));

		//将用户信息保存到session域中
		session.setAttribute("user", user);
		//重定向到菜单页面
		return "redirect:menu.html";
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("logout.action")
	public String logout(HttpSession session) throws Exception {
		//获取session中的用户信息
		SysUserCustom user = (SysUserCustom) session.getAttribute("user");
		//将application中该用户的登陆信息删除
		@SuppressWarnings("unchecked")
		Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) session.getServletContext().getAttribute("userLoginMap");
		if(map.containsKey(user.getId()))
			map.remove(user.getId());
		//将session中的该用户信息删除
		session.removeAttribute("user");
		session.invalidate();
		//重定向到登陆页面
		return "redirect:login.html";
	}

}
