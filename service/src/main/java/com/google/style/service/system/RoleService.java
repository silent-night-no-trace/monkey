package com.google.style.service.system;

import com.google.style.model.system.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色
 * @author liangz
 */
public interface RoleService {

	/**
	 * get
	 * @param id id
	 * @return Role
	 */
	Role get(Long id);

	/**
	 * list
	 * @return List<Role>
	 */
	List<Role> list();

	/**
	 * save
	 * @param role role
	 * @return
	 */
	int save(Role role);

	/**
	 * update
	 * @param role role
	 * @return int
	 */
	int update(Role role);

	/**
	 * remove
	 * @param id id
	 * @return int
	 */
	int remove(Long id);

	/**
	 * 获取用户 角色列表
	 * @param userId userId
	 * @return List<Role>
	 */
	List<Role> list(Long userId);

	/**
	 * batchRemove
	 * @param ids ids
	 * @return int
	 */
	int batchRemove(Long[] ids);
}
