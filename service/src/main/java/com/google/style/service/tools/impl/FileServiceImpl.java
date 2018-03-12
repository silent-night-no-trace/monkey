package com.google.style.service.tools.impl;

import com.google.style.dao.mapper.tools.FileMapper;
import com.google.style.model.tools.FileDO;
import com.google.style.service.tools.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author liangz
 * @date 2018/03/12 14:56
 */


@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public FileDO get(Long id){
		return fileMapper.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return fileMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fileMapper.count(map);
	}
	
	@Override
	public int save(FileDO file){
		return fileMapper.save(file);
	}
	
	@Override
	public int update(FileDO file){
		return fileMapper.update(file);
	}
	
	@Override
	public int remove(Long id){
		return fileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return fileMapper.batchRemove(ids);
	}
	
}
