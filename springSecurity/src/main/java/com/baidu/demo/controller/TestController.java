package com.baidu.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.demo.dao.AdminUserMapper;
import com.baidu.demo.model.AdminUser;
import com.baidu.demo.model.dto.AdminUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangz
 * @date 2018/4/12 18:29
 **/

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RestController
public class TestController {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@RequestMapping("/login")
	public AdminUserDto login(@AuthenticationPrincipal AdminUser adminUser){
		System.out.println("用户信息 ："+ JSON.toJSONString(adminUserMapper.getByLoginName(adminUser.getUsername())));

		return adminUser.getAdminUserDto();
	}

	@RequestMapping("/getUser2Info")
	public String getUserInfo(String loginName){
		System.out.println("接口调用："+loginName);
		return loginName+" ,welcome to  here";
	}

}