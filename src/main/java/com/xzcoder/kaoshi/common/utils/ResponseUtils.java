package com.xzcoder.kaoshi.common.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * ResponseUtils
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ResponseUtils {

    public static void write(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(obj.toString());
        out.flush();
        out.close();
    }

}
