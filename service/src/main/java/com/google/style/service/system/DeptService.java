package com.google.style.service.system;

import com.google.style.model.Tree;
import com.google.style.model.system.Dept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author liangz
 * @email 1
 * @date 2018/03/06 11:00:00
 */
public interface DeptService {
	
	Dept get(Long deptId);
	
	List<Dept> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Dept sysDept);
	
	int update(Dept sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<Dept> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
