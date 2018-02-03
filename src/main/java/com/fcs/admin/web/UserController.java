package com.fcs.admin.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.fcs.admin.entity.User;
import com.fcs.admin.service.IUserService;
import com.fcs.common.constant.AuthConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
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
 * @since 2017-02-24
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequiresPermissions("admin:user:view")
    @RequestMapping("/index")
    public String index() {
        return "admin/user/list";
    }

    @RequiresPermissions("admin:user:view")
    @RequestMapping("/list")
    @ResponseBody
    public Page<User> list() {
        Page<User> page = userService.selectPage(new Page<User>(0, 12));
        return page;
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(long id, int state) {
        User user = new User();
        user.setId(id);
        user.setState(state);
        userService.updateById(user);
        return "success";
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(long id) {
        User user = userService.selectById(id);
        return user;
    }

    @RequestMapping("/update")
    public String update(User user) {
        boolean isSuccc = userService.updateById(user);
        if (isSuccc) {
            return "redirect:index";
        }
        return "error";
    }

    @RequestMapping("/deleteOne")
    @ResponseBody
    public int deleteOne(long id) {
        boolean isSucc = userService.deleteById(id);
        return isSucc ? 1 : 0;
    }

    @RequestMapping("/toCreate")
    public String toCreate(User user) {
//        String md5Pwd = new Md5Hash(user.getPassword()).toString();
//        user.setPassword(md5Pwd);
        user.insert();
        return "toCreate";
    }

    @RequestMapping("/create")
    public Page<User> create(User user) {
        user.insert();
        return null;
    }

    /**
     * AR 部分测试
     */
    @RequestMapping("/test")
    public Page<User> test() {
        User user = new User("testAr", "123", "13532659875");
        System.err.println("删除所有：" + user.delete(null));
        user.insert();
        System.err.println("查询插入结果：" + user.selectById().toString());
        user.setUsername("mybatis-plus-ar");
        System.err.println("更新：" + user.updateById());
        return user.selectPage(new Page<User>(0, 12), null);
    }

    /**
     * 增删改查 CRUD
     */
    @RequestMapping("/test1")
    public User test1() {
        System.err.println("删除一条数据：" + userService.deleteById(1L));
        System.err.println("插入一条数据：" + userService.insert(new User(1L, "张三", "13659876532")));
        User user = new User("张三", "456", "15526538456");
        boolean result = userService.insert(user);
        // 自动回写的ID
        Long id = user.getId();
        System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
        System.err.println("查询：" + userService.selectById(id).toString());
        System.err.println("更新一条数据：" + userService.updateById(new User(1L, "三毛", "15669852653")));
        return userService.selectById(1L);
    }

    /**
     * 插入 OR 修改
     */
    @RequestMapping("/test2")
    public User test2() {
        userService.insertOrUpdate(new User(1L, "王五", "15507529865"));
        return userService.selectById(1L);
    }

    /**
     * 分页 PAGE
     */
    @RequestMapping("/test3")
    public Page<User> test3() {
        return userService.selectPage(new Page<User>(0, 12));
    }
	
}
