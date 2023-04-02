package com.xzcoder.kaoshi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.admin.service.StClassifyService;
import com.xzcoder.kaoshi.admin.service.StKnowledgeService;
import com.xzcoder.kaoshi.admin.service.StLevelService;
import com.xzcoder.kaoshi.admin.service.StMainService;
import com.xzcoder.kaoshi.admin.service.StTypeService;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamStBianchengCustom;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;
import com.xzcoder.kaoshi.po.ExamStLevelCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.ExamStTypeCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.StMainQueryVo;

/**
 * StController
 * 试题管理Controller
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/shiti/")
public class StController {

	@Autowired
	private StTypeService stTypeService;
	@Autowired
	private StClassifyService stClassifyService;
	@Autowired
	private StLevelService stLevelService;
	@Autowired
	private StKnowledgeService stKnowledgeService;
	@Autowired
	private StMainService stMainService;

	/**
	 * 转发到试题管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stManager.html")
	public String stManagerHtml() throws Exception {
		return "admin/stManager";
	}
	@RequestMapping(value="editSt.html", method={RequestMethod.GET, RequestMethod.POST})
	public String editStHtml(Model model,
					@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
					@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId,
					@ModelAttribute("typeName") @RequestParam(value="typeName",defaultValue="-1") String typeName) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", new String(typeName.getBytes("ISO8859-1"), "UTF-8"));
		return "admin/editSt";
	}
	@RequestMapping("editStDanxuan.html")
	public String editStDanxuanHtml(Model model,
			@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
			@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", "单选题");
		return "admin/editStDanxuan";
	}
	@RequestMapping("editStDuoxuan.html")
	public String editStDuoxuanHtml(Model model,
			@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
			@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", "多选题");
		return "admin/editStDuoxuan";
	}
	@RequestMapping("editStPanduan.html")
	public String editStPanduanHtml(Model model,
			@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
			@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", "判断题");
		return "admin/editStPanduan";
	}
	@RequestMapping("editStTiankong.html")
	public String editStTiankongHtml(Model model,
			@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
			@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", "填空题");
		return "admin/editStTiankong";
	}
	@RequestMapping("editStBiancheng.html")
	public String editStBianchengHtml(Model model,
			@ModelAttribute("type") @RequestParam(value="type",defaultValue="add") String type,
			@ModelAttribute("questionId") @RequestParam(value="questionId",defaultValue="-1") Integer questionId) throws Exception {
		model.addAttribute("type", type);
		model.addAttribute("questionId", questionId);
		//处理GET请求乱码问题
		model.addAttribute("typeName", "编程题");
		return "admin/editStBiancheng";
	}

	/**
	 * 获取所有试题类别的json串
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStType.json")
	public void getAllStType(HttpServletResponse response) throws Exception {

		List<ExamStTypeCustom> stts = stTypeService.getAllStTypes();
		JSONArray json = JSONArray.fromObject(stts);
		ResponseUtils.write(response, json);

	}

	/**
	 * 获取所有试题分类树形json串
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStClassifyTree.json")
	public void getAllStClassifyTree(HttpServletResponse response) throws Exception {

		List<ExamStClassifyCustom> stClassifyTree = stClassifyService.getAllStClassifyTree();
		ResponseUtils.write(response, JSONConvertUtils.stClassifyTreeToJSON(stClassifyTree));

	}

	/**
	 * 获取所有试题难度分类的树形json串
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStLevelTree.json")
	public void getAllStLevelTree(HttpServletResponse response) throws Exception {

		List<ExamStLevelCustom> stts = stLevelService.getAllStLevelTree();
		ResponseUtils.write(response, JSONArray.fromObject(stts));

	}

	/**
	 * 获取所有试题知识点分类的树形json串
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStKnowledgeTree.json")
	public void getAllStKnowledgeTree(HttpServletResponse response) throws Exception {

		List<ExamStKnowledgeCustom> stks = stKnowledgeService.getAllStKnowledgeTree();
		ResponseUtils.write(response, JSONArray.fromObject(stks));

	}

	/**
	 * 组合条件分页查询试题信息
	 * 返回指定格式的json串
	 * @param response
	 * @param stMainQueryVo
	 * @param pageCode
	 * @param rows
	 * @throws Exception
	 */
	@RequestMapping("queryStMainList.json")
	public void queryStMain(HttpServletResponse response, HttpSession session,
				StMainQueryVo stMainQueryVo,//封装了试题的组合查询条件
				@RequestParam(value="page", defaultValue="1") String pageCode,
				@RequestParam(value="rows", defaultValue="15") String rows) throws Exception {
		SysUserCustom user = (SysUserCustom) session.getAttribute("user");

		//将分页信息封装到PageBean中
		PageBean pageBean = new PageBean();
		pageBean.setPageCode(Integer.parseInt(pageCode));
		pageBean.setPageSize(Integer.parseInt(rows));

		//组合查询试题信息
		List<ExamStMainCustom> stList = stMainService.findStListByStQueryVo(stMainQueryVo, pageBean, user);

		//获取中记录数
		Integer totalRecored = pageBean.getTotalRecored();

		//转换为json串
		JSONArray arr = JSONArray.fromObject(stList);
		JSONObject result = new JSONObject();
		result.put("total", totalRecored);
		result.put("rows", arr);

		ResponseUtils.write(response, result);
	}

	/**
	 * 添加单选试题信息
	 * @param response
	 * @param session
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("saveStDanxuan.action")
	public void saveStDanxuan(HttpServletResponse response, HttpSession session,
				@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
				@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
				@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
				@RequestParam(value="title",required=true) String title,			//试题题目
				@RequestParam(value="titleText",required=true) String titleText,			//试题题目纯文本
				@RequestParam(value="options",required=true) String options,		//试题选项的json串
				@RequestParam(value="rightKey",required=true) Integer rightKey,		//正确选项
				@RequestParam(value="detail",required=true) String detail,			//试题解析
				@RequestParam(value="visable",defaultValue="1") Integer visable
				) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//添加试题
			stMainService.insertDanxuanSt(classifyId, levelId, knowledgeId, title, titleText, options, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("saveStBiancheng.action")
	public void saveStBiancheng(HttpServletResponse response, HttpSession session,
				@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
				@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
				@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
				@RequestParam(value="title",required=true) String title,			//试题题目
				@RequestParam(value="titleText",required=true) String titleText,			//试题题目纯文本
//				@RequestParam(value="options",required=true) String options,		//试题选项的json串
//				@RequestParam(value="rightKey",required=true) Integer rightKey,		//正确选项
				@RequestParam(value="detail",required=true) String detail,			//试题解析
				@RequestParam(value="visable",defaultValue="1") Integer visable
				) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//添加试题
			stMainService.insertBianchengSt(classifyId, levelId, knowledgeId, title, titleText, detail, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加判断题
	 * @param response
	 * @param session
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("saveStPanduan.action")
	public void saveStPanduan(HttpServletResponse response, HttpSession session,
				@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
				@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
				@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
				@RequestParam(value="title",required=true) String title,			//试题题目
				@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
				@RequestParam(value="rightKey",required=true) Integer rightKey,		//是否正确
				@RequestParam(value="detail",required=true) String detail,			//试题解析
				@RequestParam(value="visable",defaultValue="1") Integer visable
				) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//添加试题
			stMainService.insertPanduanSt(classifyId, levelId, knowledgeId, title, titleText, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}


	/**
	 * 根据编号获取试题信息json串
	 * @param response
	 * @param questionId
	 * @throws Exception
	 */
	@RequestMapping("getStMainById.action")
	public void getStMainById(HttpServletResponse response,
				@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStMainCustom stMainCustom = stMainService.getStMainById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stMainCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id获取单选题试题信息
	 * @param response
	 * @param questionId
	 * @throws Exception
	 */
	@RequestMapping("getStDanxuanById.action")
	public void getStDanxuanById(HttpServletResponse response,
				@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStDanxuanCustom stDanxuanCustom = stMainService.getStDanxuanById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stDanxuanCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("getStBianchengById.action")
	public void getStBianchengById(HttpServletResponse response,
				@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStBianchengCustom stBianchengCustom = stMainService.getStBianchengById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stBianchengCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id获取判断题信息
	 * @param response
	 * @param questionId
	 * @throws Exception
	 */
	@RequestMapping("getStPanduanById.action")
	public void getStPanduanById(HttpServletResponse response,
				@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStPanduanCustom stPanduanCustom = stMainService.getStPanduanById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stPanduanCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}



	/**
	 * 更新单选题信息
	 * @param response
	 * @param session
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("updateStDanxuan.action")
	public void updateStDanxuan(HttpServletResponse response, HttpSession session,
			@RequestParam(value="questionId",required=true) Integer questionId,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
			@RequestParam(value="options",required=true) String options,		//试题选项的json串
			@RequestParam(value="rightKey",required=true) Integer rightKey,		//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//修改试题
			stMainService.updateStDanxuanById(questionId, classifyId, levelId, knowledgeId, title, titleText, options, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 更新多选题信息
	 * @param response
	 * @param session
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("updateStDuoxuan.action")
	public void updateStDuoxuan(HttpServletResponse response, HttpSession session,
			@RequestParam(value="questionId",required=true) Integer questionId,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
			@RequestParam(value="options",required=true) String options,		//试题选项的json串
			@RequestParam(value="rightKey",required=true) Integer[] rightKey,	//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//修改试题
			stMainService.updateStDuoxuanById(questionId, classifyId, levelId, knowledgeId, title, titleText, options, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("updateStBiancheng.action")
	public void updateStBiancheng(HttpServletResponse response, HttpSession session,
			@RequestParam(value="questionId",required=true) Integer questionId,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//修改试题
			stMainService.updateStBianchengById(questionId, classifyId, levelId, knowledgeId, title, titleText, detail, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 更新判断题信息
	 * @param response
	 * @param session
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("updateStPanduan.action")
	public void updateStPanduan(HttpServletResponse response, HttpSession session,
			@RequestParam(value="questionId",required=true) Integer questionId,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
			@RequestParam(value="rightKey",required=true) Integer rightKey,	//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//修改试题
			stMainService.updateStPanduanById(questionId, classifyId, levelId, knowledgeId, title, titleText, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 更新填空题
	 * @param response
	 * @param session
	 * @param questionId
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("updateStTiankong.action")
	public void updateStTiankong(HttpServletResponse response, HttpSession session,
			@RequestParam(value="questionId",required=true) Integer questionId,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,	//试题题目纯文本
			@RequestParam(value="rightKey",required=true) String rightKey,	//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//修改试题
			stMainService.updateStTiankongById(questionId, classifyId, levelId, knowledgeId, title, CommonUtils.delHTMLTag(titleText), detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加多选题试题信息
	 * @param response
	 * @param session
	 * @param classifyId
	 * @param levelId
	 * @param knowledgeId
	 * @param title
	 * @param titleText
	 * @param options
	 * @param rightKey
	 * @param detail
	 * @throws Exception
	 */
	@RequestMapping("saveStDuoxuan.action")
	public void saveStDuoxuan(HttpServletResponse response, HttpSession session,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,			//试题题目纯文本
			@RequestParam(value="options",required=true) String options,		//试题选项的json串
			@RequestParam(value="rightKey",required=true) Integer[] rightKey,		//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//添加试题
			stMainService.insertDuoxuanSt(classifyId, levelId, knowledgeId, title, titleText, options, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("saveStTiankong.action")
	public void saveStTiankong(HttpServletResponse response, HttpSession session,
			@RequestParam(value="classifyId",required=true) Integer classifyId,	//试题分类编号
			@RequestParam(value="levelId",required=true) Integer levelId,		//试题难度编号
			@RequestParam(value="knowledgeId",required=true) Integer knowledgeId,//试题知识点编号
			@RequestParam(value="title",required=true) String title,			//试题题目
			@RequestParam(value="titleText",required=true) String titleText,			//试题题目纯文本
			@RequestParam(value="rightKey",required=true) String rightKey,		//正确选项
			@RequestParam(value="detail",required=true) String detail,			//试题解析
			@RequestParam(value="visable",defaultValue="1") Integer visable
			) throws Exception {

		try {
			//取出富文本中的html标签
			titleText = CommonUtils.delHTMLTag(titleText);
			//获取当前用户信息
			SysUserCustom user = (SysUserCustom) session.getAttribute("user");
			//添加试题
			stMainService.insertTiankongSt(classifyId, levelId, knowledgeId, title, titleText, detail, rightKey, visable, user);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id获取
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getStDuoxuanById.action")
	public void  getStDuoxuanById(HttpServletResponse response,
			@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStDuoxuanCustom stDuoxuanCustom = stMainService.getStDuoxuanById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stDuoxuanCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id获取填空题
	 * @param response
	 * @param questionId
	 * @throws Exception
	 */
	@RequestMapping("getStTiankongById.action")
	public void getStTiankongById(HttpServletResponse response,
			@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			ExamStTiankongCustom stTiankongCustom = stMainService.getStTiankongById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", JSONObject.fromObject(stTiankongCustom)));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id删除试题信息
	 * @param response
	 * @param questionId
	 * @throws Exception
	 */
	@RequestMapping("deleteSt.action")
	public void deleteSt(HttpServletResponse response,
			@RequestParam(value="questionId",required=true) Integer questionId) throws Exception {

		try {
			stMainService.deleteStById(questionId);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 批量删除试题
	 * @param response
	 * @param ids
	 * @throws Exception
	 */
	@RequestMapping("deleteStList.action")
	public void deleteStList(HttpServletResponse response,
			@RequestParam(value="ids[]",required=true) Integer[] ids) throws Exception {

		try {
			stMainService.deleteStList(ids);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	@RequestMapping("getStById.json")
	public void getStById(HttpServletResponse response,
			@RequestParam(value="qsnId",required=true) Integer qsnId) throws Exception {
		JSONObject json = stMainService.getStJsonById(qsnId);
		ResponseUtils.write(response, json);
	}

}
