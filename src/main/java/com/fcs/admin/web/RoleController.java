package com.fcs.admin.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.Role;
import com.fcs.admin.service.IRoleService;
import com.fcs.admin.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lucare
 * @since 2017-03-06
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("admin:role:view")
    @RequestMapping("/index")
    public String index() {
        return "admin/role/list";
    }

    @RequiresPermissions("admin:role:view")
    @RequestMapping("/list")
    @ResponseBody
    public Page<Role> list() {
        Page<Role> page = roleService.selectRolePage(new Page<Role>(0, 12));
        return page;
    }
	
}
