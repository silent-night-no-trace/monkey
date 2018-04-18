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

	/**
	 * get
	 * @param id id
 	 * @return file
	 */
	FileDO get(Long id);

	/**
	 * 根据 url 获取 文件
	 * @param url
	 * @return
	 */
	FileDO findFileByUrl(String url);

	/**
	 * list
	 * @param map map
	 * @return list
	 */
	List<FileDO> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param file file
	 * @return int
	 */
	int save(FileDO file);

	/**
	 * update
	 * @param file file
	 * @return int
	 */
	int update(FileDO file);

	/**
	 *  remove
	 * @param id id
	 * @return int
	 */
	int remove(Long id);

	/**
	 * batch remove
	 * @param ids ids
	 * @return int
	 */
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
	Boolean isExist(String url);
}
