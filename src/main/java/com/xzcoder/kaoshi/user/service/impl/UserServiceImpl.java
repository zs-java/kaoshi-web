package com.xzcoder.kaoshi.user.service.impl;

import java.util.List;

import com.xzcoder.kaoshi.common.po.PageBean;
import com.xzcoder.kaoshi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.KsUserMapper;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.po.ExamKsUserCustom;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.user.mapper.UMapper;
import com.xzcoder.kaoshi.vo.KsUserQueryVo;

public class UserServiceImpl implements UserService {

    @Autowired
    private UMapper uMapper;
    @Autowired
    private KsUserMapper ksUserMapper;

    @Override
    public SysUserCustom getUserByUnameAndPwd(String username, String password)
            throws Exception {

        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            throw new CustomException("用户名或密码不能为空！");
        }
        //根据用户名密码获取用户信息
        SysUserCustom user = uMapper.getUserByUnameAndPwd(username, password);
        //如果user为null，即登陆失败返回null
        if (user == null) {
            return null;
        }

        //登陆成功，将登陆次数+1
        uMapper.updateLoginCountAddById(user.getId());
        user.setLoginCount(user.getLoginCount() + 1);

        //返回用户信息
        return user;
    }

    @Override
    public List<ExamKsUserCustom> findKsUserListByUidAndState(
            KsUserQueryVo ksUserQueryVo, PageBean pageBean) throws Exception {

        //获取总记录数
        Integer totalRecored = ksUserMapper.getKsUserCountByUidAndState(ksUserQueryVo);
        //将总记录数设置到PageBean
        pageBean.setTotalRecored(totalRecored);
        //将pageBean设置到Vo
        ksUserQueryVo.setPageBean(pageBean);

        //组合条件查询
        return ksUserMapper.findKsUserByUidAndState(ksUserQueryVo);
    }

}
