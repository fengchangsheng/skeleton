package com.fcs.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lucare.Feng on 2017/2/28.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("this is frame");
        return "common/frame";
    }


    @RequestMapping("/index")
    public String list(Model model) {
        System.out.println("this is index");
        return "index";
    }

}
