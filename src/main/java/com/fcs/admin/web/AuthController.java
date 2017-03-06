package com.fcs.admin.web;

import com.fcs.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lucare.Feng on 2017/3/6.
 */
@Controller
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login")
    public String index(Model model,User user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            //开始调用shiro验证
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
            //获取当前的subject
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证通过");
            //验证是否登录成功
            if (currentUser.isAuthenticated()) {
                logger.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                return "redirect:/";
            } else {
                token.clear();
                return "redirect:/login";
            }
        }
        return "login";
    }
}
