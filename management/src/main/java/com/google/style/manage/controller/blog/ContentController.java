package com.google.style.manage.controller.blog;


import com.google.style.constant.Global;
import com.google.style.manage.common.controller.BaseController;
import com.google.style.model.blog.Content;
import com.google.style.service.blog.ContentService;
import com.google.style.utils.PageUtils;
import com.google.style.utils.Query;
import com.google.style.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-21 10:46:28
 */
 
@Controller
@RequestMapping("/blog/content")
public class ContentController extends BaseController{
	@Autowired
	private ContentService contentService;
	
	@GetMapping()
	@RequiresPermissions("blog:content:content")
	String content(){
	    return "blog/content/content";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:content:content")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Content> contentList = contentService.list(query);
		int total = contentService.count(query);
		PageUtils pageUtils = new PageUtils(contentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("blog:content:add")
	String add(){
	    return "blog/content/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("blog:content:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Content content = contentService.get(id);
		model.addAttribute("content", content);
	    return "blog/content/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("blog:content:add")
	@PostMapping("/save")
	public R save(Content content) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (content.getAllowComment() == null) {
			content.setAllowComment(0);
		}
		if (content.getAllowFeed() == null) {
			content.setAllowFeed(0);
		}
		if(null==content.getType()) {
			content.setType("article");
		}
		content.setCreateTime(new Date());
		content.setUpdateTime(new Date());
		int count;
		if (content.getId() == null || "".equals(content.getId())) {
			count = contentService.save(content);
		} else {
			count = contentService.update(content);
		}
		if (count > 0) {
			return R.ok().put("cid", content.getId());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("blog:content:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( Content content) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		content.setUpdateTime(new Date());
		contentService.update(content);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:content:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (contentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:content:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		contentService.batchRemove(ids);
		return R.ok();
	}
	
}
