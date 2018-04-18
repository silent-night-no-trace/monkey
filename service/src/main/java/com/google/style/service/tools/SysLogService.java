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

	/**
	 * get
	 * @param id id
 	 * @return SysLog
	 */
	SysLog get(Long id);

	/**
	 * list
	 * @param map map
	 * @return list
	 */
	List<SysLog> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param sysLog sysLog
	 * @return int
	 */
	int save(SysLog sysLog);

	/**
	 * update sysLog
	 * @param sysLog sysLog
	 * @return int
	 */
	int update(SysLog sysLog);


	/**
	 * remove
	 * @param id id
	 * @return int
	 */
	int remove(Long id);

	/**
	 * batch remove
	 * @param ids  ids
	 * @return int
	 */
	int batchRemove(Long[] ids);
}
