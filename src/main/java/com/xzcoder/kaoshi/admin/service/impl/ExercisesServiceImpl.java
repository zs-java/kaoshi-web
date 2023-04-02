package com.xzcoder.kaoshi.admin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.ExercisesMapper;
import com.xzcoder.kaoshi.admin.mapper.StClassifyMapper;
import com.xzcoder.kaoshi.admin.mapper.StDanxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StDuoxuanMapper;
import com.xzcoder.kaoshi.admin.mapper.StKnowledgeMapper;
import com.xzcoder.kaoshi.admin.mapper.StLevelMapper;
import com.xzcoder.kaoshi.admin.mapper.StMainMapper;
import com.xzcoder.kaoshi.admin.mapper.StPanduanMapper;
import com.xzcoder.kaoshi.admin.mapper.StTiankongMapper;
import com.xzcoder.kaoshi.admin.service.ExercisesService;
import com.xzcoder.kaoshi.admin.service.GroupService;
import com.xzcoder.kaoshi.admin.service.StWrongService;
import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;
import com.xzcoder.kaoshi.po.ExamStDanxuanCustom;
import com.xzcoder.kaoshi.po.ExamStDuoxuanCustom;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;
import com.xzcoder.kaoshi.po.ExamStMainCustom;
import com.xzcoder.kaoshi.po.ExamStPanduanCustom;
import com.xzcoder.kaoshi.po.ExamStTiankongCustom;
import com.xzcoder.kaoshi.po.ExercisesCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.po.UserExerData;
import com.xzcoder.kaoshi.vo.ExercisesQueryVo;
import com.xzcoder.kaoshi.vo.StMainQueryVo;

/**
 * ExercisesServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class ExercisesServiceImpl implements ExercisesService {

    @Autowired
    private ExercisesMapper exercisesMapper;
    @Autowired
    private StClassifyMapper stClassifyMapper;
    @Autowired
    private StKnowledgeMapper stKnowledgeMapper;
    @Autowired
    private StLevelMapper stLevelMapper;
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
    private StWrongService stWrongService;
    @Autowired
    private GroupService groupService;

    @Override
    public int insertExercises(Integer[] classifyIds, Integer[] knowledgeIds,
                               SysUserCustom user) throws Exception {
        if (classifyIds.length == 0) {
            throw new CustomException("试题分类id集合不能为空！");
        }
        if (knowledgeIds.length == 0) {
            throw new CustomException("试题知识点id集合不能为空！");
        }

        /*
         * 如果类别id中包含-1，则获取全部id，即全部
         */
        List<Integer> classifyList = Arrays.asList(classifyIds);
        if (classifyList.contains(-1)) {
            classifyList = stClassifyMapper.getAllStClassifyIds();
        }
        List<Integer> knowledgeList = Arrays.asList(knowledgeIds);
        if (knowledgeList.contains(-1)) {
            knowledgeList = stKnowledgeMapper.getAllStKnowledgeIds();
        }

        //TODO 默认为所有试题难度
        List<Integer> levelList = stLevelMapper.getAllStLevelIds();


        Integer[] arr1 = CommonUtils.convertToArr(classifyList, Integer.class);
        Integer[] arr2 = CommonUtils.convertToArr(knowledgeList, Integer.class);
        Integer[] arr3 = CommonUtils.convertToArr(levelList, Integer.class);
        randomQuestionId(arr1, arr2, arr3);

        ExercisesCustom exerisesCustom = new ExercisesCustom();
        exerisesCustom.setUserId(user.getId());//用户id
        //获取当天日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        exerisesCustom.setDayDate(df.parse(df.format(new Date())));//当天日期
        exerisesCustom.setStClassifys(JSONArray.fromObject(classifyList).toString());
        exerisesCustom.setStKnowledges(JSONArray.fromObject(knowledgeList).toString());
        exerisesCustom.setStLevels(JSONArray.fromObject(levelList).toString());
        exerisesCustom.setTotalCount(0);
        exerisesCustom.setRightCount(0);
        exerisesCustom.setUuid(CommonUtils.uuid());

        //添加纪录
        exercisesMapper.insertExercises(exerisesCustom);
        //返回自增id
        return exerisesCustom.getId();
    }

    @Override
    public JSONObject getNextQuestion(Integer exerId) throws Exception {
        if (exerId == null || exerId <= 0) {
            throw new CustomException("用户刷题编号不正确！");
        }

        //根据id获取用户刷题信息
        ExercisesCustom exerisesCustom = exercisesMapper.getExercisesById(exerId);


        Integer[] classifyIds = CommonUtils.JSONArrToIntegerArray(exerisesCustom.getStClassifys());
        Integer[] knowledgeIds = CommonUtils.JSONArrToIntegerArray(exerisesCustom.getStKnowledges());
        Integer[] levelIds = CommonUtils.JSONArrToIntegerArray(exerisesCustom.getStLevels());
        int questionId = randomQuestionId(classifyIds, knowledgeIds, levelIds);

        //获取该题目的详细信息并封装成json数据返回
        ExamStMainCustom stMainCustom = stMainMapper.getStMainById(questionId);

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
                ExamStDanxuanCustom danxuan = danxuanMapper.getStDanxuanById(questionId);
                json.put("title", danxuan.getTitle());
                json.put("options", danxuan.getOptions());
                json.put("rightKey", danxuan.getRightKey());
                json.put("detail", danxuan.getDetail());
                break;
            case 2://多选题
                ExamStDuoxuanCustom duoxuan = duoxuanMapper.getStDuoxuanById(questionId);
                json.put("title", duoxuan.getTitle());
                json.put("options", duoxuan.getOptions());
                json.put("rightKey", duoxuan.getRightKey());
                json.put("detail", duoxuan.getDetail());
                break;
            case 3://判断题
                ExamStPanduanCustom panduan = panduanMapper.getStPanduanById(questionId);
                json.put("title", panduan.getTitle());
                json.put("rightKey", panduan.getRightKey() ? 1 : 0);
                json.put("detail", panduan.getDetail());
                break;
            case 4://填空题
                ExamStTiankongCustom tiankong = tiankongMapper.getStTiankongById(questionId);
                json.put("title", tiankong.getTitle());
                json.put("rightKey", tiankong.getRightKey());
                json.put("detail", tiankong.getDetail());
                break;
            default:
                throw new CustomException("试题类型错误！");
        }

        //将试题信息的json数据返回
        return json;
    }

    @Override
    public void updateExercises(Integer exerId, Integer questionId,
                                Boolean flag, SysUserCustom user) throws Exception {
        //根据刷题记录编号获取刷题记录信息
        ExercisesCustom exercisesCustom = exercisesMapper.getExercisesById(exerId);

        //判断用户id是否正确
        if ((int) user.getId() != (int) exercisesCustom.getUserId()) {
            throw new CustomException("非法操作，刷题编号错误！");
        }

        String stIds = exercisesCustom.getStIds();
        if (stIds == null) {
            JSONArray arr = new JSONArray();
            arr.add(questionId);
            stIds = arr.toString();
        } else {
            JSONArray arr = JSONArray.fromObject(stIds);
            arr.add(questionId);
            stIds = arr.toString();
        }
        exercisesCustom.setStIds(stIds);

        //刷题数量加1
        exercisesCustom.setTotalCount(exercisesCustom.getTotalCount() + 1);

        Integer rightCount = exercisesCustom.getRightCount();
        /*
         * 正确：将正确数加1
         * 错误：添加用户错题
         */
        if (flag) {
            exercisesCustom.setRightCount(rightCount + 1);
        } else {
            stWrongService.insertUserWrong(user.getId(), questionId);
        }

        //更新刷题记录信息
        exercisesMapper.updateExercises(exercisesCustom);
    }

    @Override
    public ExercisesCustom getExercisesById(Integer exerId) throws Exception {
        return exercisesMapper.getExercisesById(exerId);
    }

    @Override
    public List<ExercisesCustom> getExersByUser(SysUserCustom user)
            throws Exception {
        List<ExercisesCustom> list = exercisesMapper.getExersByUserId(user.getId());

        fullClassifyName(list);
        return list;
    }

    @Override
    public List<ExercisesCustom> findExersByUserPage(SysUserCustom user, PageBean pb) throws Exception {

        pb.setTotalRecored(exercisesMapper.getExersCountByUserId(user.getId()));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getId());
        map.put("pb", pb);
        List<ExercisesCustom> list = exercisesMapper.findExersByUserIdPage(map);

        fullClassifyName(list);
        return list;
    }

    @Override
    public List<UserExerData> findUserExerDataByVo(ExercisesQueryVo vo,
                                                   PageBean pageBean) throws Exception {

        Integer[] groupIds = groupService.getChildGroupsIdByPid(vo.getGroupId());
        vo.setGroupIds(groupIds);

        if ((int) vo.getType() == 0) {
            Integer totalRecord = exercisesMapper.getUserExerDatasCountByVo(vo);
            pageBean.setTotalRecored(totalRecord);
            vo.setPageBean(pageBean);

            List<UserExerData> list = exercisesMapper.findUserExerDatasByVo(vo);
            if (vo.getDate() != null && !"".equals(vo.getDate())) {
                Collections.sort(list);
            }

            return list;
        } else {
            Integer totalRecord = exercisesMapper.getNotExerUsersCount(vo);
            pageBean.setTotalRecored(totalRecord);
            vo.setPageBean(pageBean);

            List<UserExerData> list = exercisesMapper.findNotExerUsers(vo);
            return list;
        }
    }

    @Override
    public List<ExercisesCustom> findExercisesListByVo(
            ExercisesQueryVo vo, PageBean pageBean)
            throws Exception {
        Integer totalRecored = exercisesMapper.getExercisesCountByVo(vo);
        pageBean.setTotalRecored(totalRecored);
        vo.setPageBean(pageBean);
        List<ExercisesCustom> list = exercisesMapper.findExercisesListByVo(vo);
        fullClassifyName(list);
        return list;
    }

    /**
     * 填充分类名称
     *
     * @param list
     * @throws Exception
     */
    private void fullClassifyName(List<ExercisesCustom> list) throws Exception {
        Map<Integer, String> classifyMap = new HashMap<Integer, String>();
        for (ExamStClassifyCustom classify : stClassifyMapper.getAllStClassify()) {
            classifyMap.put(classify.getClassifyId(), classify.getName());
        }
        Map<Integer, String> knowledgeMap = new HashMap<Integer, String>();
        for (ExamStKnowledgeCustom knowledge : stKnowledgeMapper.getAllStKnowledge()) {
            knowledgeMap.put(knowledge.getClassifyId(), knowledge.getName());
        }
        for (ExercisesCustom exercisesCustom : list) {
            Integer[] classifyIds = CommonUtils.JSONArrToIntegerArray(exercisesCustom.getStClassifys());
            StringBuilder classifyNames = new StringBuilder();
            for (Integer classifyId : classifyIds) {
                classifyNames.append(classifyMap.get(classifyId) + "，");
            }
            Integer[] knowledgeIds = CommonUtils.JSONArrToIntegerArray(exercisesCustom.getStKnowledges());
            StringBuilder knowledgeNames = new StringBuilder();
            for (Integer knowledgeId : knowledgeIds) {
                knowledgeNames.append(knowledgeMap.get(knowledgeId) + "，");
            }
            exercisesCustom.setClassifyName(classifyNames.toString());
            exercisesCustom.setKnowledgeName(knowledgeNames.toString());
        }
    }

    @Override
    public Integer getExerTotalCountByUserId(Integer userId) throws Exception {
        Integer count = exercisesMapper.getExerTotalCountByUserId(userId);
        return count == null ? 0 : count;
    }

    private Integer randomQuestionId(Integer[] classifyIds, Integer[] knowledgeIds, Integer[] levelIds) throws Exception {

        StMainQueryVo stMainQueryVo = new StMainQueryVo();
        stMainQueryVo.setClassifyIds(classifyIds);
        stMainQueryVo.setKnowledgeIds(knowledgeIds);
        stMainQueryVo.setLevelIds(levelIds);

        //获取所有匹配的试题id的集合
        List<Integer> stIds = stMainMapper.findStIdListByVo(stMainQueryVo);

        if (stIds == null || stIds.size() == 0) {
            throw new CustomException("不存在该类题目！");
        }

        //随机抽取一题
        int random = (int) (Math.random() * stIds.size());
        System.out.println("stIds:" + Arrays.toString(stIds.toArray()));
        System.out.println("random:" + random);
        return stIds.get(random);
    }

}
