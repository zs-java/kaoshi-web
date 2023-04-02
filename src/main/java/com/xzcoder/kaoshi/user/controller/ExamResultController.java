package com.xzcoder.kaoshi.user.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.KsUserService;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * ExamResultController
 * 用户成绩Controller
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/user")
public class ExamResultController {

    @Autowired
    private KsUserService ksUserService;

    /**
     * 转发到我的成绩页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("myResult.html")
    public String myResultHtml(HttpSession session) throws Exception {
        /*
         * 1、更新用户考试状态
         * 2、将该用户的所有成绩设置为已读
         */
        //获取用户信息
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");
        //更新用户考试状态
//		ksUserService.updateKsUserPublishResultByUserId(user.getId());
        //将该用户的所有成绩设置为已读
        ksUserService.updateResultReadedById(user.getId());
        return "user/myResult";
    }

    /**
     * 转发到成绩查询页面
     *
     * @param model
     * @param kuId
     * @return
     * @throws Exception
     */
    @RequestMapping("previewResult.html")
    public String previewResultHtml(Model model,
                                    @RequestParam(value = "kuId", required = true) Integer kuId) throws Exception {
        model.addAttribute("kuId", kuId);
        return "user/previewResult";
    }

    @RequestMapping("previewMyExam.html")
    public String previewMyExamHtml(Model model,
                                    @RequestParam(value = "kuId", required = true) Integer kuId) throws Exception {
        model.addAttribute("kuId", kuId);
        return "user/previewMyExam";
    }

    /**
     * 分页、组合条件查询用户的成绩信息集合
     *
     * @param response
     * @param session
     * @param pageCode
     * @param rows
     * @throws Exception
     */
    @RequestMapping("queryMyResult.json")
    public void queryMyResult(HttpServletResponse response, HttpSession session,
                              @RequestParam(value = "page", defaultValue = "1") String pageCode,
                              @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //获取当前用户
        SysUserCustom user = (SysUserCustom) session.getAttribute("user");

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        //将成绩查询条件封装为组合查询包装类对象
        KsUserQueryVo ksUserQueryVo = new KsUserQueryVo();
        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        //设置用户编号
        ksUserCustom.setUserId(user.getId());
        ksUserQueryVo.setKsUserCustom(ksUserCustom);

        //查询
        List<ExamKsUserCustom> list = ksUserService.findResultListByVo(ksUserQueryVo, pageBean);
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
     * 根据id查询用户考试详细信息，json
     *
     * @param response
     * @param kuId
     * @return
     * @throws Exception
     */
    @RequestMapping("getKsUserInfoById.json")
    public @ResponseBody ExamKsUserCustom getKsUserInfoById(HttpServletResponse response,
                                                            @RequestParam(value = "kuId", required = true) Integer kuId) throws Exception {
        return ksUserService.getKsUserById(kuId);
    }

}
