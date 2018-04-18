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

	/**
	 * 获取 部门
	 * @param deptId deptId
	 * @return dept
	 */
	Dept get(Long deptId);

	/**
	 * 获取部门 list
	 * @param map map
	 * @return list<Dept></>
	 */
	List<Dept> list(Map<String, Object> map);

	/**
	 * count
	 * @param map map
	 * @return int
	 */
	int count(Map<String, Object> map);

	/**
	 * save
	 * @param sysDept sysDept
	 * @return int
	 */
	int save(Dept sysDept);

	/**
	 * update
	 * @param sysDept sysDept
	 * @return int
	 */
	int update(Dept sysDept);

	/**
	 * remove
	 * @param deptId deptId
	 * @return int
	 */
	int remove(Long deptId);

	/**
	 * 批量删除
	 * @param deptIds deptIds
	 * @return
	 */
	int batchRemove(Long[] deptIds);

	/**
	 * 获取 部门树菜单
	 * @return Tree<Dept>
	 */
	Tree<Dept> getTree();

	/**
	 * 判断 部门是否存在 用户
	 * @param deptId deptId
	 * @return boolean
	 */
	boolean checkDeptHasUser(Long deptId);
}
