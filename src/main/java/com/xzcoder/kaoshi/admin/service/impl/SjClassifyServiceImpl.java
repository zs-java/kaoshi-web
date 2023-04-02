package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xzcoder.kaoshi.admin.service.SjClassifyService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.SjClassifyMapper;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamSjClassifyCustom;

/**
 * SjClassifyServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class SjClassifyServiceImpl implements SjClassifyService {

    @Autowired
    private SjClassifyMapper sjClassifyMapper;

    @Override
    public List<ExamSjClassifyCustom> getAllSjClassify() throws Exception {
        return sjClassifyMapper.getAllSjClassify();
    }

    @Override
    public void renameSjClassifyById(Integer id, String newName)
            throws Exception {
        if (newName == null || "".equals(newName)) {
            throw new CustomException("节点名称不能为空！");
        }

        ExamSjClassifyCustom sjClassifyCustom = new ExamSjClassifyCustom();
        sjClassifyCustom.setClassifyId(id);
        sjClassifyCustom.setName(newName);

        sjClassifyMapper.updateSjClassifyNameById(sjClassifyCustom);
    }

    @Override
    public Integer insertSjClassify(Integer pid, String newName)
            throws Exception {
        if (pid == null) {
            pid = 0;
        }
        if (newName == null || "".equals(newName)) {
            newName = "new node";
        }

        ExamSjClassifyCustom sjClassifyCustom = new ExamSjClassifyCustom();
        sjClassifyCustom.setPid(pid);
        sjClassifyCustom.setName(newName);
        //TODO 封装sort信息

        //添加
        sjClassifyMapper.insertSjClassify(sjClassifyCustom);

        //返回自增主键
        return sjClassifyCustom.getClassifyId();
    }

    @Override
    public void deleteSjClassifyById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new ThrowsException("删除试题分组时，分组id不能为空！");
        }

        //获取该节点的所有子节点id
        Integer[] childIds = getChildIdsByPid(id);

        //TODO 删除该表关联表的对应记录

        //删除该节点下的所有子节点
        if (childIds != null && childIds.length != 0) {
            sjClassifyMapper.deleteSjClassifyByIdArray(childIds);
        }

        //删除该节点
        sjClassifyMapper.deleteSjClassifyById(id);
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
            List<Integer> cids = sjClassifyMapper.getChildIdByPid(pid);
            if (cids != null && cids.size() > 0) {
                pids.addAll(cids);
            }
            fullChildIdsByPid(cids);
        }
    }

    @Override
    public List<ExamSjClassifyCustom> getAllSjClassifyTree() throws Exception {
        List<ExamSjClassifyCustom> sjcs = sjClassifyMapper.getSjClassifyByPid(0);
        full(sjcs);
        return sjcs;
    }

    /**
     * 通过递归查询并填充父分组的子分组
     *
     * @param sjcs
     * @throws Exception
     */
    private void full(List<ExamSjClassifyCustom> sjcs) throws Exception {
        for (int i = 0; i < sjcs.size(); i++) {
            ExamSjClassifyCustom sjc = sjcs.get(i);
            Integer classifyId = sjc.getClassifyId();
            List<ExamSjClassifyCustom> childs = sjClassifyMapper.getSjClassifyByPid(classifyId);
            sjc.setChildren(childs);
            if (childs != null) {
                full(sjc.getChildren());
            }
        }
    }

}
