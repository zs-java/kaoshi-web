package com.xzcoder.kaoshi.admin.service.impl;

import java.util.Date;
import java.util.List;

import com.xzcoder.kaoshi.admin.mapper.UserMapper;
import com.xzcoder.kaoshi.admin.service.GroupService;
import com.xzcoder.kaoshi.admin.service.UserService;
import com.xzcoder.kaoshi.common.po.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzcoder.kaoshi.admin.mapper.ExercisesMapper;
import com.xzcoder.kaoshi.admin.mapper.KsUserMapper;
import com.xzcoder.kaoshi.admin.mapper.StWrongMapper;
import com.xzcoder.kaoshi.common.utils.CommonUtils;
import com.xzcoder.kaoshi.exception.CustomException;
import com.xzcoder.kaoshi.exception.ThrowsException;
import com.xzcoder.kaoshi.exception.UsernameExistsException;
import com.xzcoder.kaoshi.po.SysUserCustom;
import com.xzcoder.kaoshi.vo.UserQueryVo;

/**
 * UserServiceImpl
 *
 * @author <a href="https://xzcoder.com">朱帅</a>
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private KsUserMapper ksUserMapper;
    @Autowired
    private StWrongMapper stWrongMapper;
    @Autowired
    private ExercisesMapper exercisesMapper;

    @Override
    public List<SysUserCustom> findUsersByUserQueryVo(UserQueryVo userQueryVo)
            throws Exception {

        //获取总记录数
        int totalRecored = userMapper.getUsersCountByUserQueryVo(userQueryVo);
        //封装分页信息
        PageBean page = userQueryVo.getPageBean();
        page.setTotalRecored(totalRecored);
        //将分页信息 重新设置到userQueryVo
        userQueryVo.setPageBean(page);

        //获取该分组的所有子分组，并封装成集合
        if (userQueryVo.getGroupCustom() != null && userQueryVo.getGroupCustom().getGroupId() != null) {
            Integer[] groupIds = groupService.getChildGroupsIdByPid(userQueryVo.getGroupCustom().getGroupId());
            userQueryVo.setGroupIds(groupIds);
        }
        return userMapper.findUsersByUserQueryVo(userQueryVo);
    }

    @Override
    public List<SysUserCustom> getAllUsers() throws Exception {
        return userMapper.getAllUsers();
    }

    @Override
    public void insertUser(SysUserCustom userCustom) throws Exception {
        //判断用户名是否存在
        int count = userMapper.getUserCountByUsername(userCustom.getUsername());
        if (count != 0) {
            throw new UsernameExistsException();
        }

        //封装user信息
        userCustom.setIcon("default/default_user_icon.jpg");
        userCustom.setUuid(CommonUtils.uuid());
        userCustom.setRegDate(new Date());
        userMapper.insertUser(userCustom);
    }

    @Override
    public void deleteUserList(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            ksUserMapper.deleteReadlyByUserId(id);
            stWrongMapper.deleteWrongByUserId(id);
            exercisesMapper.deleteExersByUserId(id);
            userMapper.deleteUserById(id);
        }
    }

    @Override
    public void deleteUserById(Integer userId) throws Exception {
        ksUserMapper.deleteReadlyByUserId(userId);
        stWrongMapper.deleteWrongByUserId(userId);
        exercisesMapper.deleteExersByUserId(userId);
        userMapper.deleteUserById(userId);
    }

    @Override
    public SysUserCustom getUserById(Integer userId) throws Exception {
        return userMapper.getUserById(userId);
    }

    @Override
    public void updateUserById(Integer userId, SysUserCustom userCustom) throws Exception {
        if (userId == null) {
            throw new CustomException("没有传入用户编号！");
        }
        userCustom.setId(userId);
        userMapper.updateUserById(userCustom);
    }

//	@Override
//	public void deleteUserByGroupId(Integer groupId) throws Exception {
//		userMapper.deleteUserByGroupId(groupId);
//	}
//
//	@Override
//	public void deleteUserByGroupIdArray(Integer[] groupIds) throws Exception {
//		userMapper.deleteUserByGroupIdArray(groupIds);
//	}

    @Override
    public List<SysUserCustom> findUserListByIds(Integer[] ids, Integer[] groupIds)
            throws Exception {
        if (ids == null) {
            throw new ThrowsException("用户编号集合不能为空！");
        }
        return userMapper.findUserListByIds(ids, groupIds);
    }

    @Override
    public List<SysUserCustom> findUserNotExamByKsId(UserQueryVo vo,
                                                     PageBean pageBean) throws Exception {

        if (vo.getGroupId() == -1) {
            vo.setGroupIds(groupService.getChildGroupsIdByPid(1));
        } else {
            vo.setGroupIds(groupService.getChildGroupsIdByPid(vo.getGroupId()));
        }

        pageBean.setTotalRecored(userMapper.getUserNotInExamByVo(vo));
        vo.setPageBean(pageBean);

        return userMapper.findUserNotInExamByVo(vo);
    }

}
