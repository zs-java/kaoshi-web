package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StClassifyMapper;
import com.xzcoder.kaoshi.admin.service.StClassifyService;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamStClassifyCustom;

/**
 * StClassifyServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StClassifyServiceImpl implements StClassifyService {

    @Autowired
    private StClassifyMapper stClassifyMapper;

    @Override
    public List<ExamStClassifyCustom> getAllStClassify() throws Exception {
        return stClassifyMapper.getAllStClassify();
    }

    @Override
    public void renameStClassifyById(Integer classifyId, String newName)
            throws Exception {
        if (newName == null || "".equals(newName)) {
            throw new CustomException("节点名称不能为空！");
        }

        ExamStClassifyCustom stClassifyCustom = new ExamStClassifyCustom();
        stClassifyCustom.setClassifyId(classifyId);
        stClassifyCustom.setName(newName);

        stClassifyMapper.updateStClassifyNameById(stClassifyCustom);
    }

    @Override
    public Integer insertStClassify(Integer pid, String newName) throws Exception {
        if (pid == null) {
            pid = 0;
        }
        if (newName == null || "".equals(newName)) {
            newName = "new node";
        }

        ExamStClassifyCustom stClassifyCustom = new ExamStClassifyCustom();
        stClassifyCustom.setPid(pid);
        stClassifyCustom.setName(newName);
        //TODO 封装sort信息

        //添加
        stClassifyMapper.insertStClassify(stClassifyCustom);

        //返回自增主键
        return stClassifyCustom.getClassifyId();
    }

    @Override
    public void deleteStClassifyById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new ThrowsException("删除试题分组时，分组id不能为空！");
        }

        //获取该节点的所有子节点id
        Integer[] childIds = getChildIdsByPid(id);

        //TODO 删除该表关联表的对应记录

        //删除该节点下的所有子节点
        if (childIds != null && childIds.length != 0) {
            stClassifyMapper.deleteStClassifyByIdArray(childIds);
        }

        //删除该节点
        stClassifyMapper.deleteStClassifyById(id);
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
            List<Integer> cids = stClassifyMapper.getChildIdByPid(pid);
            if (cids != null && cids.size() > 0) {
                pids.addAll(cids);
            }
            fullChildIdsByPid(cids);
        }
    }

    @Override
    public List<ExamStClassifyCustom> getAllStClassifyTree() throws Exception {
        List<ExamStClassifyCustom> stcs = stClassifyMapper.getStClassifysByPid(0);
        full(stcs);
        return stcs;
    }

    /**
     * 通过递归查询并填充父分组的子分组
     *
     * @param stcs
     * @throws Exception
     */
    private void full(List<ExamStClassifyCustom> stcs) throws Exception {
        for (int i = 0; i < stcs.size(); i++) {
            ExamStClassifyCustom stc = stcs.get(i);
            Integer stClassifyId = stc.getClassifyId();
            List<ExamStClassifyCustom> childs = stClassifyMapper.getStClassifysByPid(stClassifyId);
            stc.setChildStClassifys(childs);
            if (childs != null) {
                full(stc.getChildStClassifys());
            }
        }
    }

}
