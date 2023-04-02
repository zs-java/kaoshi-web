package com.xzcoder.kaoshi.admin.service.impl;

import java.util.List;

import com.xzcoder.kaoshi.admin.service.StTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.StTypeMapper;
import com.xzcoder.kaoshi.po.ExamStTypeCustom;

/**
 * StTypeServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class StTypeServiceImpl implements StTypeService {

    @Autowired
    private StTypeMapper stTypeMapper;

    @Override
    public List<ExamStTypeCustom> getAllStTypes() throws Exception {
        return stTypeMapper.getAllStTypes();
    }

}
