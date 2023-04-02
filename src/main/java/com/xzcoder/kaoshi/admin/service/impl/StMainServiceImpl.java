package com.xzcoder.kaoshi.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.StBianchengMapper;
import com.xzcoder.kaoshi.admin.mapper.StDanxuanMapper;
import com.xzcoder.kaoshi.admin.service.StMainService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StDuoxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.admin.mapper.StPanduanMapper;
import com.xzcoder.kaoshi.admin.mapper.StTiankongMapper;
import com.xzcoder.kaoshi.admin.mapper.StWrongMapper;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStBianchengCustom;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.StMainQueryVo;

public class StMainServiceImpl implements StMainService {

    @Autowired
    private StMainMapper stMainMapper;
    @Autowired
    private StDanxuanMapper stDanxuanMapper;
    @Autowired
    private StDuoxuanMapper stDuoxuanMapper;
    @Autowired
    private StPanduanMapper stPanduanMapper;
    @Autowired
    private StTiankongMapper stTiankongMapper;
    @Autowired
    private StBianchengMapper stBianchengMapper;
    @Autowired
    private StWrongMapper stWrongMapper;

    @Override
    public List<ExamStMainCustom> findStListByStQueryVo(
            StMainQueryVo stMainQueryVo, PageBean pageBean, SysUserCustom user) throws Exception {

        if (stMainQueryVo.getStMainCustom() == null) {
            stMainQueryVo.setStMainCustom(new ExamStMainCustom());
        }

        //判断该用户是否有私有题库的权限
        if ((int) user.getReadPrivateSt() != 1) {
            //没有权限，只能查看公开题目
            stMainQueryVo.getStMainCustom().setVisable(1);
        } else {
            //有权限
            stMainQueryVo.getStMainCustom().setVisable(stMainQueryVo.getVisable());
        }


        //获取总记录数
        Integer totalRecored = stMainMapper.getStCountByStQueryVo(stMainQueryVo);
        //封装分页信息
        pageBean.setTotalRecored(totalRecored);
        //将分页信息设置到stMainQueryVo
        stMainQueryVo.setPageBean(pageBean);

        //组合条件查询
        return stMainMapper.findStListByStQueryVo(stMainQueryVo);
    }

    @Override
    public Integer insertDanxuanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText, String options, String detail,
                                   Integer rightKey, Integer visable, SysUserCustom user) throws Exception {
        //将试题信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        titleText = (titleText == null || "".equals(titleText)) ? "单选题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));//设置题目纯文本
        stMainCustom.setUserId(user.getId());//设置添加试题的用户编号
        stMainCustom.setStClassifyId(classifyId);//设置试题分类编号
        stMainCustom.setStTypeId(1);//设置试题类型为单选题
        stMainCustom.setStLevelId(levelId);//设置试题难度编号
        stMainCustom.setStKnowledgeId(knowledgeId);//设置试题知识点编号
        //获取当前最大排序值
        Integer maxSort = stMainMapper.getStMainMaxSort();
        maxSort = maxSort == null ? 0 : maxSort;
        stMainCustom.setSort(maxSort + 1);//设置试题排序为当前最大排序+1
        stMainCustom.setInsUser(user.getUsername());//设置添加者用户名
        stMainCustom.setInsDate(new Date());

        //如果题目可见性为私有，先判断该用户能添加私有题目
        if ((int) visable == 0) {
            //题目为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置题目为私有
                stMainCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有题目的权限！");
            }
        } else {
            //题目为公开
            stMainCustom.setVisable(1);
        }

        //向试题主表中添加试题信息
        stMainMapper.insertStMain(stMainCustom);

        //获取添加试题主表 产生的自增主键（试题编号 ）
        Integer questionId = stMainCustom.getQuestionId();

        //将单选题信息封装成单选题对象
        ExamStDanxuanCustom danxuanCustom = new ExamStDanxuanCustom();
        danxuanCustom.setQuestionId(questionId);
        danxuanCustom.setTitle(title);
        danxuanCustom.setOptions(options);
        danxuanCustom.setRightKey(rightKey);
        danxuanCustom.setDetail(detail);

        //添加单选题详细信息
        stDanxuanMapper.insertStDanxuan(danxuanCustom);

        //返回试题编号
        return questionId;
    }

    @Override
    public ExamStMainCustom getStMainById(Integer questionId) throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stMainMapper.getStMainById(questionId);
    }

    @Override
    public ExamStDanxuanCustom getStDanxuanById(Integer questionId)
            throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stDanxuanMapper.getStDanxuanById(questionId);
    }

    @Override
    public void updateStDanxuanById(Integer questionId, Integer classifyId,
                                    Integer levelId, Integer knowledgeId, String title,
                                    String titleText, String options, String detail, Integer rightKey, Integer visable,
                                    SysUserCustom user) throws Exception {

        if (questionId == null || questionId <= 0) {
            throw new ThrowsException("试题编号错误！");
        }

        //将试题信息封装成单选题对象
        ExamStDanxuanCustom stDanxuanCustom = new ExamStDanxuanCustom();
        stDanxuanCustom.setQuestionId(questionId);
        stDanxuanCustom.setTitle(title);
        stDanxuanCustom.setOptions(options);
        stDanxuanCustom.setRightKey(rightKey);
        stDanxuanCustom.setDetail(detail);

        //更新单选题信息
        stDanxuanMapper.updateStDanxuan(stDanxuanCustom);

        //将试题信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        stMainCustom.setQuestionId(questionId);
        titleText = (titleText == null || "".equals(titleText)) ? "单选题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        stMainCustom.setUpdUser(user.getUsername());
        stMainCustom.setUpdDate(new Date());

        validateUpdateSt(questionId, visable, stMainCustom, user);

        //更新试题信息
        stMainMapper.updateStMainById(stMainCustom);
    }

    @Override
    public Integer insertDuoxuanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText,
                                   String options, String detail, Integer[] rightKeys, Integer visable, SysUserCustom user)
            throws Exception {

        //将多选题封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        titleText = (titleText == null || "".equals(titleText)) ? "多选题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setUserId(user.getId());
        stMainCustom.setStTypeId(2);
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        //获取当前最大排序
        Integer maxSort = stMainMapper.getStMainMaxSort();
        stMainCustom.setSort(maxSort + 1);
        stMainCustom.setInsUser(user.getUsername());
        stMainCustom.setInsDate(new Date());

        //如果题目可见性为私有，先判断该用户能添加私有题目
        if ((int) visable == 0) {
            //题目为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置题目为私有
                stMainCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有题目的权限！");
            }
        } else {
            //题目为公开
            stMainCustom.setVisable(1);
        }
        //添加多选题的基本 信息到信息主表
        stMainMapper.insertStMain(stMainCustom);

        //获取自增主键，试题编号
        Integer questionId = stMainCustom.getQuestionId();

        //将多选题信息封装成多选题对象
        ExamStDuoxuanCustom stDuoxuanCustom = new ExamStDuoxuanCustom();
        stDuoxuanCustom.setQuestionId(questionId);
        stDuoxuanCustom.setTitle(title);
        stDuoxuanCustom.setOptions(options);

        //将正确答案集合转换成json数组
        stDuoxuanCustom.setRightKey(JSONArray.fromObject(rightKeys).toString());
        stDuoxuanCustom.setDetail(detail);

        //添加多选题信息
        stDuoxuanMapper.insertStDuoxuan(stDuoxuanCustom);

        //返回试题编号
        return questionId;
    }

    @Override
    public ExamStDuoxuanCustom getStDuoxuanById(Integer questionId)
            throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stDuoxuanMapper.getStDuoxuanById(questionId);
    }

    @Override
    public void updateStDuoxuanById(Integer questionId, Integer classifyId,
                                    Integer levelId, Integer knowledgeId, String title,
                                    String titleText, String options, String detail,
                                    Integer[] rightKey, Integer visable, SysUserCustom user) throws Exception {
        if (questionId == null || questionId <= 0) {
            throw new ThrowsException("试题编号错误！");
        }

        //将信息封装成多选题对象
        ExamStDuoxuanCustom stDuoxuanCustom = new ExamStDuoxuanCustom();
        stDuoxuanCustom.setQuestionId(questionId);
        stDuoxuanCustom.setTitle(title);
        stDuoxuanCustom.setOptions(options);
        //将正确答案集合转换成json数组
        stDuoxuanCustom.setRightKey(JSONArray.fromObject(rightKey).toString());
        stDuoxuanCustom.setDetail(detail);

        //更新多选题信息
        stDuoxuanMapper.updateStDuoxuan(stDuoxuanCustom);

        //将信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        stMainCustom.setQuestionId(questionId);
        titleText = (titleText == null || "".equals(titleText)) ? "多选题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        stMainCustom.setUpdUser(user.getUsername());
        stMainCustom.setUpdDate(new Date());

        validateUpdateSt(questionId, visable, stMainCustom, user);

        //更新试题信息
        stMainMapper.updateStMainById(stMainCustom);
    }

    @Override
    public Integer insertPanduanSt(Integer classifyId, Integer levelId,
                                   Integer knowledgeId, String title, String titleText,
                                   String detail, Integer rightKey, Integer visable, SysUserCustom user) throws Exception {
        //将判断题封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        titleText = (titleText == null || "".equals(titleText)) ? "判断题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setUserId(user.getId());
        stMainCustom.setStTypeId(3);
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        //获取当前最大排序
        Integer maxSort = stMainMapper.getStMainMaxSort();
        stMainCustom.setSort(maxSort + 1);
        stMainCustom.setInsUser(user.getUsername());
        stMainCustom.setInsDate(new Date());

        //如果题目可见性为私有，先判断该用户能添加私有题目
        if ((int) visable == 0) {
            //题目为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置题目为私有
                stMainCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有题目的权限！");
            }
        } else {
            //题目为公开
            stMainCustom.setVisable(1);
        }

        //添加多选题的基本 信息到信息主表
        stMainMapper.insertStMain(stMainCustom);

        //获取自增主键，试题编号
        Integer questionId = stMainCustom.getQuestionId();

        //将判断题信息封装成判断题对象
        ExamStPanduanCustom stPanduanCustom = new ExamStPanduanCustom();
        stPanduanCustom.setQuestionId(questionId);
        stPanduanCustom.setTitle(title);
        if (rightKey == 1) {
            stPanduanCustom.setRightKey(true);
        } else if (rightKey == 0) {
            stPanduanCustom.setRightKey(false);
        } else {
            throw new ThrowsException("判断题答案错误！");
        }
        stPanduanCustom.setDetail(detail);

        //添加判断题信息
        stPanduanMapper.insertStPanduan(stPanduanCustom);

        //返回试题编号
        return questionId;
    }

    @Override
    public ExamStPanduanCustom getStPanduanById(Integer questionId)
            throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stPanduanMapper.getStPanduanById(questionId);
    }

    @Override
    public void updateStPanduanById(Integer questionId, Integer classifyId,
                                    Integer levelId, Integer knowledgeId, String title,
                                    String titleText, String detail, Integer rightKey,
                                    Integer visable, SysUserCustom user) throws Exception {
        if (questionId == null || questionId <= 0) {
            throw new ThrowsException("试题编号错误！");
        }

        //将信息封装成判断题对象
        ExamStPanduanCustom stPanduanCustom = new ExamStPanduanCustom();
        stPanduanCustom.setQuestionId(questionId);
        stPanduanCustom.setTitle(title);
        if (rightKey == 1) {
            stPanduanCustom.setRightKey(true);
        } else if (rightKey == 0) {
            stPanduanCustom.setRightKey(false);
        } else {
            throw new ThrowsException("判断题答案错误！");
        }
        stPanduanCustom.setDetail(detail);


        //更新判断题信息
        stPanduanMapper.updateStPanduan(stPanduanCustom);

        //将信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        stMainCustom.setQuestionId(questionId);
        titleText = (titleText == null || "".equals(titleText)) ? "判断题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        stMainCustom.setUpdUser(user.getUsername());
        stMainCustom.setUpdDate(new Date());

        validateUpdateSt(questionId, visable, stMainCustom, user);

        //更新试题信息
        stMainMapper.updateStMainById(stMainCustom);
    }

    @Override
    public void deleteStById(Integer questionId) throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }

        /*
         * 先根据试题id判断试题类型
         * 根据试题类型删除该条试题记录
         * 删除试题主表中的试题记录
         */
        Integer typeId = stMainMapper.getStTypeIdById(questionId);

        switch (typeId) {
            case 1://单选题
                stDanxuanMapper.deleteStDanxuan(questionId);
                break;
            case 2://多选题
                stDuoxuanMapper.deleteStDuoxuan(questionId);
                break;
            case 3://判断题
                stPanduanMapper.deleteStPanduan(questionId);
                break;
            case 4://填空题
                stTiankongMapper.deleteStTiankong(questionId);
                break;
            case 5:
                stBianchengMapper.deleteStBianchengById(questionId);
                break;
            default:
                throw new ThrowsException("试题类型不存在");
        }

        //删除错题
        stWrongMapper.deleteWrongByQsnId(questionId);

        //删除试题主表中的试题记录
        stMainMapper.deleteStMain(questionId);
    }

    @Override
    public void deleteStList(Integer[] ids) throws Exception {

        if (ids == null || ids.length == 0) {
            throw new CustomException("删除前请至少选择一条记录！");
        }

        for (Integer id : ids) {
            Integer typeId = stMainMapper.getStTypeIdById(id);
            switch (typeId) {
                case 1://单选题
                    stDanxuanMapper.deleteStDanxuan(id);
                    break;
                case 2://多选题
                    stDuoxuanMapper.deleteStDuoxuan(id);
                    break;
                case 3://判断题
                    stPanduanMapper.deleteStPanduan(id);
                    break;
                case 4://填空题
                    stTiankongMapper.deleteStTiankong(id);
                    break;
                case 5:
                    stBianchengMapper.deleteStBianchengById(id);
                    break;
                default:
                    throw new ThrowsException("试题类型不存在");
            }
        }

        //批量删除试题信息
        stMainMapper.deleteStMainList(ids);
    }

    @Override
    public Integer insertTiankongSt(Integer classifyId, Integer levelId,
                                    Integer knowledgeId, String title, String titleText, String detail,
                                    String rightKey, Integer visable, SysUserCustom user) throws Exception {
        //将判断题封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        titleText = (titleText == null || "".equals(titleText)) ? "填空题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setUserId(user.getId());
        stMainCustom.setStTypeId(4);
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        //获取当前最大排序
        Integer maxSort = stMainMapper.getStMainMaxSort();
        stMainCustom.setSort(maxSort + 1);
        stMainCustom.setInsUser(user.getUsername());
        stMainCustom.setInsDate(new Date());

        //如果题目可见性为私有，先判断该用户能添加私有题目
        if ((int) visable == 0) {
            //题目为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置题目为私有
                stMainCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有题目的权限！");
            }
        } else {
            //题目为公开
            stMainCustom.setVisable(1);
        }

        //添加填空题的基本 信息到信息主表
        stMainMapper.insertStMain(stMainCustom);

        //获取自增主键，试题编号
        Integer questionId = stMainCustom.getQuestionId();

        //将判断题信息封装成填空题对象
        ExamStTiankongCustom stTiankongCustom = new ExamStTiankongCustom();
        stTiankongCustom.setQuestionId(questionId);
        stTiankongCustom.setTitle(title);
        stTiankongCustom.setRightKey(rightKey);
        stTiankongCustom.setDetail(detail);

        //添加填空题信息
        stTiankongMapper.insertStTiankong(stTiankongCustom);

        //返回试题编号
        return questionId;
    }

    @Override
    public ExamStTiankongCustom getStTiankongById(Integer questionId)
            throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stTiankongMapper.getStTiankongById(questionId);
    }

    @Override
    public void updateStTiankongById(Integer questionId, Integer classifyId,
                                     Integer levelId, Integer knowledgeId, String title,
                                     String titleText, String detail, String rightKey, Integer visable, SysUserCustom user)
            throws Exception {
        if (questionId == null || questionId <= 0) {
            throw new ThrowsException("试题编号错误！");
        }

        //将信息封装成填空对象
        ExamStTiankongCustom stTiankongCustom = new ExamStTiankongCustom();
        stTiankongCustom.setQuestionId(questionId);
        stTiankongCustom.setTitle(title);
        stTiankongCustom.setRightKey(rightKey);
        stTiankongCustom.setDetail(detail);

        //更新判断题信息
        stTiankongMapper.updateStTiankong(stTiankongCustom);

        //将信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        stMainCustom.setQuestionId(questionId);
        titleText = (titleText == null || "".equals(titleText)) ? "填空题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        stMainCustom.setUpdUser(user.getUsername());
        stMainCustom.setUpdDate(new Date());

        validateUpdateSt(questionId, visable, stMainCustom, user);

        //更新试题信息
        stMainMapper.updateStMainById(stMainCustom);
    }

    @Override
    public List<ExamStMainCustom> findStUseableByVo(
            StMainQueryVo stMainQueryVo, PageBean pageBean, SysUserCustom user) throws Exception {
        if (stMainQueryVo.getStMainCustom() == null) {
            stMainQueryVo.setStMainCustom(new ExamStMainCustom());
        }

        //判断该用户是否有私有题库的权限
        if ((int) user.getReadPrivateSt() != 1) {
            //没有权限，只能查看公开题目
            stMainQueryVo.getStMainCustom().setVisable(1);
        } else {
            //有权限
            stMainQueryVo.getStMainCustom().setVisable(stMainQueryVo.getVisable());
        }

        //获取总记录数
        Integer totalRecored = stMainMapper.getStUseableCountByStQueryVo(stMainQueryVo);
        //封装分页信息
        pageBean.setTotalRecored(totalRecored);
        //将分页信息设置到stMainQueryVo
        stMainQueryVo.setPageBean(pageBean);

        //组合条件查询
        return stMainMapper.findStUseableByStQueryVo(stMainQueryVo);
    }

    @Override
    public JSONObject getStJsonById(Integer qsnId) throws Exception {
        //获取该题目的详细信息并封装成json数据返回
        ExamStMainCustom stMainCustom = stMainMapper.getStMainById(qsnId);

        JSONObject json = new JSONObject();
        json.put("questionId", stMainCustom.getQuestionId());
        json.put("userId", stMainCustom.getUserId());
        json.put("typeId", stMainCustom.getStTypeId());
        json.put("classifyId", stMainCustom.getStClassifyId());
        json.put("knowledgeId", stMainCustom.getStKnowledgeId());
        json.put("levelId", stMainCustom.getStLevelId());
        json.put("insUser", stMainCustom.getInsUser());
        json.put("insDate", stMainCustom.getInsDate());
        json.put("score", 0);
        json.put("updUser", stMainCustom.getUpdUser());
        json.put("updDate", stMainCustom.getUpdDate());
        json.put("del", stMainCustom.getDel() ? "true" : "false");
        switch (stMainCustom.getStTypeId()) {
            case 1://单选题
                ExamStDanxuanCustom danxuan = stDanxuanMapper.getStDanxuanById(qsnId);
                json.put("title", danxuan.getTitle());
                json.put("options", danxuan.getOptions());
                json.put("rightKey", danxuan.getRightKey());
                json.put("detail", danxuan.getDetail());
                break;
            case 2://多选题
                ExamStDuoxuanCustom duoxuan = stDuoxuanMapper.getStDuoxuanById(qsnId);
                json.put("title", duoxuan.getTitle());
                json.put("options", duoxuan.getOptions());
                json.put("rightKey", duoxuan.getRightKey());
                json.put("detail", duoxuan.getDetail());
                break;
            case 3://判断题
                ExamStPanduanCustom panduan = stPanduanMapper.getStPanduanById(qsnId);
                json.put("title", panduan.getTitle());
                json.put("rightKey", panduan.getRightKey() ? 1 : 0);
                json.put("detail", panduan.getDetail());
                break;
            case 4://填空题
                ExamStTiankongCustom tiankong = stTiankongMapper.getStTiankongById(qsnId);
                json.put("title", tiankong.getTitle());
                json.put("rightKey", tiankong.getRightKey());
                json.put("detail", tiankong.getDetail());
                break;
            case 5://编程题
                ExamStBianchengCustom biancheng = stBianchengMapper.getBianchengById(qsnId);
                json.put("title", biancheng.getTitle());
                json.put("detail", biancheng.getDetail());
                break;
            default:
                throw new CustomException("试题类型错误！");
        }

        //将试题信息的json数据返回
        return json;
    }

    @Override
    public List<ExamStMainCustom> findStListByIds(StMainQueryVo vo)
            throws Exception {
        return stMainMapper.findStListByIds(vo);
    }

    @Override
    public Integer insertBianchengSt(Integer classifyId, Integer levelId,
                                     Integer knowledgeId, String title, String titleText, String detail,
                                     Integer visable, SysUserCustom user) throws Exception {
        //将编程题封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        titleText = (titleText == null || "".equals(titleText)) ? "编程题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setUserId(user.getId());
        stMainCustom.setStTypeId(5);
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        //获取当前最大排序
        Integer maxSort = stMainMapper.getStMainMaxSort();
        stMainCustom.setSort(maxSort + 1);
        stMainCustom.setInsUser(user.getUsername());
        stMainCustom.setInsDate(new Date());

        //如果题目可见性为私有，先判断该用户能添加私有题目
        if ((int) visable == 0) {
            //题目为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置题目为私有
                stMainCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有题目的权限！");
            }
        } else {
            //题目为公开
            stMainCustom.setVisable(1);
        }

        //添加多选题的基本 信息到信息主表
        stMainMapper.insertStMain(stMainCustom);

        //获取自增主键，试题编号
        Integer questionId = stMainCustom.getQuestionId();

        //将编程题信息封装成编程题对象
        ExamStBianchengCustom stBianchengCustom = new ExamStBianchengCustom();
        stBianchengCustom.setQuestionId(questionId);
        stBianchengCustom.setTitle(title);
        stBianchengCustom.setDetail(detail);
        //添加编程题信息
        stBianchengMapper.insertStBiancheng(stBianchengCustom);

        //返回试题编号
        return questionId;
    }

    @Override
    public ExamStBianchengCustom getStBianchengById(Integer questionId)
            throws Exception {
        if (questionId == null || questionId < 0) {
            throw new ThrowsException("试题编号错误！");
        }
        return stBianchengMapper.getBianchengById(questionId);
    }

    @Override
    public void updateStBianchengById(Integer questionId, Integer classifyId,
                                      Integer levelId, Integer knowledgeId, String title,
                                      String titleText, String detail, Integer visable, SysUserCustom user)
            throws Exception {
        if (questionId == null || questionId <= 0) {
            throw new ThrowsException("试题编号错误！");
        }

        //将信息封装成编程题对象
        ExamStBianchengCustom stBianchengCustom = new ExamStBianchengCustom();
        stBianchengCustom.setQuestionId(questionId);
        stBianchengCustom.setTitle(title);
        stBianchengCustom.setDetail(detail);

        //更新编程题信息
        stBianchengMapper.updateStBiancheng(stBianchengCustom);

        //将信息封装成试题对象
        ExamStMainCustom stMainCustom = new ExamStMainCustom();
        stMainCustom.setQuestionId(questionId);
        titleText = (titleText == null || "".equals(titleText)) ? "编程题题目信息" : titleText;
        stMainCustom.setTitle(CommonUtils.getTitleTextByTitle(title));
        stMainCustom.setStClassifyId(classifyId);
        stMainCustom.setStLevelId(levelId);
        stMainCustom.setStKnowledgeId(knowledgeId);
        stMainCustom.setUpdUser(user.getUsername());
        stMainCustom.setUpdDate(new Date());

        validateUpdateSt(questionId, visable, stMainCustom, user);

        //更新试题信息
        stMainMapper.updateStMainById(stMainCustom);
    }

    public void validateUpdateSt(Integer questionId, Integer visable, ExamStMainCustom stMainCustom, SysUserCustom user) throws Exception {
        /*
         * 验证试题可见性
         */
        Integer oldVisable = stMainMapper.getStVisableById(questionId);
        if (oldVisable == 1) {
            //试题修改前为公开
            if ((int) visable == 1) {
                //如果要修改为公开，visable设置为-1表示不修改
                stMainCustom.setVisable(-1);
            } else {
                //要修改为私有，先判断用户权限够不够
                if ((int) user.getReadPrivateSt() == 1) {
                    //有权限，可以需改为私有
                    stMainCustom.setVisable(0);
                } else {
                    //没有权限，无法修改
                    stMainCustom.setVisable(-1);
                }
            }
        } else if (oldVisable == 0) {
            //试题修改前为私有
            if ((int) visable == 1) {
                //要改为公开，先判断用户权限
                if ((int) user.getReadPrivateSt() == 1) {
                    //有权限，可以修改为公开
                    stMainCustom.setVisable(1);
                } else {
                    //没有权限，无法修改
                    stMainCustom.setVisable(-1);
                }
            } else {
                //要改为私有，visable设置为-1表示不修改
                stMainCustom.setVisable(-1);
            }
        }
    }

}
