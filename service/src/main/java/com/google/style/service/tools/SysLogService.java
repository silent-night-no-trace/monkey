package com.google.style.service.tools;

import com.google.style.model.tools.SysLog;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-14 17:54:27
 */
public interface SysLogService {

	SysLog get(Long id);
	
	List<SysLog> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SysLog sysLog);
	
	int update(SysLog sysLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
