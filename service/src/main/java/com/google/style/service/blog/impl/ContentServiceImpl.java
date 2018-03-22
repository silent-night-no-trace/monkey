package com.google.style.service.blog.impl;

import com.google.style.dao.mapper.blog.ContentMapper;
import com.google.style.model.blog.Content;
import com.google.style.service.blog.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 文章内容 service 业务层实现
 *
 * @author liangz
 * @email xx@.com
 * @date 2018-03-21 10:46:28
 */


@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public Content get(Long id){
		return contentMapper.get(id);
	}
	
	@Override
	public List<Content> list(Map<String, Object> map){
		return contentMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contentMapper.count(map);
	}
	
	@Override
	public int save(Content content){
		return contentMapper.save(content);
	}
	
	@Override
	public int update(Content content){
		return contentMapper.update(content);
	}
	
	@Override
	public int remove(Long id){
		return contentMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return contentMapper.batchRemove(ids);
	}
	
}
