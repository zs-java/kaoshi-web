package com.xzcoder.kaoshi.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.StDanxuanMapper;
import com.xzcoder.kaoshi.admin.service.StWrongService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StDuoxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.admin.mapper.StPanduanMapper;
import com.xzcoder.kaoshi.admin.mapper.StTiankongMapper;
import com.xzcoder.kaoshi.admin.mapper.StWrongMapper;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.StWrong;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.po.Wrong;

/**
 * StWrongServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StWrongServiceImpl implements StWrongService {

    @Autowired
    private StWrongMapper stWrongMapper;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public StWrong getUserWrongs(Integer userId, Integer pc, Integer ps) throws Exception {

        StWrong stWrong = new StWrong();
        stWrong.setUserId(userId);
        stWrong.setPc(pc);
        stWrong.setPs(ps);

        stWrong.setTr(stWrongMapper.getWrongDateCountByUserId(stWrong));
        List<Wrong> wrongs = stWrongMapper.findWrongDateByUserId(stWrong);


        //不存在错题返回空对象
        if (wrongs.size() == 0) {
            return stWrong;
        }

        //根据id，日期获取该日期的错题id集合
        for (Wrong wrong : wrongs) {
            wrong.setStIds(stWrongMapper.findStIdsByUserIdAndDate(wrong));
        }

        //封装段落信息
        JSONObject dlData = getDlDataByStIds(wrongs);
        stWrong.setDlData(dlData.toString());

        //封装题目信息
        stWrong.setXtData(getXtDataJSONByDlData(wrongs).toString());
        return stWrong;
    }

    @Override
    public void updateUserWrong(Integer questionId, SysUserCustom user)
            throws Exception {
        stWrongMapper.deleteWrongByUserIdQsnId(user.getId(), questionId);
    }

    private JSONObject getDlDataByStIds(List<Wrong> wrongs) {
        JSONObject dlData = new JSONObject();
        String allIdsStr = "";
        int i = 1;
        for (Wrong wrong : wrongs) {
            JSONObject d = new JSONObject();
            d.put("t", sdf.format(wrong.getInsDate()));
            String idsStr = "";
            for (Integer id : wrong.getStIds()) {
                idsStr += ("," + id);
            }
            d.put("ids", idsStr);
            d.put("fen", 0);
            dlData.put(i + "", d);

            allIdsStr += idsStr;
            i++;
        }
        dlData.put("allids", allIdsStr);
        return dlData;
    }

    private JSONObject getXtDataJSONByDlData(List<Wrong> wrongs) throws Exception {
        JSONObject xtData = new JSONObject();
        int i = 1;
        for (Wrong wrong : wrongs) {
            JSONArray dataArr = new JSONArray();
            for (Integer id : wrong.getStIds()) {
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
//				stDetail.put("score", score);
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
            xtData.put(i + "", data);
            i++;
        }
        return xtData;
    }

    @Override
    public void insertUserWrong(Integer userId, Integer questionId)
            throws Exception {
//		stWrongMapper.deleteWrongByUserIdQsnId(userId, questionId);
        stWrongMapper.deleteWrongByUserIdQsnIdNowDate(userId, questionId);
        stWrongMapper.insertWrongByUserIdQsnId(userId, questionId);
    }

    @Override
    public Integer getUserWrongCount(SysUserCustom user) throws Exception {
        return stWrongMapper.getUserWrongsCount(user.getId());
    }

    @Override
    public void deleteWrongBeforeDate(Date date) throws Exception {
        stWrongMapper.deleteWrongBeforeDate(date);
    }
}
