package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xzcoder.kaoshi.admin.service.StLevelService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StLevelMapper;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStLevelCustom;

/**
 * StLevelServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StLevelServiceImpl implements StLevelService {

    @Autowired
    private StLevelMapper stLevelMapper;

    @Override
    public List<ExamStLevelCustom> getAllStLevel() throws Exception {
        return stLevelMapper.getAllStLevel();
    }

    @Override
    public void renameStLevelById(Integer levelId, String newName)
            throws Exception {
        if (levelId == null) {
            throw new CustomException("试题难度分类编号不能为空！");
        }
        if (newName == null || "".equals(newName)) {
            throw new CustomException("试题难度分类名称不能为空！");
        }

        ExamStLevelCustom stLevelCustom = new ExamStLevelCustom();
        stLevelCustom.setLevelId(levelId);
        stLevelCustom.setName(newName);

        //重命名
        stLevelMapper.updateStLevelNameById(stLevelCustom);
    }

    @Override
    public Integer insertStLevel(Integer pid, String newName) throws Exception {
        if (pid == null) {
            pid = 0;
        }
        if (newName == null || "".equals(newName)) {
            newName = "new node";
        }

        ExamStLevelCustom stLevelCustom = new ExamStLevelCustom();
        stLevelCustom.setPid(pid);
        stLevelCustom.setName(newName);
        //TODO 封装sort信息

        //添加节点
        stLevelMapper.insertStLevel(stLevelCustom);

        //返回自增主键
        return stLevelCustom.getLevelId();
    }

    @Override
    public void deleteStLevel(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new ThrowsException("删除节点时，分组id不能为空！");
        }

        //根据id获取该节点下的 所有子节点
        Integer[] childIds = getChildIdsByPid(id);

        //TODO 删除该表关联表的对应记录

        //删除该节点下的所有子节点
        if (childIds != null && childIds.length != 0) {
            stLevelMapper.deleteStLevelByIdArray(childIds);
        }

        //删除该节点
        stLevelMapper.deleteStLevelById(id);
    }

    /**
     * 根据节点id获取所有子节点的id集合
     *
     * @param pid
     * @return
     * @throws Exception
     */
    private Integer[] getChildIdsByPid(Integer pid) throws Exception {
        List<Integer> pids = new ArrayList<Integer>();
        pids.add(pid);
        fullChildIdsByPid(pids);
        Integer[] arr = new Integer[pids.size()];
        arr = pids.toArray(arr);
        return arr;
    }

    /**
     * 递归算法
     * 遍历节点id集合获取该节点下一层节点id集合
     * 将查询到的下一层节点id集合填充到参数传入的集合中
     *
     * @param pids
     * @throws Exception
     */
    private void fullChildIdsByPid(List<Integer> pids) throws Exception {
        for (int i = 0; i < pids.size(); i++) {
            Integer pid = pids.get(i);
            List<Integer> cids = stLevelMapper.getChildIdByPid(pid);
            if (cids != null && cids.size() > 0) {
                pids.addAll(cids);
            }
            fullChildIdsByPid(cids);
        }
    }

    @Override
    public List<ExamStLevelCustom> getAllStLevelTree() throws Exception {
        List<ExamStLevelCustom> stts = stLevelMapper.getStLevelByPid(0);
        full(stts);
        return stts;
    }

    /**
     * 通过递归查询并填充父分组的子分组
     *
     * @param stts
     * @throws Exception
     */
    private void full(List<ExamStLevelCustom> stts) throws Exception {
        for (int i = 0; i < stts.size(); i++) {
            ExamStLevelCustom stt = stts.get(i);
            Integer stLevelId = stt.getLevelId();
            List<ExamStLevelCustom> childs = stLevelMapper.getStLevelByPid(stLevelId);
            stt.setChildren(childs);
            if (childs != null) {
                full(stt.getChildren());
            }
        }
    }

}
