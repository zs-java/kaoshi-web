package com.xzcoder.kaoshi.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@WebListener
public class UserSessionListener implements HttpSessionListener {

    public UserSessionListener() {
    }

    /**
     * session创建时执行
     */
    public void sessionCreated(HttpSessionEvent event) {
    }

    /**
     * session死亡前执行
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        //session
        HttpSession session = event.getSession();
        //context
        ServletContext context = session.getServletContext();

        //从session中获取已经登录的用户信息
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        /*
         * 如果user不为空，即用户没有退出登录就直接关闭会话
         * 则删除applicaton中的用户登录信息
         * 如果user为空，则无需操作
         */
        if (user != null) {
            @SuppressWarnings("unchecked")
            Map<Integer, UserLoginInfoBean> map = (Map<Integer, UserLoginInfoBean>) context.getAttribute("userLoginMap");
            if (map.containsKey(user.getId()))
                map.remove(user.getId());
        }
    }

}
