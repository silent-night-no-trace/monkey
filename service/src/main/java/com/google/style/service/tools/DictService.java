package com.google.style.service.tools;

import com.google.style.model.system.User;
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

	/**
	 * 获取所有类型 字典
	 * @param
	 * @return
	 */
    List<Dict> listType();


    /**
     * 根据类型获取字典
     * @param type
     * @return
     */
    List<Dict> listByType(String type);

    /**
     * 获取爱好列表
     * @return
     * @param user
     */
    List<Dict> getHobbyList(User user);

    /**
     * 获取性别列表
     * @return
     */
    List<Dict> getSexList();
}
