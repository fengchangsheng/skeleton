package com.fcs.admin.mapper;

import com.fcs.admin.entity.Role;
import com.fcs.admin.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Lucare
 * @since 2017-03-08
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> findRoleListByUserId(long uid);

}