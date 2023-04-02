package com.xzcoder.kaoshi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xzcoder.kaoshi.admin.service.ExercisesService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.UserExerData;
import com.xzcoder.kaoshi.vo.ExercisesQueryVo;

/**
 * ExerController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/exer")
public class ExerController {

    @Autowired
    private ExercisesService exercisesService;

    @RequestMapping("exerRecord.html")
    public String exerRecordHtml() throws Exception {
        return "admin/exerRecord";
    }

    @RequestMapping("queryUserExer.json")
    public void queryUserExer(HttpServletResponse response, ExercisesQueryVo vo,
                              @RequestParam(value = "page", defaultValue = "1") String pageCode,
                              @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        List<UserExerData> list = exercisesService.findUserExerDataByVo(vo, pageBean);

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

    @RequestMapping("getExercisesByUserId.json")
    public void getExercisesByUserId(HttpServletResponse response,
                                     @RequestParam(value = "userId", required = true) Integer userId,
                                     @RequestParam(value = "dateString", defaultValue = "") String dateString,
                                     @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                     @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        ExercisesQueryVo vo = new ExercisesQueryVo();
        if (!(dateString == null || "".equals(dateString))) {
            vo.setDayDateString(dateString);
        }
        vo.setUserId(userId);

        List<ExercisesCustom> list = exercisesService.findExercisesListByVo(vo, pageBean);

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


}
