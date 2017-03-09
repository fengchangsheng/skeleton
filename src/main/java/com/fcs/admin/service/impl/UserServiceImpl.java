package com.fcs.admin.service.impl;

import com.fcs.admin.entity.Role;
import com.fcs.admin.entity.User;
import com.fcs.admin.mapper.*;
import com.fcs.admin.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucare
 * @since 2017-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<Role> findRolePermissions(long uid) {
        List<Role> roleIdList = userRoleMapper.findRoleListByUserId(uid);
        for (Role role : roleIdList) {
            Set<String> everyRolePer = rolePermissionMapper.findPermissions(role.getId());
            role.setPerNameSet(everyRolePer);
        }
        return roleIdList;
    }
}
