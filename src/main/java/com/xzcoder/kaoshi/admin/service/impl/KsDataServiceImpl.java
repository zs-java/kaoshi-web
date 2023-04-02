package com.xzcoder.kaoshi.admin.service.impl;

import java.util.*;

import com.xzcoder.kaoshi.admin.service.KsDataService;
import com.xzcoder.kaoshi.common.po.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.KsDataMapper;
import com.xzcoder.kaoshi.admin.mapper.KsUserMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.common.po.ExamStResult;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.ehcache.EhCacheUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsMonitor;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.ExamSjDataCustom;
import com.xzcoder.kaoshi.po.SysGroupCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.KsDataQueryVo;
import com.xzcoder.kaoshi.vo.KsMonitorQueryVo;

/**
 * KsDataServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsDataServiceImpl implements KsDataService {

    private final static Logger logger = LoggerFactory.getLogger(KsDataServiceImpl.class);
    private final static String errorResultCacheName = "errorResultCache";

    @Autowired
    private EhCacheUtils ehCacheUtils;

    @Autowired
    private KsDataMapper ksDataMapper;
    @Autowired
    private KsUserMapper ksUserMapper;
    @Autowired
    private StMainMapper stMainMapper;

    @Override
    public List<ExamKsDataCustom> findKsDataByVo(KsDataQueryVo ksDataQueryVo,
                                                 PageBean pageBean) throws Exception {

        //获取总记录数
        Integer totalRecored = ksDataMapper.getKsDataCountByVo(ksDataQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        ksDataQueryVo.setPageBean(pageBean);

        //组合条件查询
        return ksDataMapper.findKsDataListByVo(ksDataQueryVo);
    }

    @Override
    public Integer insertKsData(ExamKsDataCustom ksDataCustom, SysUserCustom user, Integer[] userIds)
            throws Exception {

        //设置添加者信息
        ksDataCustom.setInsUser(user.getUsername());
        ksDataCustom.setInsDate(new Date());
        //TODO 暂时默认通过审核
        ksDataCustom.setReviewInt(1);
        ksDataCustom.setUuid(CommonUtils.uuid());
        if (ksDataCustom.getPic() == null || "".equals(ksDataCustom.getPic())) {
            ksDataCustom.setPic("default/default_ks_icon.jpg");
        }

        //添加考试信息
        ksDataMapper.insertKsData(ksDataCustom);

        //添加考试与用户对应关系信息表信息
        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        ksUserCustom.setKsId(ksDataCustom.getKsId());//考试id
        ksUserCustom.setSjId(ksDataCustom.getSjId());//试卷id
        ksUserCustom.setInsUser(user.getUsername());//添加者
        ksUserCustom.setInsDate(new Date());//添加时间

        if (ksDataCustom.getUserSignupInt() != 1) {
            //用户无需报名，默认状态为报名成功
            ksUserCustom.setSignupState(2);//审核通过
            ksUserCustom.setState(2);//未考试
        } else {
            //用户需要报名
            ksUserCustom.setSignupState(0);//未报名
            ksUserCustom.setState(0);//未报名
        }

        for (Integer userId : userIds) {
            ksUserCustom.setUserId(userId);
            ksUserMapper.insertKsUserDefault(ksUserCustom);
        }

        //获取自增主键考试编号
        Integer ksId = ksDataCustom.getKsId();

        return ksId;
    }

    @Override
    public ExamKsDataCustom getKsDataById(Integer id) throws Exception {
        if (id <= 0) {
            throw new ThrowsException("考试编号不正确！");
        }

        return ksDataMapper.getExamKsDataById(id);
    }

    @Override
    public void updateKsData(ExamKsDataCustom ksDataCustom, SysUserCustom user) throws Exception {

        //先判断该考试是否可以修改
        ExamKsDataCustom lastKsData = ksDataMapper.getExamKsDataById(ksDataCustom.getKsId());
        //获取当前日期
        Date now = new Date();
        //当前日期大于考试开始日期
        if (lastKsData.getBeginTime().getTime() < now.getTime()) {
            throw new CustomException("该考试已经开始，不能修改考试信息！");
        }
        //当前日期大于考试结束日期
        if (lastKsData.getEndTime().getTime() < now.getTime()) {
            throw new CustomException("该考试已经结束，不能修改考试信息！");
        }

        ksDataCustom.setUpdUser(user.getUsername());
        ksDataCustom.setUpdDate(new Date());

        //更新考试信息
        ksDataMapper.updateKsData(ksDataCustom);
    }

    @Override
    public void deleteKsDataById(Integer ksId, SysUserCustom user)
            throws Exception {
        if (ksId <= 0) {
            throw new ThrowsException("考试编号不正确！");
        }

        //获取当前日期
        Date now = new Date();
        //删除考试用户关联信息
        ksUserMapper.deleteKsUser(ksId, user.getUsername(), now);

        //删除考试信息
        ksDataMapper.deleteKsData(ksId, user.getUsername(), now);
    }

    @Override
    public String isCanBeginKs(Integer kuId) throws Exception {
        ExamKsUserCustom ksUserCustom = ksUserMapper.getKsUserById(kuId);
        switch (ksUserCustom.getState()) {
            case 0:
                return "未报名考试，请先报名！";
            case 1:
                return "报名尚未审核通过，请耐心等待！";
            case 4:
            case 5:
                return "考试已经完毕，请勿重复考试！";
            default:
                break;
        }

        //根据考试编号获取该考试信息
        ExamKsDataCustom ksDataCustom = ksUserCustom.getKsDataCustom();
        //日期判断
        Date now = new Date();
        System.out.println(now.toString());
        System.out.println(ksDataCustom.getBeginTime());
        if (now.getTime() < ksDataCustom.getBeginTime().getTime()) {
            return "考试尚未开始，不能开始考试！";//考试未开始
        }
        if (now.getTime() >= ksDataCustom.getEndTime().getTime()) {
            return "考试已经结束，不能开始考试！";//考试已经结束
        }
        //判断考试开始禁止进入考试时间
        if (ksDataCustom.getDisableTime() != 0
                && (now.getTime() > ksDataCustom.getBeginTime().getTime() + (ksDataCustom.getDisableTime() * 60 * 1000))) {
            return "开始考试" + ksDataCustom.getDisableTime() + "分钟后不能进入考试！";
        }

        //可以开始考试
        return null;
    }

    @Override
    public Integer isCanSubmitExam(Integer ksId, Integer kuId) throws Exception {

        /*
         *
         */
        //获取考试信息
        ExamKsDataCustom ksDataCustom = ksDataMapper.getExamKsDataById(ksId);
        //开始时间
        Date beginTime = null;
        if (ksDataCustom.getTimeType()) {
            //考试开始计时
            beginTime = ksDataCustom.getBeginTime();
        } else {
            //答卷开始计时
            ExamKsUserCustom ksUserCustom = ksUserMapper.getKsUserById(kuId);
            //获取答卷开始时间
            beginTime = ksUserCustom.getBeginTime();
        }

        //禁止提前交卷时间（分钟）
        Integer disableSubmit = ksDataCustom.getDisableSubmit();

        //获取当前时间
        Date now = new Date();
        if (now.getTime() > (beginTime.getTime() + (disableSubmit * 60 * 1000))) {
            //可以进行交卷
            return -1;
        }

        //不能交卷，返回禁止提前交卷时间
        return disableSubmit;
    }

    @Override
    public List<ExamKsMonitor> getAllExamIdAndNameList() throws Exception {
        return ksDataMapper.getAllExamIdAndNameList();
    }

    @Override
    public List<ExamKsMonitor> findKsMonitorByVo(
            KsMonitorQueryVo ksMonitorQueryVo, PageBean pageBean)
            throws Exception {

        //获取总记录数
        Integer totalRecored = ksDataMapper.getKsMonitorCountByVo(ksMonitorQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        ksMonitorQueryVo.setPageBean(pageBean);

        //组合条件查询
        List<ExamKsMonitor> list = ksDataMapper.findKsMonitorListByVo(ksMonitorQueryVo);

        //填充每场考试的详细监控信息（总人数、未考式人数、考试中人数、交卷人数）
        for (ExamKsMonitor ksMonitor : list) {
            //填充考试总人数
            ksMonitor.setTotalCount(ksUserMapper.findTotalCountByKsId(ksMonitor.getKsId()));
            //填充未参加考试人数
            ksMonitor.setNotJoinCount(ksUserMapper.findNotJoinCountByKsId(ksMonitor.getKsId()));
            //填充正在考试人数
            ksMonitor.setInExamCount(ksUserMapper.findInExamCountByKsId(ksMonitor.getKsId()));
            //填充已经交卷人数
            ksMonitor.setExamOverCount(ksUserMapper.findExamOverCountByKsId(ksMonitor.getKsId()));
            //填充平均分
            ksMonitor.setScoreAvg(ksUserMapper.getKsUserScoreAvgByKsId(ksMonitor.getKsId()));
            //填充及格人数
            ksMonitor.setPassUserCount(ksUserMapper.getPassUserCountByKsId(ksMonitor.getKsId()));
        }

        //返回考试监控信息集合
        return list;
    }

    @Override
    public boolean isCanAddUser(Integer ksId) throws Exception {
        ExamKsDataCustom ksData = ksDataMapper.getExamKsDataById(ksId);
        Date now = new Date();
        if (now.getTime() > ksData.getEndTime().getTime()) {
            throw new CustomException("考试已经结束，不能添加考生！");
        }
        return true;
    }

    @Override
    public List<ExamStResult> getKsErrorResult(Integer ksId, Integer groupId) throws Exception {

        String key = this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName() +
                "-ksId:" + ksId + ",groupId:";

        //查询数据库获取最新的交卷人数
        Integer nowOverCount = ksUserMapper.findExamOverCountByKsId(ksId);

        @SuppressWarnings("unchecked")
        Map<String, Object> ehcacheResult = (Map<String, Object>) ehCacheUtils.get(errorResultCacheName, key);

        //如果没有缓存，则查询数据库返回数据
        if (ehcacheResult == null) {
            return getKsErrorResultFromDb(ksId, groupId, nowOverCount);
        }

        /*
         * 缓存有数据，验证缓存中数据是否可用
         */
        //获取缓存中的交卷人数
        Integer lastOverCount = Integer.parseInt(ehcacheResult.get("overCount").toString());
        //对比数据，如果数据不一致，重新从数据库中获取数据
        if (Objects.equals(lastOverCount, nowOverCount)) {
            return getKsErrorResultFromDb(ksId, groupId, nowOverCount);
        }
        //获取缓存中的数据集合
        @SuppressWarnings("unchecked")
        List<ExamStResult> list = (List<ExamStResult>) ehcacheResult.get("errorResult");
        //数据为空，重新获取
        if (list == null) {
            return getKsErrorResultFromDb(ksId, groupId, nowOverCount);
        }

        //直接返回缓存中的数据
        return list;
    }

    private List<ExamStResult> getKsErrorResultFromDb(Integer ksId, Integer groupId, Integer overCount) throws Exception {
        //获取考试信息
        ExamKsDataCustom ksData = ksDataMapper.getExamKsDataById(ksId);
        //获取试卷信息
        ExamSjDataCustom sjData = ksData.getSjDataCustom();
        JSONObject sjJson = JSONObject.fromObject(sjData.getGdxt());
        Integer[] ids = CommonUtils.StringArrToIntegerArr(sjJson.getString("allids"));
        Map<Integer, ExamStResult> map = new HashMap<>();
        for (Integer id : ids) {
            map.put(id, new ExamStResult(id, stMainMapper.getStMainById(id)));
        }

        //获取已经完成该考试的用户答题信息
        List<ExamKsUserCustom> ksUserList = ksUserMapper.getOverKsUserByKsId(ksId, groupId);
        for (ExamKsUserCustom ksUser : ksUserList) {
            String daanData = ksUser.getDaanData();
            if (StringUtils.isEmpty(daanData)) {
                logger.error("学生答案异常！ksUserId=" + ksUser.getId());
                continue;
            }
            JSONArray userAnswerArr = JSONArray.fromObject(daanData);
            if (userAnswerArr.size() <= 0) {
                //答案异常
                logger.error("学生答案异常！ksUserId=" + ksUser.getId());
                continue;
            }

            for (int i = 0; i < userAnswerArr.size(); i++) {
                JSONObject daanJson = null;
                try {
                    daanJson = userAnswerArr.getJSONObject(i);
                    if (daanJson == null || "{}".equals(daanJson.toString())) {
                        continue;
                    }
                    int qsnId = daanJson.getInt("sid");
                    int defen = daanJson.getInt("defen");
                    if (!map.containsKey(qsnId)) {
                        //TODO 学生答案异常！
                        logger.error("学生答案异常！ksUserId=" + ksUser.getId());
                        continue;
                    }
                    ExamStResult examStResult = map.get(qsnId);
                    if (defen == 0) {
                        //回答错误
                        examStResult.addError(ksUser.getUserCustom());
                    } else {
                        //回答正确
                        examStResult.addRight(ksUser.getUserCustom());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        List<ExamStResult> list = new ArrayList<ExamStResult>(map.values());
        putErrorResultToCache(errorResultCacheName, list, overCount);
        return list;
    }

    private void putErrorResultToCache(String key, List<ExamStResult> errorResult, int overCount) {
        Map<String, Object> ehcacheResult = new HashMap<String, Object>();
        //存入缓存
        ehcacheResult.put("overCount", overCount);
        ehcacheResult.put("errorResult", errorResult);
        ehCacheUtils.put(errorResultCacheName, key, ehcacheResult);
    }

    @Override
    public List<SysGroupCustom> getKsUserGroupsByKsId(Integer ksId)
            throws Exception {
        return ksUserMapper.getKsUserGroupsByKsId(ksId);
    }


}
