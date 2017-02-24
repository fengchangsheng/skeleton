package com.fcs.admin.service.impl;

import com.fcs.admin.entity.User;
import com.fcs.admin.mapper.UserMapper;
import com.fcs.admin.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
	
}
