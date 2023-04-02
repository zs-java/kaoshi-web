package com.xzcoder.kaoshi.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xzcoder.kaoshi.admin.service.StClassifyService;
import com.xzcoder.kaoshi.admin.service.StKnowledgeService;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzcoder.kaoshi.common.utils.JSONConvertUtils;
import com.xzcoder.kaoshi.common.utils.ResponseUtils;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;

/**
 * UserStController
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
@Controller
@RequestMapping("/user/shiti/")
public class UserStController {

    @Autowired
    private StClassifyService stClassifyService;
    @Autowired
    private StKnowledgeService stKnowledgeService;

    /**
     * 获取所有试题分类树形json串
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("getAllStClassifyTree.json")
    public void getAllStClassifyTree(HttpServletResponse response) throws Exception {

        List<ExamStClassifyCustom> stClassifyTree = stClassifyService.getAllStClassifyTree();
        ResponseUtils.write(response, JSONConvertUtils.stClassifyTreeToJSON(stClassifyTree));

    }

    /**
     * 获取所有试题知识点分类的树形json串
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("getAllStKnowledgeTree.json")
    public void getAllStKnowledgeTree(HttpServletResponse response) throws Exception {

        List<ExamStKnowledgeCustom> stks = stKnowledgeService.getAllStKnowledgeTree();
        ResponseUtils.write(response, JSONArray.fromObject(stks));

    }

}
