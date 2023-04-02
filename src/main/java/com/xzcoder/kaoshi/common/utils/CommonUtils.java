package com.xzcoder.kaoshi.common.utils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.xzcoder.kaoshi.exception.ThrowsException;

/**
 * CommonUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class CommonUtils {

    /**
     * 生成uuid
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 通过反代理获取用户的真实ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 通过request获取请求的浏览器名称
     *
     * @param request
     * @return
     */
    public static String getBrowserInfoFromRequest(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();

        String browser = "";

        // ===============Browser===========================
        if (user.contains("edge")) {
            browser = (userAgent.substring(userAgent.indexOf("Edge"))
                    .split(" ")[0]).replace("/", "-");
        } else if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE"))
                    .split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-"
                    + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(
                    " ")[0]).split("/")[0]
                    + "-"
                    + (userAgent.substring(userAgent.indexOf("Version")).split(
                    " ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera"))
                        .split(" ")[0]).split("/")[0]
                        + "-"
                        + (userAgent.substring(userAgent.indexOf("Version"))
                        .split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR"))
                        .split(" ")[0]).replace("/", "-")).replace("OPR",
                        "Opera");
            }

        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(
                    " ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1)
                || (user.indexOf("netscape6") != -1)
                || (user.indexOf("mozilla/4.7") != -1)
                || (user.indexOf("mozilla/4.78") != -1)
                || (user.indexOf("mozilla/4.08") != -1)
                || (user.indexOf("mozilla/3") != -1)) {
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(
                    " ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv"))
                    .split(" ")[0]).replace("rv:", "-");
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }

        return browser;
    }

    /**
     * 通过request获取请求的操作系统名称
     *
     * @param request
     * @return
     */
    public static String getOsInfoFromRequest(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        // =================OS Info=======================
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }
        return os;
    }

    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * 将String类型的id数组转换成Integer数组
     *
     * @param ids
     * @return
     * @throws ThrowsException
     */
    public static Integer[] StringArrToIntegerArr(String ids) throws ThrowsException {
        if (ids == null || "".equals(ids.trim())) {
            return null;
        }
        try {
            //去除所有双引号
            ids = ids.replace("\"", "");
            String[] split = ids.split(",");
            List<Integer> idList = new ArrayList<Integer>();
            for (String idStr : split) {
                if (!"".equals(idStr)) {
                    idList.add(Integer.parseInt(idStr));
                }
            }
            Integer[] questionIds = new Integer[idList.size()];
            questionIds = idList.toArray(questionIds);
            return questionIds;
        } catch (Exception e) {
            throw new ThrowsException("参数错误！ids:" + ids);
        }
    }

    public static Integer[] JSONArrToIntegerArray(String jsonArrStr) throws ThrowsException {
        //去掉两端的[]
        jsonArrStr = jsonArrStr.substring(jsonArrStr.indexOf("[") + 1, jsonArrStr.indexOf("]"));
        return StringArrToIntegerArr(jsonArrStr);
    }

    public static String getTitleTextByTitle(String title) {
        int index = title.indexOf("<img");
        if (index != -1) {
            title = title.substring(0, index) + "[图片]" + title.substring(index);
        }
        return delHTMLTag(title);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] convertToArr(List<T> list, Class<?> clazz) {
        T[] arr = (T[]) Array.newInstance(clazz, list.size());
        arr = list.toArray(arr);
        return arr;
    }

    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

}
