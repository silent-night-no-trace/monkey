package com.google.style.manage.controller.system;


import com.google.style.manage.annotation.Log;
import com.google.style.manage.common.controller.BaseController;
import com.google.style.manage.utils.ShiroUtils;
import com.google.style.model.Tree;
import com.google.style.model.system.Menu;
import com.google.style.model.system.User;
import com.google.style.model.tools.FileDO;
import com.google.style.service.system.MenuService;
import com.google.style.service.system.UserService;
import com.google.style.service.tools.FileService;
import com.google.style.utils.MD5Utils;
import com.google.style.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liangz
 * @date  2018/03/07 11:24
 *  系统登录
 */
@Controller
@SuppressWarnings("unused")
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;

	@Autowired
    UserService userService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/blog";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		//shir 中获取到用户信息中不包含 picId
		User userDO = userService.get(getUserId());
		Long picId = null;
		if(userDO!=null&&userDO.getPicId()!=null){
            picId = userDO.getPicId();
        }
		FileDO fileDO = fileService.get(picId);
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());

			}else {
				model.addAttribute("picUrl","/img/photo_s.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
    R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	@Log("退出登录")
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}