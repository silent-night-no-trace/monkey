package com.google.style.manage.controller.tools;


import com.google.style.manage.annotation.Log;
import com.google.style.model.tools.SysLog;
import com.google.style.service.tools.SysLogService;
import com.google.style.utils.PageUtils;
import com.google.style.utils.Query;
import com.google.style.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-14 17:54:27
 */
 
@Controller
@RequestMapping("/tools/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	@GetMapping()
	@RequiresPermissions("tools:log")
	String log(){
	    return "common/log/log";
	}

	@Log("获取系统日志列表")
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SysLog> logList = sysLogService.list(query);
		int total = sysLogService.count(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}


	/**
	 * 删除
	 */
	@Log("系统日志删除")
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(sysLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("系统日志批量删除")
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		sysLogService.batchRemove(ids);
		return R.ok();
	}
	
}
