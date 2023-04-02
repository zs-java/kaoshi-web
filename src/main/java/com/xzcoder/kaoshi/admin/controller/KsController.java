package com.xzcoder.kaoshi.admin.controller;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xzcoder.kaoshi.admin.service.KsClassifyService;
import com.xzcoder.kaoshi.admin.service.KsDataService;
import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.configuration.KaoshiProperties;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xzcoder.kaoshi.common.po.ExamStResult;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsMonitor;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.KsUserService;
import com.xzcoder.kaoshi.vo.KsDataQueryVo;
import com.xzcoder.kaoshi.vo.KsMonitorQueryVo;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * KsController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/admin/exam")
public class KsController {

    @Autowired
    private KsClassifyService ksClassifyService;
    @Autowired
    private KsDataService ksDataService;
    @Autowired
    private KsUserService ksUserService;
    @Autowired
    private KaoshiProperties kaoshiProperties;

    /**
     * 转向考试管理页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("examManager.html")
    public String ksManagerHtml() throws Exception {
        return "admin/examManager";
    }

    /**
     * 转向添加考试页面
     *
     * @param model
     * @param pageType 页面类型（"add":添加考试，"edit":编辑考试）
     * @param ksId     编辑考试时须传入ksId
     * @return
     * @throws Exception
     */
    @RequestMapping("addExam.html")
    public String addExamHtml(Model model,
                              @RequestParam(value = "sjId", defaultValue = "-1") Integer sjId) throws Exception {
        model.addAttribute("sjId", sjId);
        return "admin/addExam";
    }

    /**
     * 转向编辑考试页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("editExam.html")
    public String editExamHtml(Model model,
                               @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {
        model.addAttribute("ksId", ksId);
        return "admin/editExam";
    }

    /**
     * 转向考试报名管理页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("signupManager.html")
    public String singupManagerHtml() throws Exception {
        return "admin/signupManager";
    }

    /**
     * 转向考试监控页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("examMonitor.html")
    public String examControlHtml() throws Exception {
        return "admin/examMonitor";
    }

    /**
     * 获取所有考试分类信息的树形json串
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("getAllKsClassifyTree.json")
    public @ResponseBody List<ExamKsClassifyCustom> getAllKsClassifyTree() throws Exception {
        return ksClassifyService.getAllKsClassifyTree();
    }

    /**
     * 组合条件分页查询考试信息
     *
     * @param response
     * @param ksDataQueryVo
     * @param pageCode
     * @param rows
     * @throws Exception
     */
    @RequestMapping("queryKsDataList.json")
    public void queryKsDataList(HttpServletResponse response,
                                KsDataQueryVo ksDataQueryVo,
                                @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        //组合条件查询
        List<ExamKsDataCustom> ksList = ksDataService.findKsDataByVo(ksDataQueryVo, pageBean);

        //获取总记录数
        Integer totalRecored = pageBean.getTotalRecored();

        //转换为json串
        JSONArray arr = JSONArray.fromObject(ksList);
        JSONObject result = new JSONObject();
        result.put("total", totalRecored);
        result.put("rows", arr);
        //返回json数据
        ResponseUtils.write(response, result);
    }

    /**
     * 上传考试封面图片
     *
     * @param response
     * @param ksPic
     * @throws Exception
     */
    @RequestMapping("uploadKsPic.action")
    public void uploadKsPic(HttpServletResponse response,
                            @RequestParam(value = "ksPic", required = false) MultipartFile ksPic) throws Exception {

        //如果上传的文件为空，返回nofile
        if (ksPic == null) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("nofile", null));
            return;
        }

        //图片保存的基本路径
        String basePath = kaoshiProperties.getPicPath();

        //根据当天日期进行目录打散
        Date nowDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datePath = "kspic/" + df.format(nowDate) + "/";
        File dir = new File(basePath + datePath);
        //如果该目录不存在就创建这个目录
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //图片原名称
        String filename = ksPic.getOriginalFilename();
        if (filename != null && filename.length() > 0) {
            //通过uuid来保证图片上传的唯一性
            String newFilename = CommonUtils.uuid() + filename.substring(filename.lastIndexOf("."));

            File newFile = new File(basePath + datePath + newFilename);
            newFile.createNewFile();

            //将图片写入磁盘
            ksPic.transferTo(newFile);

            //保存成功后返回状态信息和pic路径
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", datePath + newFilename));
        } else {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", null));
        }
    }

    /**
     * 添加试卷信息
     *
     * @param response
     * @param session
     * @param ksDataCustom
     * @param userIds
     * @throws Exception
     */
    @RequestMapping("addExam.action")
    public void addExam(HttpServletResponse response, HttpSession session,
                        ExamKsDataCustom ksDataCustom,
                        @RequestParam(value = "userIds[]", required = true) Integer[] userIds) throws Exception {

        try {
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            Integer ksId = ksDataService.insertKsData(ksDataCustom, user, userIds);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", ksId));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }

    }

    /**
     * 根据考试Id获取所有考试信息
     *
     * @param ksId
     * @return
     * @throws Exception
     */
    @RequestMapping("getKsDataById.json")
    public @ResponseBody ExamKsDataCustom getKsDataById(
            @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {
        ExamKsDataCustom ksDataCustom = ksDataService.getKsDataById(ksId);
        return ksDataCustom;
    }

    /**
     * 更新考试信息
     *
     * @param response
     * @param session
     * @param ksDataCustom
     * @throws Exception
     */
    @RequestMapping("updateExamData")
    public void updateExamData(HttpServletResponse response, HttpSession session,
                               ExamKsDataCustom ksDataCustom) throws Exception {
        try {
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            ksDataService.updateKsData(ksDataCustom, user);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    /**
     * 根据id删除考试
     *
     * @param response
     * @param session
     * @param ksId
     * @throws Exception
     */
    @RequestMapping("deleteExamById.action")
    public void deleteExamById(HttpServletResponse response, HttpSession session,
                               @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {
        try {
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            ksDataService.deleteKsDataById(ksId, user);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    /**
     * 分页、条件查询正在报名中的用户考试信息
     *
     * @param response
     * @param ksUserQueryVo
     * @param pageCode
     * @param rows
     * @throws Exception
     */
    @RequestMapping("querySignupingKsUser.json")
    public void querySignupingKsUser(HttpServletResponse response,
                                     KsUserQueryVo ksUserQueryVo,
                                     @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                     @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        //组合条件查询
        List<ExamKsUserCustom> list = ksUserService.findSignupingKsUserListByVo(ksUserQueryVo, pageBean);

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
     * 对用户考试报名进行审核通过
     *
     * @param response
     * @param session
     * @param id
     * @throws Exception
     */
    @RequestMapping("signupKsUserById.action")
    public void signupKsUserById(HttpServletResponse response, HttpSession session,
                                 @RequestParam(value = "id", required = true) Integer id) throws Exception {
        try {
            //获取当前用户信息
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            //通过报名
            ksUserService.updateSignupTrueKsUserById(id, user);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    /**
     * 获取所有有效考试的编号和名称的集合，json
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("getExamList.json")
    public @ResponseBody List<ExamKsMonitor> getExamList() throws Exception {
        //查询所有有效考试的编号和名称的集合，使用ResponesBody返回json数据
        return ksDataService.getAllExamIdAndNameList();
    }

    /**
     * 分页、组合条件考试监控信息集合，json
     *
     * @param response
     * @param ksMonitorQueryVo
     * @param pageCode
     * @param rows
     * @throws Exception
     */
    @RequestMapping("queryExamMonitorList.json")
    public void queryExamMonitorList(HttpServletResponse response,
                                     KsMonitorQueryVo ksMonitorQueryVo,
                                     @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                     @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        //组合条件查询
        List<ExamKsMonitor> list = ksDataService.findKsMonitorByVo(ksMonitorQueryVo, pageBean);

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
     * 转向考试结果统计页面
     *
     * @return
     */
    @RequestMapping("result.html")
    public String resultHtml(@RequestParam(value = "examId", defaultValue = "-1") Integer examId) throws Exception {
        if (examId == -1)
            return "redirect:chooseExam.html";
        return "admin/examResult";
    }

    @RequestMapping("chooseExam.html")
    public String chooseExamHtml(Model model) throws Exception {
        List<ExamKsMonitor> list = ksDataService.getAllExamIdAndNameList();
        model.addAttribute("exams", list);
        return "admin/chooseExam";
    }

    @RequestMapping("queryKsUserByKsId.json")
    public void queryKsUserByKsId(HttpServletResponse response, KsUserQueryVo vo,
                                  @RequestParam(value = "ksId", required = true) Integer ksId,
                                  @RequestParam(value = "page", defaultValue = "1") String pageCode,
                                  @RequestParam(value = "rows", defaultValue = "15") String rows) throws Exception {

        //将分页信息封装到PageBean中
        PageBean pageBean = new PageBean();
        pageBean.setPageCode(Integer.parseInt(pageCode));
        pageBean.setPageSize(Integer.parseInt(rows));

        List<ExamKsUserCustom> list = ksUserService.findKsUserByKsId(vo, pageBean);

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

    @RequestMapping("exportExamResult.action")
    public void exportExamResult(HttpServletResponse response, KsUserQueryVo vo,
                                 @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {
        ExamKsDataCustom ksData = ksDataService.getKsDataById(ksId);
        List<ExamKsUserCustom> list = ksUserService.findKsUserByKsId(vo, new PageBean(1, 99999));

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet(ksData.getName());
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue(ksData.getName() + "考试成绩一览表");

        HSSFCellStyle style = wb.createCellStyle(); // 样式对象   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平   
        /**字体begin*/
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short) 14);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体增粗
        // 把字体应用到当前的样式
        style.setFont(font);
        cell.setCellStyle(style);

        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("用户组");
        row2.createCell(2).setCellValue("得分");
        row2.createCell(3).setCellValue("是否及格");

        int rowIndex = 2;

        BigDecimal passScore = BigDecimal.valueOf(ksData.getPassScore());
        for (ExamKsUserCustom ku : list) {
            HSSFRow row3 = sheet.createRow(rowIndex);
            row3.createCell(0).setCellValue(ku.getUserCustom().getUsername());
            row3.createCell(1).setCellValue(ku.getUserCustom().getGroupCustom().getGroupName());
            BigDecimal score = ku.getScore() == null ? BigDecimal.valueOf(0) : ku.getScore();
            row3.createCell(2).setCellValue(score.toString());
            row3.createCell(3).setCellValue(score.compareTo(passScore) >= 0 ? "及格" : "不及格");
            rowIndex++;
        }


        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=" + CommonUtils.toUtf8String(ksData.getName()) + ".xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }

    @RequestMapping("isCanAddUser.action")
    public void isCanAddUser(HttpServletResponse response,
                             @RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {

        try {
            ksDataService.isCanAddUser(ksId);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }

    }

    @RequestMapping("addUserToExam.action")
    public void addUserToExam(HttpServletResponse response, HttpSession session,
                              @RequestParam(value = "ksId", required = true) Integer ksId,
                              @RequestParam(value = "ids[]", required = true) Integer[] ids) throws Exception {
        try {
            SysUserCustom user = (SysUserCustom) session.getAttribute("user");
            ksUserService.addUsersToExam(ksId, ids, user);
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("success", null));
        } catch (CustomException e) {
            ResponseUtils.write(response, JSONConvertUtils.getStatusAndInfoJson("error", e.getMessage()));
        }
    }

    @RequestMapping("/getKsUserStResult.json")
    @ResponseBody
    public List<ExamStResult> getKsUserStResult(@RequestParam(value = "ksId", required = true) Integer ksId,
                                                @RequestParam(value = "groupId", defaultValue = "-1") Integer groupId) throws Exception {
        return ksDataService.getKsErrorResult(ksId, groupId);
    }

    @RequestMapping("/getKsUserGroupsByKsId.json")
    @ResponseBody
    public List<SysGroupCustom> getKsUserGroupsByKsId(@RequestParam(value = "ksId", required = true) Integer ksId) throws Exception {
        return ksDataService.getKsUserGroupsByKsId(ksId);
    }

    @RequestMapping("/downErrorResultExcel.action")
    public void downErrorResultExcel(HttpServletResponse response,
                                     @RequestParam(value = "ksId", required = true) Integer ksId,
                                     @RequestParam(value = "groupId", defaultValue = "-1") Integer groupId) throws Exception {
        ExamKsDataCustom ksData = ksDataService.getKsDataById(ksId);
        List<ExamStResult> list = ksDataService.getKsErrorResult(ksId, groupId);

//		ExportExcelUtils.writeExcelFromList(ksData.getName() + "-错题分析", columns, list, response);
    }

}
