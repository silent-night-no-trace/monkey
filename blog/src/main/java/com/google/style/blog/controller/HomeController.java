package com.google.style.blog.controller;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;
import java.util.Map;

/**
 * @author liangz
 * @date 2018/2/5 17:04
 *  主页
 **/
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(){

        return  "default/index";
    }
}
