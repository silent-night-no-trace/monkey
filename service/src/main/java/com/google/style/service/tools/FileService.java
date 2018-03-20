package com.google.style.service.tools;


import com.google.style.model.tools.FileDO;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-12 14:39:46
 */
public interface FileService {
	
	FileDO get(Long id);

	FileDO findFileByUrl(String url);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO file);
	
	int update(FileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
	Boolean isExist(String url);
}
