package com.xzcoder.kaoshi.user.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xzcoder.kaoshi.admin.mapper.StDanxuanMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StDuoxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.admin.mapper.StPanduanMapper;
import com.xzcoder.kaoshi.admin.mapper.StTiankongMapper;
import com.xzcoder.kaoshi.admin.mapper.WrongMapper;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.ExamWrongCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.WrongService;

/**
 * WrongServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class WrongServiceImpl implements WrongService {

    @Autowired
    private WrongMapper wrongMapper;
    //	@Autowired
//	private SjDataService sjDataService;
    @Autowired
    private StMainMapper stMainMapper;
    @Autowired
    private StDanxuanMapper danxuanMapper;
    @Autowired
    private StDuoxuanMapper duoxuanMapper;
    @Autowired
    private StPanduanMapper panduanMapper;
    @Autowired
    private StTiankongMapper tiankongMapper;

    @Override
    public ExamWrongCustom getUserWrongQsnByUserId(Integer userId)
            throws Exception {
        List<ExamWrongCustom> list = wrongMapper.getWrongListByUserId(userId);
        //多条错误记录
        if (list.size() > 1) {
            throw new ThrowsException("用户存在多条错题信息记录，与设计初衷不符合！");
        }
        //没有错误信息记录
        if (list.size() == 0) {
            return new ExamWrongCustom();
        }

        /*
         * 存在错误记录
         */
        //获取用户错题记录
        ExamWrongCustom wrongCustom = list.get(0);

        //判断错题数量0，返回空对象
        if (wrongCustom.getCount() <= 0) {
            return new ExamWrongCustom();
        }

        //获取段落信息的json数据
        JSONObject dlData = getDlDataByStIds(CommonUtils.JSONArrToIntegerArray(wrongCustom.getStIds()));
        //设置到po中
        wrongCustom.setDlData(dlData.toString());

        //获取试题详细信息的json数据
        String xtData = getXtDataJSONByDlData(CommonUtils.JSONArrToIntegerArray(wrongCustom.getStIds())).toString();
        //设置到po中
        wrongCustom.setXtData(xtData);

        //返回错题信息
        return wrongCustom;
    }

    private JSONObject getDlDataByStIds(Integer[] ids) {
        JSONObject dlData = new JSONObject();
        JSONObject d = new JSONObject();

        d.put("t", "");
        String idsStr = "";
        for (Integer id : ids) {
            idsStr += ("," + id);
        }
        d.put("ids", idsStr);
        d.put("fen", 0);
        dlData.put("1", d);
        dlData.put("allids", idsStr);
        return dlData;
    }

    private JSONObject getXtDataJSONByDlData(Integer[] ids) throws Exception {
        JSONArray dataArr = new JSONArray();
        for (Integer id : ids) {
            JSONObject stDetail = new JSONObject();
            //根据id获取试题信息
            ExamStMainCustom stMainCustom = stMainMapper.getStMainById(id);
            stDetail.put("questionId", stMainCustom.getQuestionId());
            stDetail.put("userId", stMainCustom.getUserId());
            stDetail.put("typeId", stMainCustom.getStTypeId());
            stDetail.put("classifyId", stMainCustom.getStClassifyId());
            stDetail.put("levelId", stMainCustom.getStLevelId());
            stDetail.put("knowledgeId", stMainCustom.getStKnowledgeId());
            stDetail.put("insUser", stMainCustom.getInsUser());
            stDetail.put("insDate", stMainCustom.getInsDateString());
            stDetail.put("updUser", stMainCustom.getUpdUser());
            stDetail.put("updDate", stMainCustom.getUpdDateString());
//			stDetail.put("score", score);
            stDetail.put("del", stMainCustom.getDel() ? true : false);
            switch (stMainCustom.getStTypeId()) {
                case 1://单选题
                    ExamStDanxuanCustom dx = danxuanMapper.getStDanxuanById(id);
                    stDetail.put("title", dx.getTitle());
                    stDetail.put("options", dx.getOptions().replaceAll("\"", "\\\""));
                    stDetail.put("rightKey", dx.getRightKey());
                    stDetail.put("detail", dx.getDetail());
                    break;
                case 2://多选题
                    ExamStDuoxuanCustom duoxan = duoxuanMapper.getStDuoxuanById(id);
                    stDetail.put("title", duoxan.getTitle());
                    stDetail.put("options", duoxan.getOptions().replaceAll("\"", "\\\""));
                    stDetail.put("rightKey", duoxan.getRightKey());
                    stDetail.put("detail", duoxan.getDetail());
                    break;
                case 3://判断题
                    ExamStPanduanCustom panduan = panduanMapper.getStPanduanById(id);
                    stDetail.put("title", panduan.getTitle());
                    stDetail.put("rightKey", panduan.getRightKey() ? 1 : 0);
                    stDetail.put("detail", panduan.getDetail());
                    break;
                case 4://填空题
                    ExamStTiankongCustom tiankong = tiankongMapper.getStTiankongById(id);
                    stDetail.put("title", tiankong.getTitle());
                    stDetail.put("rightKey", tiankong.getRightKey().replaceAll("\"", "\\\""));
                    stDetail.put("detail", tiankong.getDetail());
                    break;
                default:
                    throw new ThrowsException("试题类型编号错误！");
            }
            dataArr.add(stDetail);
        }

        JSONObject data = new JSONObject();
        data.put("data", dataArr);

        JSONObject xtData = new JSONObject();
        xtData.put("1", data);

        return xtData;
    }

    //	@Override
    public void updateUserWrongQsn(Integer questionId, SysUserCustom user)
            throws Exception {
        List<ExamWrongCustom> list = wrongMapper.getWrongListByUserId(user.getId());
        //多条错误记录
        if (list.size() > 1) {
            throw new ThrowsException("用户存在多条错题信息记录，与设计初衷不符合！");
        }
        //没有错误信息记录
        if (list.size() == 0) {
            return;
        }

        //获取用户错题信息
        ExamWrongCustom wrongCustom = list.get(0);
        //将错题ids字符串转换成数组
        Integer[] idsArr = CommonUtils.JSONArrToIntegerArray(wrongCustom.getStIds());
        //将数组转换为list集合
        List<Integer> ids = new ArrayList<Integer>();
        for (Integer id : idsArr) {
            ids.add(id);
        }
        //删除消除掉的错题编号
        if (!ids.remove(questionId)) {
            throw new CustomException("删除错题失败！");
        }

        /*
         * 如果错题全部消除，即list.size()等于0，就删除该用户的错题记录信息
         * 否则修改用户错题记录信息
         */
        if (ids.size() == 0) {
            wrongMapper.deleteWrongByUserId(user.getId());
        } else {
            //将新的错题集合设置到po中
            wrongCustom.setStIds(JSONArray.fromObject(ids).toString());
            //更新用户错题信息
            wrongMapper.updateStIdsByUserId(wrongCustom);
        }
    }

    //	@Override
    public Integer getUserWrongCount(SysUserCustom user) throws Exception {

        List<ExamWrongCustom> list = wrongMapper.getWrongListByUserId(user.getId());

        //多条错误记录
        if (list.size() > 1) {
            throw new ThrowsException("用户存在多条错题信息记录，与设计初衷不符合！");
        }

        //没有错题
        if (list.size() == 0) {
            return 0;
        }

        //获取用户错题信息
        ExamWrongCustom wrongCustom = list.get(0);
        //返回错题总数
        return wrongCustom.getCount();
    }

    //	@Override
    public void insertUserWrong(SysUserCustom user, Integer questionId)
            throws Exception {
        //根据用户id获取用户已存在的错题
        List<ExamWrongCustom> wrs = wrongMapper.getWrongListByUserId(user.getId());

        if (wrs != null && wrs.size() == 0) {
            /*
             * 该用户原先不存在错题
             */
            ExamWrongCustom wrongCustom = new ExamWrongCustom();
            wrongCustom.setUserId(user.getId());
            //将id数组转换成JSON串
            JSONArray arr = new JSONArray();
            arr.add(questionId);
            wrongCustom.setStIds(arr.toString());
            //添加错题记录
            wrongMapper.insertWrong(wrongCustom);
        } else if (wrs != null && wrs.size() == 1) {
            /*
             * 该用户已经存在错题
             */
            ExamWrongCustom wrongCustom = wrs.get(0);
            //先获取用户原先的错题
            String lastIdsStr = wrongCustom.getStIds();
            Integer[] lastIds = CommonUtils.JSONArrToIntegerArray(lastIdsStr);

            //创建set集合用于保存新的ids数组
            Set<Integer> ids = new HashSet<Integer>();
            //将原先的错题添加到set中
            for (Integer id : lastIds) {
                ids.add(id);
            }
            //将新的错题添加到set中
            ids.add(questionId);

            //将set集合转换为json串
            JSONArray jsonIds = JSONArray.fromObject(ids);
            //将试题ids重新设置到po中
            wrongCustom.setStIds(jsonIds.toString());
            //修改该用户的错题记录
            wrongMapper.updateStIdsByUserId(wrongCustom);
        } else {
            //该用户存在多条错题记录，与设计初衷不符
            throw new ThrowsException("exam_wrong表存在异常！");
        }
    }


}
