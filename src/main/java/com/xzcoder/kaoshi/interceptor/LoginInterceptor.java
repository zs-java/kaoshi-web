package com.xzcoder.kaoshi.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import com.xzcoder.kaoshi.po.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xzcoder.kaoshi.common.utils.CommonUtils;

/**
 * LoginInterceptor
 * 登录认证拦截器
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class LoginInterceptor implements HandlerInterceptor {

    //进入Handler方法之前执行
    //用于身份认证、身份授权
    //比如身份认证，如果认证不通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //获取请求的url
        String url = request.getRequestURI();

        //判断url是否是公开地址（实际使用时间公开地址配置在配置文件中）
        //这里公开地址是登陆提交的地址
        //管理员登陆页面放行
        if (url.indexOf("admin/login") >= 0) {
            return true;
        }
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        ///kaoshi/user/submitExerTiankong.action
        if (url.indexOf("admin") >= 0 || url.indexOf("user") >= 0) {
            //不是公开地址，验证拦截
            if (user == null) {
                //验证失败，保存错误信息转发到登陆页面
                this.RedirectLogin(request, response, url, "请先登录！");
                return false;
            } else {
                /*
                 * 如果用户信息存在
                 * 判断用户是否已经登陆过
                 */
                ServletContext context = session.getServletContext();
                @SuppressWarnings("unchecked")
                Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");
                //判断用户是否已经登陆
                if (map.containsKey(user.getId())) {
                    /*
                     * 用户已经登陆
                     * 判断登陆信息中的ip是否和当前用户ip相同
                     * 如果相同，则放行
                     * 如果不同，说明该账号已经在其他客户端登陆，不放行
                     */
                    String ip = CommonUtils.getIpAddress(request);
                    UserLoginInfoBean loginInfo = map.get(user.getId());
                    if (loginInfo.getLoginIp().equals(ip)) {
                        //相同
                        if (user.getRoleId() == 2 && url.startsWith(request.getContextPath() + "/admin/")) {
                            //非法访问
                            request.setAttribute("message", "非法访问！");
                            request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
                            return false;
                        }
                        return true;
                    } else {
                        //不同
                        this.RedirectLogin(request, response, url, "您的账号已在其他客户端登陆！");
                        return false;
                    }
                } else {
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
                    if (user.getRoleId() == 2 && url.startsWith(request.getContextPath() + "admin/")) {
                        //非法访问
                        request.setAttribute("message", "非法访问！");
                        request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
                        return false;
                    }
                    return true;
                }
            }
        }
        //公开地址或验证成功，放行
        return true;
    }

    private void RedirectLogin(HttpServletRequest request, HttpServletResponse response, String url, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        if (url.indexOf("admin") >= 0) {
//			response.sendRedirect(request.getContextPath() + "/admin/login.html");
//			PrintWriter out = response.getWriter();
//			out.print("<script language = javascript>window.open('" + request.getContextPath() + "/admin/login.html?message=请先登录！','_top')</script>");
            request.getRequestDispatcher("/WEB-INF/jsp/admin/login.jsp").forward(request, response);
        } else if (url.indexOf("user") >= 0)
            request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
    }


    //进入Handler方法之后，返回modelAndView之前执行
    //应用场景从modelAndView出发：将公用的模型数据（比如菜单导航）在这里传到视图，也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView mv) throws Exception {
    }

    //执行Handler方法之后
    //应用场景：统一的异常处理，统一日志处理
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
