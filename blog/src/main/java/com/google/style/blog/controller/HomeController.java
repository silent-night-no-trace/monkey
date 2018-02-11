package com.google.style.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangz
 * @date 2018/2/5 17:04
 *  主页
 **/
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){

        return  "default/index";
    }

}
