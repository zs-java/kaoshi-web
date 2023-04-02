package com.xzcoder.kaoshi.admin.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.SjClassifyService;
import com.xzcoder.kaoshi.admin.service.SjDataService;
import com.xzcoder.kaoshi.admin.service.StMainService;
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
import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.SjDataQueryVo;
import com.xzcoder.kaoshi.vo.StMainQueryVo;

/**
 * SjController
 * 试卷管理Controller
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/shijuan")
public class SjController {

	@Autowired
	private SjDataService sjDataService;
	@Autowired
	private SjClassifyService sjClassifyService;
	@Autowired
	private StMainService stMainService;

	@RequestMapping("sjManager.html")
	public String sjManagerHtml() throws Exception {
		return "admin/sjManager";
	}
	@RequestMapping("editSj.html")
	public String editSjHtml(Model model,
			@RequestParam(value="pageType",defaultValue="add") String pageType,
			@RequestParam(value="sjId",defaultValue="-1") Integer sjId) throws Exception {
		model.addAttribute("pageType", pageType);
		model.addAttribute("sjId", sjId);
		return "admin/editSj";
	}
	@RequestMapping("previewSj.html")
	public String previewSjHtml(Model model,
			@RequestParam(value="sjId",required=true) Integer sjId) throws Exception {
		model.addAttribute("sjId", sjId);
		return "admin/previewSj";
	}

	/**
	 * 获取所有试卷分类树形json串
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllSjClassify.json")
	public void getAllSjClassify(HttpServletResponse response) throws Exception {

		List<ExamSjClassifyCustom> sjcs = sjClassifyService.getAllSjClassifyTree();
		ResponseUtils.write(response, JSONArray.fromObject(sjcs));

	}

	@RequestMapping("querySjList.json")
	public void querySjList(HttpServletResponse response, HttpSession session,
				SjDataQueryVo sjDataQueryVo,
				@RequestParam(value="page", defaultValue="1") String pageCode,
				@RequestParam(value="rows", defaultValue="15") String rows) throws Exception {
		SysUserCustom user = (SysUserCustom) session.getAttribute("user");

		//将分页信息封装到PageBean中
		PageBean pageBean = new PageBean();
		pageBean.setPageCode(Integer.parseInt(pageCode));
		pageBean.setPageSize(Integer.parseInt(rows));

		//组合条件查询
		List<ExamSjDataCustom> list = sjDataService.findSjDataListByVo(sjDataQueryVo, pageBean, user);

		//获取总记录数
		Integer totalRecored = pageBean.getTotalRecored();

		//转换为json串
		JSONArray arr = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("total", totalRecored);
		result.put("rows", arr);

		ResponseUtils.write(response, result);
	}

	/**
	 * 组合条件分页查询试卷信息
	 * @param response
	 * @param stMainQueryVo
	 * @param ids
	 * @param pageCode
	 * @param rows
	 * @throws Exception
	 */
	@RequestMapping("queryStList.json")
	public void queryStList(HttpServletResponse response, HttpSession session,
			StMainQueryVo stMainQueryVo,//封装了试题的组合查询条件
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="page", defaultValue="1") String pageCode,
			@RequestParam(value="rows", defaultValue="15") String rows) throws Exception {
		SysUserCustom user = (SysUserCustom) session.getAttribute("user");

		//将分页信息封装到PageBean中
		PageBean pageBean = new PageBean();
		pageBean.setPageCode(Integer.parseInt(pageCode));
		pageBean.setPageSize(Integer.parseInt(rows));

		//将id数组设置到vo
		Integer[] questionIds = CommonUtils.StringArrToIntegerArr(ids);
		stMainQueryVo.setQuestionIds(questionIds);

		//组合查询试题信息
		List<ExamStMainCustom> stList = stMainService.findStUseableByVo(stMainQueryVo, pageBean, user);

		//获取中记录数
		Integer totalRecored = pageBean.getTotalRecored();

		//转换为json串
		JSONArray arr = JSONArray.fromObject(stList);
		JSONObject result = new JSONObject();
		result.put("total", totalRecored);
		result.put("rows", arr);

		ResponseUtils.write(response, result);
	}

	@RequestMapping("queryYxStList.json")
	public void queryStList(HttpServletResponse response,
			@RequestParam(value="ids[]", defaultValue="") Integer[] ids) throws Exception {

		StMainQueryVo vo = new StMainQueryVo();
		vo.setQuestionIds(ids);

		//组合查询试题信息
		List<ExamStMainCustom> stList = stMainService.findStListByIds(vo);

		//转换为json串
		JSONArray arr = JSONArray.fromObject(stList);
		JSONObject result = new JSONObject();
		result.put("total", stList.size());
		result.put("rows", arr);

		ResponseUtils.write(response, result);
	}



	/**
	 * 保存试卷信息
	 * @param sjId 根据试卷编号是否为空判断增加试卷或更新试卷
	 * @param response
	 * @param title
	 * @param sjClassifyId
	 * @param des
	 * @param count
	 * @param totalScore
	 * @param stInfo
	 * @throws Exception
	 */
	@RequestMapping("saveShijuan.action")
	public void saveShijuan(HttpServletResponse response, HttpSession session,
			@RequestParam(value="sjId",required=false) Integer sjId,//试卷id
			@RequestParam(value="title",required=true) String title,//试卷名称
			@RequestParam(value="sjClassifyId",required=true) Integer sjClassifyId,//试卷分类编号
			@RequestParam(value="des",required=false) String des,//试卷描述，可为空
			@RequestParam(value="totalshiti",required=true) Integer count,//试卷总题目数
			@RequestParam(value="totalscore",required=true) BigDecimal totalScore,//试卷总分数
			@RequestParam(value="stinfo",required=true) String stInfo,//试卷对应试题信息，json格式
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");

			/*
			 * 试卷id为空或小于等于0
			 * 就添加试卷，否则更新原先存在的试卷信息
			 */
			Integer id = null;
			if(sjId != null && sjId >= 0) {//更新试卷
				sjDataService.updateSjData(sjId, title, sjClassifyId, des, count, totalScore, stInfo, visable, user);
				id = sjId;
			} else //添加试卷
				id = sjDataService.insertSjData(title, sjClassifyId, des, count, totalScore, stInfo, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", id));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id获取试卷的详细信息
	 * @param response
	 * @param sjId
	 * @throws Exception
	 */
	@RequestMapping("getSjDataById.json")
	public void getSjDataById(HttpServletResponse response, HttpSession session,
			@RequestParam(value="sjId",required=true) Integer sjId) throws Exception {

		SysUserCustom user = (SysUserCustom) session.getAttribute("user");

		//获取试卷信息
		ExamSjDataCustom sjDataCustom = sjDataService.getSjDataById(sjId, user);
		//转换成json格式并返回
		ResponseUtils.write(response, JSONObject.fromObject(sjDataCustom));

	}

	/**
	 * 根据id删除试卷（逻辑删除）
	 * @param response
	 * @param session
	 * @param sjId
	 * @throws Exception
	 */
	@RequestMapping("deleteSjById.action")
	public void deleteSjById(HttpServletResponse response, HttpSession session,
			@RequestParam(value="sjId",required=true) Integer sjId) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			sjDataService.deleteSjDataById(sjId, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 批量删除试卷
	 * @param response
	 * @param session
	 * @param sjIds
	 * @throws Exception
	 */
	@RequestMapping("deleteSjList.action")
	public void deleteSjList(HttpServletResponse response, HttpSession session,
			@RequestParam("ids[]") Integer[] sjIds) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			sjDataService.deleteSjDataList(sjIds, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

}
