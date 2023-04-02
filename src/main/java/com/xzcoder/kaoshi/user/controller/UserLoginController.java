package com.xzcoder.kaoshi.user.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.UserService;

/**
 * UserLoginController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
public class UserLoginController {

    @Autowired
    private UserService uService;

    @RequestMapping("login.html")
    public String loginHtml() throws Exception {
        return "user/login";
    }


    @RequestMapping("menu.html")
    public String menuHtml() throws Exception {
        return "user/center";
    }

    @RequestMapping("login.action")
    public String login(HttpServletRequest request, Model model, HttpSession session,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) throws Exception {

        SysUserCustom user = uService.getUserByUnameAndPwd(username, password);
        if (user == null) {
            model.addAttribute("message", "用户名或密码错误！");
            return "forward:login.html";
        }

        //将用户登录信息设置到application域
        ServletContext context = request.getSession().getServletContext();
        @SuppressWarnings("unchecked")
        Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");

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
        return "redirect:/user/center.html";
    }

    @RequestMapping("loginout.action")
    public String loginout(HttpSession session) throws Exception {
        //获取application域对象
        ServletContext context = session.getServletContext();
        //获取 session中的user对象
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");
        //获取application中的用户map集合
        @SuppressWarnings("unchecked")
        Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");
        //删除map中的该用户
        map.remove(user.getId());
        //删除session中的用户信息
        session.removeAttribute("user");
        session.invalidate();
        //重定向到登录页面
        return "redirect:login.html";
    }

}
