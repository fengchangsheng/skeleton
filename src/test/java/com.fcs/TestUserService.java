package com.fcs;

import com.fcs.admin.entity.User;
import com.fcs.admin.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Created by Lucare.Feng on 2017/3/7.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private IUserService userService;

    @Test
    public void findByName(){
        User user = userService.findByName("fcs");
        System.out.println(user.getId());
    }
}
