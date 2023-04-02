package com.xzcoder.kaoshi.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xzcoder.kaoshi.common.po.UserLoginInfoBean;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@WebListener
public class UserServletContextListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public UserServletContextListener() {
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        //在ServletContext创建的时候添加空的用户登录信息集合
        Map<Integer, UserLoginInfoBean> map = new HashMap<Integer, UserLoginInfoBean>();
        ServletContext context = event.getServletContext();
        context.setAttribute("userLoginMap", map);
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

}
