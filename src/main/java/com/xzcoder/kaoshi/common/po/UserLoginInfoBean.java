package com.xzcoder.kaoshi.common.po;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserLoginInfoBean
 * 用户登陆信息
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class UserLoginInfoBean {

    private Integer userId;// 用户id
    private String loginIp;// 客户端ip
    private Date loginDate;// 登录时间
    private String browser;
    private String os;

    // private String JSESSIONID;

    public UserLoginInfoBean() {
        super();
    }

    public UserLoginInfoBean(Integer userId, String loginIp, Date loginDate,
                             String browser, String os) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginDate = loginDate;
        this.browser = browser;
        this.os = os;
    }

    // public UserLoginInfoBean(Integer userId, String loginIp, Date loginDate,
    // String jSESSIONID) {
    // super();
    // this.userId = userId;
    // this.loginIp = loginIp;
    // this.loginDate = loginDate;
    // JSESSIONID = jSESSIONID;
    // }

    public String getLoginDateString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return loginDate != null ? df.format(loginDate) : null;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    // public String getJSESSIONID() {
    // return JSESSIONID;
    // }
    //
    // public void setJSESSIONID(String jSESSIONID) {
    // JSESSIONID = jSESSIONID;
    // }

}
