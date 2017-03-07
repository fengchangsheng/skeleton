package com.fcs.admin.web;

import com.fcs.admin.entity.User;
import com.fcs.admin.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lucare.Feng on 2017/3/6.
 */
@Controller
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login")
    public String index(User user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
            try {
                currentUser.login( token );
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/";
                } else {
                    token.clear();
                    return "redirect:/login";
                }
            } catch ( UnknownAccountException uae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch ( IncorrectCredentialsException ice ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-password didn't match");
            } catch ( LockedAccountException lae ) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-account is locked in the system");
            } catch ( AuthenticationException ae ) {
                logger.error(ae.getMessage());
            }
        }
        return "login";
    }
}
