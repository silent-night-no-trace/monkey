package com.google.style.service.tools.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.PageHelper;
import com.google.style.config.GlobalConfig;
import com.google.style.dao.mapper.tools.FileMapper;
import com.google.style.model.tools.FileDO;
import com.google.style.service.tools.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
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

	@Autowired
	private GlobalConfig globalConfig;

	@Override
	public FileDO get(Long id){
		return fileMapper.get(id);
	}

	@Override
	public FileDO findFileByUrl(String url) {
		return fileMapper.findFileByUrl(url);
	}

	@Override
	public List<FileDO> list(Map<String, Object> map){
		Integer offset = (Integer) map.get("offset");
		Integer limit = (Integer)map.get("limit");
		if(offset!=null&&limit!=null){
			PageHelper.startPage(offset,limit);
		}
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

	/**
	 * 判断文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
	@Override
	public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = globalConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
}
