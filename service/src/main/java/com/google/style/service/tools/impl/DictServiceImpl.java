package com.google.style.service.tools.impl;

import com.google.style.dao.mapper.tools.DictMapper;
import com.google.style.model.tools.Dict;
import com.google.style.service.tools.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
}
