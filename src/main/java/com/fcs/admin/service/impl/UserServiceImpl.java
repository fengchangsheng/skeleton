package com.fcs.admin.service.impl;

import com.fcs.admin.entity.User;
import com.fcs.admin.mapper.PermissionMapper;
import com.fcs.admin.mapper.RoleMapper;
import com.fcs.admin.mapper.UserMapper;
import com.fcs.admin.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public Map<String,Object> findRolePermissions(long uid) {
        List<Long> roleIdList = roleMapper.findRoleIdListByUserId(uid);
        for (Long id : roleIdList) {
            System.out.println("roleId is " + id);
        }
        return null;
    }
}
