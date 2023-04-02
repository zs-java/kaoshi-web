package com.xzcoder.kaoshi.user.controller;

import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.StWrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzcoder.kaoshi.po.StWrong;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * WrongExerciseController
 * 用户错题Controller
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/user")
public class WrongExerciseController {

    @Autowired
    private StWrongService stWrongService;

    @RequestMapping("myWrongExercise.html")
    public String myWrongExerciseHtml() throws Exception {
        return "user/myWrongExercise";
    }

//	@RequestMapping("getUserWrongQsn.json")
//	public @ResponseBody ExamWrongCustom getUserWrongQsn(HttpSession session) throws Exception {
//		SysUserCustom user = (SysUserCustom) session.getAttribute("user");
//		return wrongService.getUserWrongQsnByUserId(user.getId());
//	}

    @RequestMapping("getUserWrongQsn.json")
    public @ResponseBody StWrong getUserWrongQsn(HttpSession session,
                                                 @RequestParam(value = "pc", defaultValue = "1") Integer pc,
                                                 @RequestParam(value = "ps", defaultValue = "4") Integer ps) throws Exception {
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");
        return stWrongService.getUserWrongs(user.getId(), pc, ps);
    }

//	@RequestMapping("updUserWrongQsn.action")
//	public void updUserWrongQsn(HttpServletResponse response, HttpSession session,
//			@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {
//
//		try {
//			//获取用户信息
//			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
//			stWrongService.updateUserWrong(questionId, user);
//			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
//		} catch (CustomException e) {
//			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
//		}
//
//	}

}
