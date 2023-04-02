package com.xzcoder.kaoshi.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.KsClassifyMapper;
import com.xzcoder.kaoshi.admin.service.KsClassifyService;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.po.ExamKsClassifyCustom;

/**
 * KsClassifyServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class KsClassifyServiceImpl implements KsClassifyService {

    @Autowired
    private KsClassifyMapper ksClassifyMapper;

    @Override
    public List<ExamKsClassifyCustom> getAllKsClassify() throws Exception {
        return ksClassifyMapper.getAllKsClassify();
    }

    @Override
    public void renameKsClassifyNameById(Integer id, String newName)
            throws Exception {
        if (newName == null || "".equals(newName)) {
            throw new CustomException("节点名称不能为空！");
        }

        ExamKsClassifyCustom ksClassifyCustom = new ExamKsClassifyCustom();
        ksClassifyCustom.setClassifyId(id);
        ksClassifyCustom.setName(newName);

        //重命名
        ksClassifyMapper.updateKsClassifyNameById(ksClassifyCustom);
    }

    @Override
    public Integer insertKsClassify(Integer pid, String newName)
            throws Exception {
        if (pid == null) {
            pid = 0;
        }
        if (newName == null || "".equals(newName)) {
            newName = "new node";
        }

        ExamKsClassifyCustom ksClassifyCustom = new ExamKsClassifyCustom();
        ksClassifyCustom.setPid(pid);
        ksClassifyCustom.setName(newName);
        //TODO 封装sort信息

        //添加
        ksClassifyMapper.insertKsClassify(ksClassifyCustom);

        //返回自增主键
        return ksClassifyCustom.getClassifyId();
    }

    @Override
    public void deleteKsClassify(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new ThrowsException("删除试题分组时，分组id不能为空！");
        }

        //获取该节点的所有子节点id
        Integer[] childIds = getChildIdsByPid(id);

        //TODO 删除该表关联表的对应记录

        //删除该节点下的所有子节点
        if (childIds != null && childIds.length != 0) {
            ksClassifyMapper.deleteKsClassifyByIdArray(childIds);
        }

        //删除该节点
        ksClassifyMapper.deleteKsClassifyById(id);
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
            List<Integer> cids = ksClassifyMapper.getChildIdByPid(pid);
            if (cids != null && cids.size() > 0) {
                pids.addAll(cids);
            }
            fullChildIdsByPid(cids);
        }
    }

    @Override
    public List<ExamKsClassifyCustom> getAllKsClassifyTree() throws Exception {
        List<ExamKsClassifyCustom> kscs = ksClassifyMapper.getKsClassifyByPid(0);
        full(kscs);
        return kscs;
    }

    /**
     * 通过递归查询并填充父分组的子分组
     *
     * @param kscs
     * @throws Exception
     */
    private void full(List<ExamKsClassifyCustom> kscs) throws Exception {
        for (int i = 0; i < kscs.size(); i++) {
            ExamKsClassifyCustom ksc = kscs.get(i);
            Integer classifyId = ksc.getClassifyId();
            List<ExamKsClassifyCustom> childs = ksClassifyMapper.getKsClassifyByPid(classifyId);
            ksc.setChildren(childs);
            if (childs != null) {
                full(ksc.getChildren());
            }
        }
    }
}
