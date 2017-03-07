package com.fcs.admin.service;

import com.fcs.admin.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucare
 * @since 2017-02-24
 */
public interface IUserService extends IService<User> {

    User findByName(String name);

}
