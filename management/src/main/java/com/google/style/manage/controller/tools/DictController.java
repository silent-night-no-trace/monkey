package com.google.style.manage.controller.tools;


import com.google.style.constant.Global;
import com.google.style.manage.annotation.Log;
import com.google.style.manage.common.controller.BaseController;
import com.google.style.model.tools.Dict;
import com.google.style.service.tools.DictService;
import com.google.style.utils.PageUtils;
import com.google.style.utils.Query;
import com.google.style.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */

@Controller
@RequestMapping("/tools/dict")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService;

	@Log("字典页面")
	@GetMapping()
	@RequiresPermissions("tools:dict:dict")
	String dict() {
		return "common/dict/dict";
	}

	@Log("获取字典列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("tools:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<Dict> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("tools:dict:add")
	String add() {
		return "common/dict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("tools:dict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		Dict dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@Log("字典保存")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("tools:dict:add")
	public R save(Dict dict) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("字典update")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tools:dict:edit")
	public R update(Dict dict) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@Log("字典删除")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("tools:dict:remove")
	public R remove(Long id) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("批量移除")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("tools:dict:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if (Global.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.batchRemove(ids);
		return R.ok();
	}
    @Log("获取字典类型列表")
	@GetMapping("/type")
	@ResponseBody
	public List<Dict> listType() {
		return dictService.listType();
	};

	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("tools:dict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return "common/dict/add";
	}

	@Log("根据类型获取字典列表")
	@ResponseBody
	@GetMapping("/list/{type}")
	public List<Dict> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<Dict> dictList = dictService.list(map);
		return dictList;
	}
}
