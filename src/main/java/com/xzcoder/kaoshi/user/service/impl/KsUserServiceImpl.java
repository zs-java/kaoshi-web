package com.xzcoder.kaoshi.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.admin.service.GroupService;
import com.xzcoder.kaoshi.admin.service.StWrongService;
import com.xzcoder.kaoshi.common.po.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.KsDataMapper;
import com.xzcoder.kaoshi.admin.mapper.KsUserMapper;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamKsDataCustom;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.service.KsUserService;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

/**
 * KsUserServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsUserServiceImpl implements KsUserService {

    @Autowired
    private KsUserMapper ksUserMapper;
    @Autowired
    private KsDataMapper ksDataMapper;
    @Autowired
    private StWrongService stWrongService;
    @Autowired
    private GroupService groupService;

    @Override
    public void updateKsUserSignUpById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new CustomException("用户考试编号不能为空！");
        }
        ksUserMapper.updateKsUserSignUpById(id);
    }

    @Override
    public List<ExamKsUserCustom> findSignupingKsUserListByVo(
            KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception {

        //获取总记录数
        Integer totalRecored = ksUserMapper.getSignupingKsUserByVo(ksUserQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        ksUserQueryVo.setPageBean(pageBean);

        //条件查询
        return ksUserMapper.findSignupingKsUserListByVo(ksUserQueryVo);
    }

    @Override
    public void updateSignupTrueKsUserById(Integer id, SysUserCustom user) throws Exception {
        if (id == null || id <= 0) {
            throw new CustomException("用户考试信息编号不能为空！");
        }

        //审核通过
        ksUserMapper.updateSignupTrueKsUserById(id, user.getUsername());
    }

    @Override
    public Integer getKsUserCountNotReadById(Integer userId) throws Exception {
        if (userId == null || userId <= 0) {
            throw new CustomException("用户编号不能为空！");
        }
        return ksUserMapper.getNotReadExamByUserId(userId);
    }

    @Override
    public void updateKsUserReadedByUserId(Integer userId) throws Exception {
        if (userId == null || userId <= 0) {
            throw new CustomException("用户编号不能为空！");
        }
        ksUserMapper.updateKsUserReadedByUserId(userId);
    }

    @Override
    public ExamKsUserCustom getKsUserById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new CustomException("用户考试信息编号不能为空！");
        }
        return ksUserMapper.getKsUserById(id);
    }

    @Override
    public void updateGoExamStateById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new CustomException("用户考试信息编号不能为空！");
        }

        //如果该用户已经开始该考试，则return
        ExamKsUserCustom ksUserCustom = ksUserMapper.getKsUserById(id);
        if (ksUserCustom.getState() == 3) {
            return;
        }
        ksUserMapper.updateKsUserStateById(id, 3);
        ksUserMapper.updateKsUserBeginTimeNowById(id);
    }

    @Override
    public void updateKsUserAnswerById(Integer kuId, String gdStr,
                                       SysUserCustom user) throws Exception {
        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        ksUserCustom.setId(kuId);
        ksUserCustom.setDaanData(gdStr);
        ksUserCustom.setUpdUser(user.getUsername());
        ksUserMapper.updateKsUserAnswerById(ksUserCustom);
    }

    @Override
    public void updateKsUserSubmit(Integer kuId, String gdStr, Integer[] wrongStIds,
                                   BigDecimal score, String ksUuid, SysUserCustom user) throws Exception {

        Integer state = ksUserMapper.getKsUserById(kuId).getState();

        if (state != 3) {
            throw new CustomException("无法提交考试！");
        }


        //将用户考试信息封装成对象
        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        ksUserCustom.setId(kuId);
        ksUserCustom.setDaanData(gdStr);
        ksUserCustom.setScore(score);
        ksUserCustom.setUpdUser(user.getUsername());

        /*
         * 判断该考试成绩发布
         */
        ExamKsDataCustom ksDataCustom = ksDataMapper.getExamKsDataByUuid(ksUuid);
        if (ksDataCustom.getResultPublishTime().getTime() < 0) {
            //已评分
            ksUserCustom.setState(5);
        } else {
            //已交卷
            ksUserCustom.setState(4);
        }

        /*
         * 交卷以后将信息设置为未读，代表有新的成绩
         */
        ksUserCustom.setReadStatus(0);

        //交卷
        ksUserMapper.updateKsUserSubmit(ksUserCustom);

        /*
         * 将用户错题添加到用户错题表
         */
        //没有错题直接返回
        if (wrongStIds == null)
            return;

        //添加用户错题
        for (Integer questionId : wrongStIds) {
            stWrongService.insertUserWrong(user.getId(), questionId);
        }

//		//根据用户id获取用户已存在的错题
//		List<ExamWrongCustom> wrs = wrongMapper.getWrongListByUserId(user.getId());
//
//		if(wrs != null && wrs.size() == 0) {
//			/*
//			 * 该用户原先不存在错题
//			 */
//			ExamWrongCustom wrongCustom = new ExamWrongCustom();
//			wrongCustom.setUserId(user.getId());
//			//将id数组转换成JSON串
//			wrongCustom.setStIds(JSONArray.fromObject(wrongStIds).toString());
//			//添加错题记录
//			wrongMapper.insertWrong(wrongCustom);
//		} else if(wrs != null && wrs.size() == 1) {
//			/*
//			 * 该用户已经存在错题
//			 */
//			ExamWrongCustom wrongCustom = wrs.get(0);
//			//先获取用户原先的错题
//			String lastIdsStr = wrongCustom.getStIds();
//			Integer[] lastIds = CommonUtils.JSONArrToIntegerArray(lastIdsStr);
//
//			//创建set集合用于保存新的ids数组
//			Set<Integer> ids = new HashSet<Integer>();
//			//将原先的错题添加到set中
//			for (Integer id : lastIds) {
//				ids.add(id);
//			}
//			//将新的错题添加到set中
//			for (Integer id : wrongStIds) {
//				ids.add(id);
//			}
//			//将set集合转换为json串
//			JSONArray jsonIds = JSONArray.fromObject(ids);
//			//将试题ids重新设置到po中
//			wrongCustom.setStIds(jsonIds.toString());
//			//修改该用户的错题记录
//			wrongMapper.updateStIdsByUserId(wrongCustom);
//		} else //该用户存在多条错题记录，与设计初衷不符
//			throw new ThrowsException("exam_wrong表存在异常！");
    }

    @Override
    public Integer getCountDownTime(Integer kuId) throws Exception {
        //获取用户考试信息
        ExamKsUserCustom ksUserCustom = ksUserMapper.getKsUserById(kuId);
        //获取考试信息
        ExamKsDataCustom ksDataCustom = ksUserCustom.getKsDataCustom();
        //考试开始时间
        Date beginTime = null;
        /*
         * 判断考试计时类型
         */
        if (ksDataCustom.getTimeType()) {
            //考试开始计时
            beginTime = ksDataCustom.getBeginTime();
        } else {
            //答卷开始计时
            beginTime = ksUserCustom.getBeginTime();
        }
        //当前时间
        Date now = new Date();

        //倒计时 = 答卷时长 - （当前时间 - 考试时间）
        double time = Math.ceil((ksDataCustom.getTotalTime() * 60 * 1000 - (now.getTime() - beginTime.getTime())) / 1000);
        System.out.println("当前时间：" + now.getTime());
        System.out.println("开始时间：" + beginTime.getTime());
        System.out.println("倒计时" + time);
        return (int) time;
    }

    @Override
    public List<ExamKsUserCustom> findResultListByVo(
            KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception {

        //获取总记录数
        Integer totalRecored = ksUserMapper.getExamResultCountByUserId(ksUserQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        ksUserQueryVo.setPageBean(pageBean);

        //条件查询
        return ksUserMapper.findExamResultByUserId(ksUserQueryVo);
    }

    @Override
    public Integer getNotReadResultByUserId(Integer userId) throws Exception {
        return ksUserMapper.getNotReadResultByUserId(userId);
    }

    @Override
    public void updateKsUserPublishResultByUserId(Integer userId)
            throws Exception {
        ksUserMapper.updateKsUserPublishResultByUserId(userId);
    }

    @Override
    public void updateResultReadedById(Integer userId) throws Exception {
        ksUserMapper.updateResultReadedById(userId);
    }

    @Override
    public Integer getKsUserCountByUserId(Integer userId) throws Exception {
        return ksUserMapper.getKsUserCountByUserId(userId);
    }

    @Override
    public Integer getUserDoQsnCountByUserId(Integer userId) throws Exception {
        Integer count = ksUserMapper.getUserDoQsnCountByUserId(userId);
        return count == null ? 0 : count;
    }

    @Override
    public List<ExamKsUserCustom> findKsUserByKsId(KsUserQueryVo vo,
                                                   PageBean pageBean) throws Exception {

        if (vo.getGroupId() == -1) {
            vo.setGroupIds(groupService.getChildGroupsIdByPid(1));
        } else {
            vo.setGroupIds(groupService.getChildGroupsIdByPid(vo.getGroupId()));
        }

        pageBean.setTotalRecored(ksUserMapper.getKsUserCountByKsId(vo));
        vo.setPageBean(pageBean);

        return ksUserMapper.findKsUserByKsId(vo);
    }

    @Override
    public void addUsersToExam(Integer ksId, Integer[] userIds,
                               SysUserCustom user) throws Exception {
        ExamKsDataCustom ksDataCustom = ksDataMapper.getExamKsDataById(ksId);

        //添加考试与用户对应关系信息表信息
        ExamKsUserCustom ksUserCustom = new ExamKsUserCustom();
        ksUserCustom.setKsId(ksDataCustom.getKsId());//考试id
        ksUserCustom.setSjId(ksDataCustom.getSjId());//试卷id
        ksUserCustom.setInsUser(user.getUsername());//添加者
        ksUserCustom.setInsDate(new Date());//添加时间

        if (!ksDataCustom.getUserSignupFlg()) {
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

    }

}
