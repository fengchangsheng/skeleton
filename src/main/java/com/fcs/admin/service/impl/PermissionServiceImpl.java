package com.fcs.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.Permission;
import com.fcs.admin.mapper.PermissionMapper;
import com.fcs.admin.mapper.RoleMapper;
import com.fcs.admin.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Page<Permission> selectPermPage(Page<Permission> page) {
        page.setRecords(permissionMapper.selectPermList(page));
        return page;
    }
}
