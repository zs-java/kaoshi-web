package com.xzcoder.kaoshi.admin.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.SjDataMapper;
import com.xzcoder.kaoshi.admin.mapper.StBianchengMapper;
import com.xzcoder.kaoshi.admin.mapper.StDanxuanMapper;
import com.xzcoder.kaoshi.admin.service.SjDataService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StDuoxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.admin.mapper.StPanduanMapper;
import com.xzcoder.kaoshi.admin.mapper.StTiankongMapper;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.po.ExamStBianchengCustom;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.SjDataQueryVo;

/**
 * SjDataServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class SjDataServiceImpl implements SjDataService {

    @Autowired
    private SjDataMapper sjDataMapper;
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
    @Autowired
    private StBianchengMapper bianchengMapper;

    @Override
    public List<ExamSjDataCustom> findSjDataListByVo(SjDataQueryVo sjDataQueryVo, PageBean pageBean, SysUserCustom user)
            throws Exception {

        if (sjDataQueryVo.getSjDataCustom() == null) {
            sjDataQueryVo.setSjDataCustom(new ExamSjDataCustom());
        }

        //判断该用户是否有私有题库的权限
        Integer readPrivateSt = user.getReadPrivateSt();
        if (readPrivateSt == null || readPrivateSt != 1) {
            //没有权限，只能查看公开题目
            sjDataQueryVo.getSjDataCustom().setVisable(1);
        } else {
            //有权限
            sjDataQueryVo.getSjDataCustom().setVisable(sjDataQueryVo.getVisable());
        }

        //获取总记录数
        Integer totalRecored = sjDataMapper.findSjDataCountByVo(sjDataQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        sjDataQueryVo.setPageBean(pageBean);

        //组合条件查询
        return sjDataMapper.findSjDataListByVo(sjDataQueryVo);
    }

    @Override
    public Integer insertSjData(String title, Integer sjClassifyId, String des,
                                Integer count, BigDecimal totalScore, String stInfo, Integer visable, SysUserCustom user)
            throws Exception {

        //解析试题信息json串
        JSONObject stInfoObj = JSONObject.fromObject(stInfo);
        String gdxtStr = stInfoObj.getString("gdxt");
        String sjxtStr = stInfoObj.getString("sjxt");
        String xzst = getXzstJSONByStInfo(stInfoObj.getJSONObject("gdxt")).toString();

        //将信息封装成试卷对象
        ExamSjDataCustom sjDataCustom = new ExamSjDataCustom();
        sjDataCustom.setTitle(title);
        sjDataCustom.setSjClassifyId(sjClassifyId);
        sjDataCustom.setDes(des);
        sjDataCustom.setGdxt(gdxtStr);
        sjDataCustom.setSjxt(sjxtStr);
        sjDataCustom.setXzst(xzst);
        sjDataCustom.setTotalScore(totalScore);
        sjDataCustom.setCount(count);
        //TODO 此处暂时默认审核通过
        sjDataCustom.setReview(true);
        sjDataCustom.setInsUser(user.getUsername());
        sjDataCustom.setInsDate(new Date());

        //如果试卷可见性为私有，先判断该用户能添加私有试卷
        if ((int) visable == 0) {
            //试卷为私有
            if ((int) user.getReadPrivateSt() == 1) {
                //有权限，设置试卷为私有
                sjDataCustom.setVisable(0);
            } else {
                //没有权限，抛出异常
                throw new CustomException("当前用户没有操作私有试卷的权限！");
            }
        } else {
            //试卷为公开
            sjDataCustom.setVisable(1);
        }

        //添加试卷信息
        sjDataMapper.insertSjData(sjDataCustom);

        //返回自增主键
        return sjDataCustom.getSjId();
    }

    @Override
    public void updateSjData(Integer sjId, String title, Integer sjClassifyId,
                             String des, Integer count, BigDecimal totalScore, String stInfo, Integer visable,
                             SysUserCustom user) throws Exception {
        if (sjId == null || sjId <= 0) {
            throw new ThrowsException("试卷标号不能为空或小于登陆0！");
        }

        //解析试题信息json串
        JSONObject stInfoObj = JSONObject.fromObject(stInfo);
        String gdxtStr = stInfoObj.getString("gdxt");
        String sjxtStr = stInfoObj.getString("sjxt");
        String xzst = getXzstJSONByStInfo(stInfoObj.getJSONObject("gdxt")).toString();

        //将信息封装成试卷对象
        ExamSjDataCustom sjDataCustom = new ExamSjDataCustom();
        sjDataCustom.setSjId(sjId);
        sjDataCustom.setTitle(title);
        sjDataCustom.setSjClassifyId(sjClassifyId);
        sjDataCustom.setDes(des);
        sjDataCustom.setGdxt(gdxtStr);
        sjDataCustom.setSjxt(sjxtStr);
        sjDataCustom.setXzst(xzst);
        sjDataCustom.setTotalScore(totalScore);
        sjDataCustom.setCount(count);
        sjDataCustom.setUpdUser(user.getUsername());
        sjDataCustom.setUpdDate(new Date());


        /*
         * 验证试题可见性
         */
        Integer oldVisable = sjDataMapper.getSjVisableById(sjId);
        if (oldVisable == 1) {
            //试卷修改前为公开
            if ((int) visable == 1) {
                //如果要修改为公开，visable设置为-1表示不修改
                sjDataCustom.setVisable(-1);
            } else {
                //要修改为私有，先判断用户权限够不够
                if ((int) user.getReadPrivateSt() == 1) {
                    //有权限，可以需改为私有
                    sjDataCustom.setVisable(0);
                } else {
                    //没有权限，无法修改
                    sjDataCustom.setVisable(-1);
                }
            }
        } else if (oldVisable == 0) {
            //试题修改前为私有
            if ((int) visable == 1) {
                //要改为公开，先判断用户权限
                if ((int) user.getReadPrivateSt() == 1) {
                    //有权限，可以修改为公开
                    sjDataCustom.setVisable(1);
                } else {
                    //没有权限，无法修改
                    sjDataCustom.setVisable(-1);
                }
            } else {
                //要改为私有，visable设置为-1表示不修改
                sjDataCustom.setVisable(-1);
            }
        }


        //更新试卷信息
        sjDataMapper.updateSjData(sjDataCustom);

    }

    @Override
    public ExamSjDataCustom getSjDataById(Integer id, SysUserCustom user) throws Exception {
        if (id == null || id <= 0) {
            throw new ThrowsException("试卷标号不能为空或小于登陆0！");
        }

        /*
         * 判断用户权限，是否可以查看该试卷
         * 如果是学生用户，判断该学生是否有对应试卷的考试，如果没有，权限不足
         */
        if ((int) user.getRoleId() == 2) {
            //学生用户
            if ((int) sjDataMapper.getSjCountBySjIdAndUid(id, user.getId()) > 0) {
                //有权
                //查询并返回试卷信息
                return sjDataMapper.getSjDataById(id);
            } else {
                //无权，返回null
                return null;
            }
        }

        //获取试卷可见等级
        if ((int) sjDataMapper.getSjVisableById(id) == 0) {
            //试卷为私有，判断用户是否有权限
            if ((int) user.getReadPrivateSt() != 1) {
                //用户没有权限，返回空
                return null;
            }
        }

        //查询并返回试卷信息
        return sjDataMapper.getSjDataById(id);
    }

    /**
     * 根据试题信息解析出试题的详细信息json串
     *
     * @param gdxtObj
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getXzstJSONByStInfo(JSONObject gdxtObj) throws Exception {
        //解析试题信息json串
//		JSONObject gdxtObj = stInfoObj.getJSONObject("gdxt");

        JSONObject index = new JSONObject();
        JSONObject data = new JSONObject();

        int i = 1;
        //遍历固定选题json串
        for (Object keyObj : gdxtObj.keySet()) {
            String key = (String) keyObj;
            if ("allids".equals(key)) {
                continue;
            }
            JSONObject sts = gdxtObj.getJSONObject(key);
            Integer[] ids = CommonUtils.StringArrToIntegerArr(sts.getString("ids"));
            Double score = sts.getDouble("fen");
            for (Integer id : ids) {
                index.put(key + "_" + id, "");
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
                stDetail.put("score", score);
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
                    case 5://编程题
                        ExamStBianchengCustom biancheng = bianchengMapper.getBianchengById(id);
                        stDetail.put("title", biancheng.getTitle());
                        stDetail.put("detail", biancheng.getDetail());
                        break;
                    default:
                        throw new ThrowsException("试题类型编号错误！");
                }
                data.put(i, stDetail);
                i++;
            }
        }
        JSONObject xzstObj = new JSONObject();
        xzstObj.put("index", index);
        xzstObj.put("data", data);
        return xzstObj;
    }

    @Override
    public void deleteSjDataById(Integer id, SysUserCustom user)
            throws Exception {
        //TODO 对包含该试卷 的考试进行操作
        sjDataMapper.deleteSjDataById(id, user.getUsername(), new Date());
    }

    @Override
    public void deleteSjDataList(Integer[] ids, SysUserCustom user)
            throws Exception {
        // TODO 对包含该试卷 的考试进行操作
        sjDataMapper.deleteSjDataList(ids, user.getUsername(), new Date());
    }

}
