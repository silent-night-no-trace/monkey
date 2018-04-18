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

	/**
	 * get
	 * @param id id
	 * @return dict
	 */
	Dict get(Long id);

	/**
	 * list
	 * @param map map
	 * @return list<Dict></>
	 */
	List<Dict> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param dict dict
	 * @return
	 */
	int save(Dict dict);

	/**
	 * update
	 * @param dict dict
	 * @return int
	 */
	int update(Dict dict);

	/**
	 * remove
	 * @param  id id
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
	 * 获取所有类型 字典
	 * @param
	 * @return
	 */
    List<Dict> listType();


    /**
     * 根据类型获取字典
     * @param type type
     * @return List<Dict>
     */
    List<Dict> listByType(String type);

    /**
     * 获取爱好列表
     * @return List<Dict
     * @param user user
     */
    List<Dict> getHobbyList(User user);

    /**
     * 获取性别列表
     * @return List<Dict>
     */
    List<Dict> getSexList();
}
