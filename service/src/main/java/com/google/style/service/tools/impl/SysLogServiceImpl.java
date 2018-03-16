package com.google.style.service.tools.impl;

import com.google.style.dao.mapper.tools.SysLogMapper;
import com.google.style.model.tools.SysLog;
import com.google.style.service.tools.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 * service
 * 业务逻辑层
 * @date  2018/03/14 18:02
 * @author liangz
 *
 */

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Override
	public SysLog get(Long id){
		return sysLogMapper.get(id);
	}
	
	@Override
	public List<SysLog> list(Map<String, Object> map){
		return sysLogMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sysLogMapper.count(map);
	}
	
	@Override
	public int save(SysLog sysLog){
		return sysLogMapper.save(sysLog);
	}
	
	@Override
	public int update(SysLog sysLog){
		return sysLogMapper.update(sysLog);
	}
	
	@Override
	public int remove(Long id){
		return sysLogMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysLogMapper.batchRemove(ids);
	}
	
}
