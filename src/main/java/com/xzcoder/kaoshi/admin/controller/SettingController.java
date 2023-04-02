package com.xzcoder.kaoshi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xzcoder.kaoshi.admin.service.KsClassifyService;
import com.xzcoder.kaoshi.admin.service.SjClassifyService;
import com.xzcoder.kaoshi.admin.service.StClassifyService;
import com.xzcoder.kaoshi.admin.service.StKnowledgeService;
import com.xzcoder.kaoshi.admin.service.StLevelService;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;
import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;
import com.xzcoder.kaoshi.po.ExamStLevelCustom;

/**
 * SettingController
 * 基础数据信息中中心Controller
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/setting/")
public class SettingController {

	@Autowired
	private StClassifyService stClassifyService;
	@Autowired
	private StLevelService stLevelService;
	@Autowired
	private StKnowledgeService stKnowledgeService;
	@Autowired
	private SjClassifyService sjClassifyService;
	@Autowired
	private KsClassifyService ksClassifyService;

	//--------------------------  页面转发 --------------------------------------

	/**
	 * 转发到试题试卷考试分类中心
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("examClassifyManager.html")
	public String examClassifyManagerHtml() throws Exception {
		return "admin/examClassifyManager";
	}

	/**
	 * 转发到试题分类管理子页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stClassifyManager.html")
	public String stClassifyManagerHtml() throws Exception {
		return "admin/stClassifyManager";
	}

	/**
	 * 转发到试题等级分类管理子页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stLevelManager.html")
	public String stLevelManagerHtml() throws Exception {
		return "admin/stLevelManager";
	}

	/**
	 * 转发到试题知识点分类管理子页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stKnowledgeManager.html")
	public String stKnowledgeManagerHtml() throws Exception {
		return "admin/stKnowledgeManager";
	}

	/**
	 * 转发到试卷分类管理子页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sjClassifyManager.html")
	public String sjClassifyManagerHtml() throws Exception {
		return "admin/sjClassifyManager";
	}

	/**
	 * 转发到考试分类管理子页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ksClassifyManager.html")
	public String ksClassifyManagerHtml() throws Exception {
		return "admin/ksClassifyManager";
	}


	//--------------------------  获取所有信息的JSON数据  --------------------------------------


	/**
	 * 获取所有试题分组信息的json数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStClassify.json")
	public void getAllStClassify(HttpServletResponse response) throws Exception {

		try {
			//获取所有试题分组信息
			List<ExamStClassifyCustom> stcs = stClassifyService.getAllStClassify();
			JSONArray json = JSONArray.fromObject(stcs);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", json));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 获取所有试题等级分类信息的json数据
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStLevel.json")
	public void getAllStLevel(HttpServletResponse response) throws Exception {

		try {
			//获取所有的试题难度分组信息
			List<ExamStLevelCustom> stls = stLevelService.getAllStLevel();
			//将数据转换成json串
			JSONArray json = JSONArray.fromObject(stls);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", json));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 获取所有试题知识点分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllStKnowledge.json")
	public void getAllStKnowledge(HttpServletResponse response) throws Exception {

		try {
			//获取所有的试题知识点分组信息
			List<ExamStKnowledgeCustom> stks = stKnowledgeService.getAllStKnowledge();
			//将数据转换成json串
			JSONArray json = JSONArray.fromObject(stks);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", json));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 获取所有试卷分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllSjClassify.json")
	public void getAllSjClassify(HttpServletResponse response) throws Exception {

		try {
			//获取所有的试题知识点分组信息
			List<ExamSjClassifyCustom> sjcs = sjClassifyService.getAllSjClassify();
			//将数据转换成json串
			JSONArray json = JSONArray.fromObject(sjcs);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", json));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 获取所有考试分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getAllKsClassify.json")
	public void getAllKsClassify(HttpServletResponse response) throws Exception {

		try {
			//获取所有的试题知识点分组信息
			List<ExamKsClassifyCustom> kscs = ksClassifyService.getAllKsClassify();
			//将数据转换成json串
			JSONArray json = JSONArray.fromObject(kscs);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", json));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	//--------------------------  节点重命名  --------------------------------------

	/**
	 * 重命名试题分类节点
	 * @param response
	 * @param classifyId
	 * @param classifyName
	 * @throws Exception
	 */
	@RequestMapping("renameStClassify.action")
	public void renameStClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer classifyId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		try {
			//通过id重命名
			stClassifyService.renameStClassifyById(classifyId, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 重命名试题难度分类节点
	 * @param response
	 * @param levelId
	 * @param newName
	 * @throws Exception
	 */
	@RequestMapping("renameStLevel.action")
	public void renameStLevel(HttpServletResponse response,
				@RequestParam(value="levelId",required=true) Integer levelId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		try {
			//通过id重命名
			stLevelService.renameStLevelById(levelId, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 重命名试题知识点分类节点
	 * @param response
	 * @param levelId
	 * @param newName
	 * @throws Exception
	 */
	@RequestMapping("renameStKnowledge.action")
	public void renameStKnowledge(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer classifyId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		try {
			//通过id重命名
			stKnowledgeService.renameStKnowledge(classifyId, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 重命名试卷分类节点
	 * @param response
	 * @param levelId
	 * @param newName
	 * @throws Exception
	 */
	@RequestMapping("renameSjClassify.action")
	public void renameSjClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer classifyId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		try {
			//通过id重命名
			sjClassifyService.renameSjClassifyById(classifyId, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 重命名考试分类节点
	 * @param response
	 * @param classifyId
	 * @param classifyName
	 * @throws Exception
	 */
	@RequestMapping("renameKsClassify.action")
	public void renameKsClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer classifyId,
				@RequestParam(value="newName",required=true) String newName) throws Exception {

		try {
			//通过id重命名
			ksClassifyService.renameKsClassifyNameById(classifyId, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	//--------------------------  添加节点  --------------------------------------

	/**
	 * 添加试题分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addStClassify.action")
	public void addStClassify(HttpServletResponse response,
				@RequestParam(value="pid",defaultValue="0") Integer pid,
				@RequestParam(value="newName",defaultValue="new node") String newName) throws Exception {

		try {
			//添加试题分类
			Integer classifyId = stClassifyService.insertStClassify(pid, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", classifyId));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加试题难度分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addStLevel.action")
	public void addStLevel(HttpServletResponse response,
				@RequestParam(value="pid",defaultValue="0") Integer pid,
				@RequestParam(value="newName",defaultValue="new node") String newName) throws Exception {

		try {
			//添加试题难度分类
			Integer levelId = stLevelService.insertStLevel(pid, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", levelId));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加试题知识点分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addStKnowledge.action")
	public void addStKnowledge(HttpServletResponse response,
				@RequestParam(value="pid",defaultValue="0") Integer pid,
				@RequestParam(value="newName",defaultValue="new node") String newName) throws Exception {

		try {
			//添加试题难度分类
			Integer classifyId = stKnowledgeService.insertStKnowledge(pid, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", classifyId));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加试卷分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addSjClassify.action")
	public void addSjClassify(HttpServletResponse response,
				@RequestParam(value="pid",defaultValue="0") Integer pid,
				@RequestParam(value="newName",defaultValue="new node") String newName) throws Exception {

		try {
			//添加试题分类
			Integer classifyId = sjClassifyService.insertSjClassify(pid, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", classifyId));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 添加考试分类信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addKsClassify.action")
	public void addKsClassify(HttpServletResponse response,
				@RequestParam(value="pid",defaultValue="0") Integer pid,
				@RequestParam(value="newName",defaultValue="new node") String newName) throws Exception {

		try {
			//添加试题分类
			Integer classifyId = ksClassifyService.insertKsClassify(pid, newName);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", classifyId));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	//--------------------------  删除节点  --------------------------------------

	/**
	 * 根据id删除该试题分类以及所有子分类
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("deleteStClassify.action")
	public void deleteStClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer id) throws Exception {

		try {
			//删除试题分类信息
			stClassifyService.deleteStClassifyById(id);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id删除该试题难度分类以及所有子分类
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("deleteStLevel.action")
	public void deleteStLevel(HttpServletResponse response,
				@RequestParam(value="levelId",required=true) Integer id) throws Exception {

		try {
			//删除试题难度分类信息
			stLevelService.deleteStLevel(id);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id删除该试题知识点分类以及所有子分类
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("deleteStKnowledge.action")
	public void deleteStKnowledge(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer id) throws Exception {

		try {
			//删除试题难度分类信息
			stKnowledgeService.deleteStKnowledgeById(id);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id删除该试卷分类以及所有子分类
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("deleteSjClassify.action")
	public void deleteSjClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer id) throws Exception {

		try {
			//删除试题分类信息
			sjClassifyService.deleteSjClassifyById(id);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

	/**
	 * 根据id删除该试题分类以及所有子分类
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("deleteKsClassify.action")
	public void deleteKsClassify(HttpServletResponse response,
				@RequestParam(value="classifyId",required=true) Integer id) throws Exception {

		try {
			//删除试题分类信息
			ksClassifyService.deleteKsClassify(id);
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
		} catch (CustomException e) {
			ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
		}

	}

}
