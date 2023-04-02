package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.StKnowledgeMapper;
import com.xzcoder.kaoshi.admin.service.StKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStKnowledgeCustom;

/**
 * StKnowledgeServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StKnowledgeServiceImpl implements StKnowledgeService {

    @Autowired
    private StKnowledgeMapper stKnowledgeMapper;

    @Override
    public List<ExamStKnowledgeCustom> getAllStKnowledge() throws Exception {
        return stKnowledgeMapper.getAllStKnowledge();
    }

    @Override
    public void renameStKnowledge(Integer id, String newName) throws Exception {

        if (newName == null || "".equals(newName)) {
            throw new CustomException("节点名称不能为空！");
        }

        ExamStKnowledgeCustom stKnowledgeCustom = new ExamStKnowledgeCustom();
        stKnowledgeCustom.setClassifyId(id);
        stKnowledgeCustom.setName(newName);

        stKnowledgeMapper.updateStKnowledgeNameById(stKnowledgeCustom);
    }

    @Override
    public Integer insertStKnowledge(Integer pid, String newName)
            throws Exception {
        if (pid == null) {
            pid = 0;
        }
        if (newName == null || "".equals(newName)) {
            newName = "new node";
        }

        ExamStKnowledgeCustom stKnowledgeCustom = new ExamStKnowledgeCustom();
        stKnowledgeCustom.setPid(pid);
        stKnowledgeCustom.setName(newName);
        //TODO 封装sort信息

        //添加
        stKnowledgeMapper.insertStKnowledge(stKnowledgeCustom);

        //返回自增主键
        return stKnowledgeCustom.getClassifyId();
    }

    @Override
    public void deleteStKnowledgeById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new ThrowsException("删除试题分组时，分组id不能为空！");
        }

        //获取该节点的所有子节点id
        Integer[] childIds = getChildIdsByPid(id);

        //TODO 删除该表关联表的对应记录

        //删除该节点下的所有子节点
        if (childIds != null && childIds.length != 0) {
            stKnowledgeMapper.deleteStKnowledgeByIdArray(childIds);
        }

        //删除该节点
        stKnowledgeMapper.deleteStKnowledgeById(id);
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
            List<Integer> cids = stKnowledgeMapper.getChildIdByPid(pid);
            if (cids != null && cids.size() > 0) {
                pids.addAll(cids);
            }
            fullChildIdsByPid(cids);
        }
    }

    @Override
    public List<ExamStKnowledgeCustom> getAllStKnowledgeTree() throws Exception {
        List<ExamStKnowledgeCustom> stks = stKnowledgeMapper.getStKnowledgesByPid(0);
        full(stks);
        return stks;
    }

    /**
     * 通过递归查询并填充父分组的子分组
     *
     * @param stks
     * @throws Exception
     */
    private void full(List<ExamStKnowledgeCustom> stks) throws Exception {
        for (int i = 0; i < stks.size(); i++) {
            ExamStKnowledgeCustom stk = stks.get(i);
            Integer classifyId = stk.getClassifyId();
            List<ExamStKnowledgeCustom> childs = stKnowledgeMapper.getStKnowledgesByPid(classifyId);
            stk.setChildren(childs);
            if (childs != null) {
                full(stk.getChildren());
            }
        }
    }


}
