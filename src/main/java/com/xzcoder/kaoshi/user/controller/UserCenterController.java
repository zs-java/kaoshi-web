package com.xzcoder.kaoshi.user.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.ExercisesService;
import com.xzcoder.kaoshi.admin.service.KsDataService;
import com.xzcoder.kaoshi.admin.service.SjDataService;
import com.xzcoder.kaoshi.admin.service.StWrongService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.KsUserService;
import com.xzcoder.kaoshi.user.service.UserService;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * UserCenterController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {

    @Autowired
    private UserService uService;
    @Autowired
    private KsUserService ksUserService;
    @Autowired
    private KsDataService ksDataService;
    @Autowired
    private SjDataService sjDataService;
    @Autowired
    private StWrongService stWrongService;
    @Autowired
    private ExercisesService exercisesService;

    @RequestMapping("center.html")
    public String centerHtml(HttpSession session, Model model,
                             @RequestParam(value = "pc", defaultValue = "1") Integer pc,
                             @RequestParam(value = "ps", defaultValue = "8") Integer ps) throws Exception {
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");
        //用户相关考试数量
        Integer userExamCount = ksUserService.getKsUserCountByUserId(user.getId());
        //用户考试刷题数量
        Integer doQsnCount = ksUserService.getUserDoQsnCountByUserId(user.getId());
        //用户刷题练习总数
        Integer doExerCount = exercisesService.getExerTotalCountByUserId(user.getId());
        //用户错题总数
        Integer wrongCount = stWrongService.getUserWrongCount(user);

        PageBean pb = new PageBean(pc, ps);
        //用户刷题信息
        List<ExercisesCustom> exers = exercisesService.findExersByUserPage(user, pb);

        //将信息保存model中
        model.addAttribute("examCount", userExamCount);
        model.addAttribute("doQsnCount", doQsnCount + doExerCount);
        model.addAttribute("wrongCount", wrongCount);
        model.addAttribute("exers", exers);
        model.addAttribute("pb", pb);
        return "user/center";
    }

    @RequestMapping("myExam.html")
    public String myExamHtml() throws Exception {
        return "user/myExam";
    }

    /**
     * 转向参加考试页面，根据考试id获取考试信息
     * 将考试信息保存到request域
     *
     * @param model
     * @param ksId
     * @return
     * @throws Exception
     */
    @RequestMapping("joinExam.html")
    public String joinExam(Model model,
                           @RequestParam(value = "kuId", required = true) Integer kuId) throws Exception {
        //获取用户考试信息
        ExamKsUserCustom ksUserCustom = ksUserService.getKsUserById(kuId);

        model.addAttribute("ksUser", ksUserCustom);
        model.addAttribute("ksDataCustom", ksUserCustom.getKsDataCustom());
        return "user/joinExam";
    }

    /**
     * 开始进行考试
     * 将用户考试状态修改为正在进行考试
     *
     * @param model
     * @param ksId
     * @return
     * @throws Exception
     */
    @RequestMapping("goExam.html")
    public String goExamHtml(Model model, HttpSession session,
                             @RequestParam(value = "kuId", required = true) final Integer kuId) throws Exception {
        if (session.getAttribute("stIds") == null || "".equals(session.getAttribute("stIds").toString())) {
            session.setAttribute("stIds", "[]");
        }
        if (session.getAttribute("gdStr") == null || "".equals(session.getAttribute("gdStr").toString())) {
            session.setAttribute("gdStr", "[]");
        }
        if (session.getAttribute("score") == null || "".equals(session.getAttribute("score").toString())) {
            session.setAttribute("score", 0);
        }

        //将用户考试状态修改为正在考试
//		ksUserService.updateGoExamStateById(kuId);

        //获取用户考试信息
        ExamKsUserCustom ksUserCustom = ksUserService.getKsUserById(kuId);

        //判断考试状态，只有状态为正在考试即state=3时才能进入
        if (ksUserCustom.getState() != 3) {
            //无法进行考试，重定向到joinExam页面
            return "redirect:joinExam.html?kuId=" + kuId;
        }

        //将用户考试id存入session
        session.setAttribute("ksUserId", ksUserCustom.getId());
        //将倒计时秒数存入model
        model.addAttribute("time", ksUserService.getCountDownTime(kuId));

        model.addAttribute("ksUser", ksUserCustom);
        model.addAttribute("ksData", ksUserCustom.getKsDataCustom());

        return "user/goExam";
    }

    /**
     * 分页、条件查询该用户的考试信息
     *
     * @param response
     * @param session
     * @param state
     * @param pageCode
     * @param rows
     * @throws Exception
     */
    @RequestMapping("queryMyExam.json")
    public void queryMyExam(HttpServletResponse response, HttpSession session,
                            @RequestParam(value = "state", defaultValue = "-1") Integer state,
                            @RequestParam(value = "page", defaultValue = "1") String pageCode,
                            @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //获取当前用户
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        //将该用户的所有考试设置为已读
        ksUserService.updateKsUserReadedByUserId(user.getId());


        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        ksUserCustom.setUserId(user.getId());
        ksUserCustom.setState(state);

        KsUserQueryVo ksUserQueryVo = new KsUserQueryVo();
        ksUserQueryVo.setKsUserCustom(ksUserCustom);

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        //组合条件查询
        List<ExamKsUserCustom> list = uService.findKsUserListByUidAndState(ksUserQueryVo, pageBean);
        //排序
        Collections.sort(list);

        //获取总记录数
        Integer totalRecored = pageBean.getTotalRecored();

        //转换为json串
        JSONArray arr = JSONArray.fromObject(list);
        JSONObject result = new JSONObject();
        result.put("total", totalRecored);
        result.put("rows", arr);
        //返回json数据
        ResponseUtils.write(response, result);
    }

    /**
     * 报名考试
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping("signUpExam.action")
    public void signUpExam(HttpServletResponse response,
                           @RequestParam(value = "id", required = true) Integer id) throws Exception {
        try {
            //报名
            ksUserService.updateKsUserSignUpById(id);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    /**
     * 判断当前是否可以 进行该考试
     *
     * @param response
     * @param kuId
     * @throws Exception
     */
    @RequestMapping("isCanBeginExam.action")
    public void beginExam(HttpServletResponse response,
                          @RequestParam(value = "kuId", required = true) Integer kuId) throws Exception {
        String examMsg = ksDataService.isCanBeginKs(kuId);
        if (examMsg != null && !"".equals(examMsg)) {
            //不能开始考试
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", examMsg));
            return;
        }
        //可以进行考试
        //修改状态为正在考试
        ksUserService.updateGoExamStateById(kuId);
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
    }

    /**
     * 获取试卷详细信息的json数据
     *
     * @param response
     * @param sjId
     * @throws Exception
     */
    @RequestMapping("showExam.json")
    public void showExam(HttpServletResponse response, HttpSession session,
                         @RequestParam(value = "sjId", required = true) Integer sjId) throws Exception {

        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        //根据试卷编号获取试卷的详细信息
        ExamSjDataCustom sjDataCustom = sjDataService.getSjDataById(sjId, user);

        //转换成json格式并返回
        ResponseUtils.write(response, JSONObject.fromObject(sjDataCustom));
    }

    /**
     * 获取未读信息数量
     *
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("getNotReadCount.json")
    public void getKsUserCountNotReadById(HttpServletResponse response, HttpSession session) throws Exception {

        //获取当前用户
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        //更新用户考试状态
        ksUserService.updateKsUserPublishResultByUserId(user.getId());

        //未读消息总数
        Integer notReadCountAll = 0;

        //获取未读考试信息数量
        Integer ksUserNotReadCount = ksUserService.getKsUserCountNotReadById(user.getId());
        //获取未读成绩信息数量
        Integer notReadResult = ksUserService.getNotReadResultByUserId(user.getId());


        notReadCountAll = ksUserNotReadCount + notReadResult;

        //返回josn数据
        JSONObject json = new JSONObject();
        json.put("notReadCountAll", notReadCountAll);
        json.put("ksUserNotReadCount", ksUserNotReadCount);
        json.put("notReadResult", notReadResult);
        ResponseUtils.write(response, json);
    }

    /**
     * 判断当前是否可以提交试卷
     *
     * @param response
     * @param ksId
     * @throws Exception
     */
    @RequestMapping("isCanSubmitExam.json")
    public void isCanSubmitExam(HttpServletResponse response, HttpSession session,
                                @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {

        //从session中获取用户考试编号
        Integer kuId = (Integer) session.getAttribute("ksUserId");

        Integer disableSubmit = ksDataService.isCanSubmitExam(ksId, kuId);


        if (disableSubmit < 0) {
            //可以交卷
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } else {
            //无法交卷
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", disableSubmit));
        }

    }

    /**
     * 保存或提交试卷
     *
     * @param response
     * @param session
     * @param sjId
     * @param kuId
     * @param gdStr
     * @param score
     * @param oprateFlg
     * @param passScore
     * @param uuid
     * @param stIdsStr
     * @throws Exception
     */
    @RequestMapping("submitExam.action")
    public void submitExam(HttpServletResponse response, HttpSession session,
                           @RequestParam(value = "sjId", required = true) Integer sjId,//试卷id
                           @RequestParam(value = "kuId", required = true) Integer kuId,//用户考试信息id
                           @RequestParam(value = "gdStr", required = true) String gdStr,//试题答案
                           @RequestParam(value = "score", required = true) BigDecimal score,//得分
                           @RequestParam(value = "oprateFlg", required = true) Integer oprateFlg,//保存或交卷标识
                           @RequestParam(value = "passScore", required = true) BigDecimal passScore,//及格分数
                           @RequestParam(value = "uuid", required = true) String uuid,//uuid
                           @RequestParam(value = "stids", required = true) String stIdsStr//错题id数组字符串
    ) throws Exception {

        //获取当前用户
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        //判断请求保存还是交卷
        if (oprateFlg == 0) {
            //保存
            //将答题信息保存到session中
            session.setAttribute("gdStr", gdStr);
            session.setAttribute("score", score);
            //将错题id保存到session中
            session.setAttribute("stIds", stIdsStr);
        } else {
            //交卷
            try {
                ksUserService.updateKsUserSubmit(kuId, gdStr, CommonUtils.JSONArrToIntegerArray(stIdsStr), score, uuid, user);
            } catch (CustomException e) {
                ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
                return;
            }

            //将session中的考试缓存信息删除
            session.removeAttribute("gdStr");
            session.removeAttribute("score");
            session.removeAttribute("stIds");
            session.removeAttribute("ksUserId");
        }

        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", score));
    }

    @RequestMapping("testConnection.json")
    public void testConnection(HttpServletResponse response) throws Exception {
        ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
    }
}
