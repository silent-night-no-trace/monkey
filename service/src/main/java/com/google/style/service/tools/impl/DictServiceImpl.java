package com.google.style.service.tools.impl;

import com.google.style.dao.mapper.tools.DictMapper;
import com.google.style.model.system.User;
import com.google.style.model.tools.Dict;
import com.google.style.service.tools.DictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 业务层实现
 * @author liangz
 * @date  2018/03/12 17:24
 */
@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public Dict get(Long id){
		return dictMapper.get(id);
	}
	
	@Override
	public List<Dict> list(Map<String, Object> map){
		return dictMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dictMapper.count(map);
	}
	
	@Override
	public int save(Dict dict){
		return dictMapper.save(dict);
	}
	
	@Override
	public int update(Dict dict){
		return dictMapper.update(dict);
	}
	
	@Override
	public int remove(Long id){
		return dictMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return dictMapper.batchRemove(ids);
	}

    @Override
    public List<Dict> listType() {
        return dictMapper.listType();
    }

	/**
	 * 根据类型获取字典
	 * @param type
	 * @return
	 */
	@Override
	public List<Dict> listByType(String type) {
		Map<String, Object> param = new HashMap<>(16);
		param.put("type", type);
		return dictMapper.list(param);
	}

	@Override
	public List<Dict> getSexList() {
		Map<String, Object> param = new HashMap<>(16);
		param.put("type", "sex");
		return dictMapper.list(param);
	}

	/**
	 * 根据用户获取爱好列表
	 * @param user
	 * @return
	 */
	@Override
	public List<Dict> getHobbyList(User user) {
		Map<String, Object> param = new HashMap<>(16);
		param.put("type", "hobby");
		//字典表存放的hobby
		List<Dict> hobbies = dictMapper.list(param);
		if (StringUtils.isNotEmpty(user.getHobby())) {
			String [] userHobbies = user.getHobby().split(";");
			for (String userHobby : userHobbies) {

				for (Dict hobby : hobbies) {
					if (!Objects.equals(userHobby, hobby.getId().toString())) {
						continue;
					}
					hobby.setRemarks("true");
					break;
				}
			}
		}
		return hobbies;
	}
}
