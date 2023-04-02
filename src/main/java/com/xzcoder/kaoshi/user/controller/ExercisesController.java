package com.xzcoder.kaoshi.user.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.ExercisesService;
import com.xzcoder.kaoshi.common.po.ExerInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;

/**
 * ExercisesController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/user")
public class ExercisesController {

    @Autowired
    private ExercisesService exercisesService;

    @RequestMapping("chooseExercises.html")
    public String chooseExercisesHtml() throws Exception {
        return "user/chooseExercises";
    }

    @RequestMapping("exercising.html")
    public String exercisingHtml(Model model,
                                 @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {
        model.addAttribute("exerId", exerId);
        return "user/exercising";
    }

    @RequestMapping("startExer.action")
    public void startExer(HttpServletResponse response, HttpSession session,
                          @RequestParam(value = "classifyIds[]", required = true) Integer[] classifyIds,
                          @RequestParam(value = "knowledgeIds[]", required = true) Integer[] knowledgeIds) throws Exception {

        try {
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            Integer id = exercisesService.insertExercises(classifyIds, knowledgeIds, user);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", id));
        } catch (CustomException e) {
//			if(e.getMessage().equals("不存在该类题目！"))
//				ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }

    }

    @RequestMapping("getNextQuestion.json")
    public void getNextQuestion(HttpServletResponse response, HttpSession session,
                                @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {
        try {
            /*
             * 首先检验session中是否已经存在刷题信息（判断是否存在exerInfo，如果存在判断exerId是否相等，已经是否submit）
             * 检验方法：
             * 	判断session中是否能存在exerInfo
             * 	存在：说明已经开始刷题或之前本次登陆进行过刷题
             * 		判断exerId是否相同
             * 		相同：判断isSubmit（即上一题是否完成），若为false，则重新指定该题
             * 		不同：说明exerInfo是本次登陆上一轮刷题留下的，通过检验
             * 	不存在：本次登陆为进行过刷题，可以通过检验
             */
            ExerInfo lastExerInfo = (ExerInfo) session.getAttribute("exerInfo");
            if (lastExerInfo != null) {
                //已经存在刷题信息
                if (exerId == (int) lastExerInfo.getExerId()) {
                    //是本次刷题
                    if (!lastExerInfo.getIsSubmit()) {
                        //TODO 上一题未完成，重新指定上一题
                        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", lastExerInfo.getQsnData()));
                        return;
                    }
                }
            }

            /*
             * 在服务端对题目进行重新排序
             * TODO 暂不进行乱序
             * 将试题信息封装成ExerInfo对象
             * 保存到session中
             */
            //根据刷题id，获取本次刷题题目
            JSONObject qsnData = exercisesService.getNextQuestion(exerId);
            ExerInfo exerInfo = new ExerInfo();
            //试题编号
            exerInfo.setStId(qsnData.getInt("questionId"));
            //刷题编号
            exerInfo.setExerId(exerId);
            //正确答案
            exerInfo.setRightKey(qsnData.getString("rightKey"));
            //取出正确答案后将json中的正确答案删除
            qsnData.remove("rightKey");
            //试题类别
            exerInfo.setType(qsnData.getInt("typeId"));
            //试题信息
            exerInfo.setQsnData(qsnData);

            //存session
            session.setAttribute("exerInfo", exerInfo);


            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", qsnData));
        } catch (CustomException e) {
            if (e.getMessage().equals("不存在该类题目！"))
                ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("isEnd", e.getMessage()));
            else
                ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

//	@RequestMapping("submitExer.action")
//	public void submitExer(HttpServletResponse response, HttpSession session,
//			@RequestParam(value="exerId",required=true) Integer exerId,
//			@RequestParam(value="questionId",required=true) Integer questionId,
//			@RequestParam(value="rightFlag",required=true) Boolean rightFlag) throws Exception {
//
//		try {
//			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
//			exercisesService.updateExercises(exerId, questionId, rightFlag, user);
//			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
//		} catch (CustomException e) {
//			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
//		}
//
//	}

    @RequestMapping("getExercisesById.json")
    public @ResponseBody ExercisesCustom getExercisesById(Integer exerId) throws Exception {
        return exercisesService.getExercisesById(exerId);
    }

    @RequestMapping("submitExerDanxuan.action")
    public void submitExerDanxuan(HttpServletResponse response, HttpSession session,
                                  @RequestParam(value = "subKey", defaultValue = "-1") Integer subKey,
                                  @RequestParam(value = "qsnId", required = true) Integer qsnId,
                                  @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {

        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        ExerInfo exerInfo = (ExerInfo) session.getAttribute("exerInfo");
        if (exerInfo == null) {
            //session中没有题目信息，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "session中没有题目信息，提交失败"));
            return;
        }
        if ((int) exerInfo.getExerId() != exerId || (int) exerInfo.getStId() != qsnId) {
            //题目编号、刷题编号不匹配，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目编号、刷题编号不匹配，提交失败"));
            return;
        }
        if (exerInfo.getIsSubmit()) {
            //题目不能重复提交
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目不能重复提交"));
            return;
        }

        JSONObject result = new JSONObject();
        result.put("rightKey", Integer.parseInt(exerInfo.getRightKey()));
        boolean rightFlag = false;
        //判断回答是否正确
        if (subKey == Integer.parseInt(exerInfo.getRightKey())) {
            //回答正确
            rightFlag = true;
        } else {
            //回答错误
            rightFlag = false;
        }
        result.put("result", rightFlag);
        exercisesService.updateExercises(exerId, qsnId, rightFlag, user);

        //设置提交完毕的状态
        exerInfo.setIsSubmit(true);
        session.setAttribute("exerInfo", exerInfo);

        //返回提交结果
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", result));
    }

    @RequestMapping("submitExerDuoxuan.action")
    public void submitExerDuoxuan(HttpServletResponse response, HttpSession session,
                                  @RequestParam(value = "subKey[]", defaultValue = "-1") Integer[] subKey,
                                  @RequestParam(value = "qsnId", required = true) Integer qsnId,
                                  @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        ExerInfo exerInfo = (ExerInfo) session.getAttribute("exerInfo");
        if (exerInfo == null) {
            //session中没有题目信息，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "session中没有题目信息，提交失败"));
            return;
        }
        if ((int) exerInfo.getExerId() != exerId || (int) exerInfo.getStId() != qsnId) {
            //题目编号、刷题编号不匹配，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目编号、刷题编号不匹配，提交失败"));
            return;
        }
        if (exerInfo.getIsSubmit()) {
            //题目不能重复提交
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目不能重复提交"));
            return;
        }

        JSONObject result = new JSONObject();
        result.put("rightKey", JSONArray.fromObject(exerInfo.getRightKey()));
        boolean rightFlag = false;
        //判断回答是否正确
        if (JSONArray.fromObject(subKey).toString().equals(JSONArray.fromObject(exerInfo.getRightKey()).toString())) {
            //回答正确
            rightFlag = true;
        } else {
            //回答错误
            rightFlag = false;
        }
        result.put("result", rightFlag);
        exercisesService.updateExercises(exerId, qsnId, rightFlag, user);

        //设置提交完毕的状态
        exerInfo.setIsSubmit(true);
        session.setAttribute("exerInfo", exerInfo);

        //返回提交结果
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", result));
    }

    @RequestMapping("submitExerPanduan.action")
    public void submitExerPanduan(HttpServletResponse response, HttpSession session,
                                  @RequestParam(value = "subKey", defaultValue = "-1") Integer subKey,
                                  @RequestParam(value = "qsnId", required = true) Integer qsnId,
                                  @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        ExerInfo exerInfo = (ExerInfo) session.getAttribute("exerInfo");
        if (exerInfo == null) {
            //session中没有题目信息，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "session中没有题目信息，提交失败"));
            return;
        }
        if ((int) exerInfo.getExerId() != exerId || (int) exerInfo.getStId() != qsnId) {
            //题目编号、刷题编号不匹配，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目编号、刷题编号不匹配，提交失败"));
            return;
        }
        if (exerInfo.getIsSubmit()) {
            //题目不能重复提交
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目不能重复提交"));
            return;
        }

        JSONObject result = new JSONObject();
        result.put("rightKey", exerInfo.getRightKey());
        boolean rightFlag = false;
        //判断回答是否正确
        if ((int) Integer.parseInt(exerInfo.getRightKey()) == subKey) {
            //回答正确
            rightFlag = true;
        } else {
            //回答错误
            rightFlag = false;
        }
        result.put("result", rightFlag);
        exercisesService.updateExercises(exerId, qsnId, rightFlag, user);

        //设置提交完毕的状态
        exerInfo.setIsSubmit(true);
        session.setAttribute("exerInfo", exerInfo);

        //返回提交结果
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", result));
    }

    @RequestMapping("submitExerTiankong.action")
    public void submitExerTiankong(HttpServletResponse response, HttpSession session,
                                   @RequestParam(value = "subKey", defaultValue = "-1") Integer subKey,
                                   @RequestParam(value = "qsnId", required = true) Integer qsnId,
                                   @RequestParam(value = "exerId", required = true) Integer exerId) throws Exception {

        ExerInfo exerInfo = (ExerInfo) session.getAttribute("exerInfo");
        if (exerInfo == null) {
            //session中没有题目信息，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "session中没有题目信息，提交失败"));
            return;
        }
        if ((int) exerInfo.getExerId() != exerId || (int) exerInfo.getStId() != qsnId) {
            //题目编号、刷题编号不匹配，提交失败
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目编号、刷题编号不匹配，提交失败"));
            return;
        }
        if (exerInfo.getIsSubmit()) {
            //题目不能重复提交
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", "题目不能重复提交"));
            return;
        }

        //TODO 未对填空题进行操作

        //设置提交完毕的状态
        exerInfo.setIsSubmit(true);
        session.setAttribute("exerInfo", exerInfo);

        //返回提交结果
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
    }


}
