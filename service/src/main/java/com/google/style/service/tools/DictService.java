package com.google.style.service.tools;

import com.google.style.model.tools.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author liangz
 * @email xx@.com
 * @date 2018-03-12 17:05:03
 */
public interface DictService {
	
	Dict get(Long id);
	
	List<Dict> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Dict dict);
	
	int update(Dict dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<Dict> listType();
}
