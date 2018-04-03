package com.google.style.manage.controller.blog;


import com.google.style.model.blog.Content;
import com.google.style.service.blog.ContentService;
import com.google.style.utils.DateUtils;
import com.google.style.utils.PageUtils;
import com.google.style.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangz
 * @date  2018/04/02 13:32
 */
@RequestMapping("/blog")
@Controller
public class BlogController {
	@Autowired
	ContentService contentService;

	@GetMapping()
	String blog() {
		return "blog/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<Content> bContentList = contentService.list(query);
		int total = contentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{id}")
	String post(@PathVariable("id") Long id, Model model) {
		Content content = contentService.get(id);
		//存放值到页面 页面进行获取
		model.addAttribute("content", content);
		model.addAttribute("updateTime", DateUtils.format(content.getUpdateTime()));
		return "blog/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		Content content =null;
		if(contentService.list(map).size()>0){
            content = contentService.list(map).get(0);
		}
		model.addAttribute("content", content);
		return "blog/index/post";
	}
}
