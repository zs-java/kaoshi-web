package com.xzcoder.kaoshi.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * CustomExceptionResolver
 * 全局异常处理器
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object Handler, Exception ex) {
        //handler就是处理器适配器要执行的Handler对象（只有method）

//		解析出当前异常类型
//		String message = null;
//		if(ex instanceof CustomException) {
//			如果该异常类型是系统自定义的异常，直接取出异常类型信息，在错误页面显示
//			message = ((CustomException)ex).getMessage();
//		} else {
//			如果该异常类型不是系统自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
//			message = "未知错误";
//		}

        //上边代码变为
        CustomException customException = null;
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            customException = new CustomException("未知错误");
        }

        //错误信息
        String message = customException.getMessage();
        ModelAndView mv = new ModelAndView();

        //将错误信息传到页面
        mv.addObject("message", message);

        //指定错误页面
        mv.setViewName("error");
        return mv;
    }

}
