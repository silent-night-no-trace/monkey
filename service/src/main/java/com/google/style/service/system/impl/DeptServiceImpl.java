package com.google.style.service.system.impl;


import com.google.style.dao.mapper.system.DeptMapper;
import com.google.style.model.Tree;
import com.google.style.model.system.Dept;
import com.google.style.service.system.DeptService;
import com.google.style.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门service 实现
 */
@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public Dept get(Long deptId){
		return deptMapper.get(deptId);
	}

	@Override
	public List<Dept> list(Map<String, Object> map){
		return deptMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return deptMapper.count(map);
	}

	@Override
	public int save(Dept sysDept){
		return deptMapper.save(sysDept);
	}

	@Override
	public int update(Dept sysDept){
		return deptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return deptMapper.delete(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return deptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<Dept> getTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> deptLists = deptMapper.list(new HashMap<>(16));
		for (Dept sysDept : deptLists) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(sysDept.getId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Dept> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		//查询部门以及此部门的下级部门
		int result = deptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

}
