package com.google.style.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangz
 * @date 2018/2/7 15:47
 **/
@RestController
public class TestC {
    @Value("${spring.datasource.driver-class-name}")
    private String driveClass;

    @RequestMapping("/bi")
    public String one(){
        return driveClass;
    }
}
