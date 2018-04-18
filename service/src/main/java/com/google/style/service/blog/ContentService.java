package com.google.style.service.blog;

import com.google.style.model.blog.Content;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-21 10:46:28
 */
public interface ContentService {

	/**
	 * 文章内容
	 * @param id id
	 * @return content
	 */
	Content get(Long id);

	/**
	 * list
	 * @param map map
	 * @return  List<Content>
	 */
	List<Content> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param content content
	 * @return int
	 */
	int save(Content content);

	/**
	 * update
	 * @param content content
	 * @return int
	 */
	int update(Content content);

	/**
	 * remove
	 * @param  id id
	 * @return int
	 */
	int remove(Long id);

	/**
	 * batch Remove
	 * @param ids ids
	 * @return int
	 */
	int batchRemove(Long[] ids);
}
