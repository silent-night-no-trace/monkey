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
	
	Content get(Long id);
	
	List<Content> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Content content);
	
	int update(Content content);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
