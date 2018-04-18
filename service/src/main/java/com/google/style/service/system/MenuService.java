package com.google.style.service.system;

import com.google.style.model.Tree;
import com.google.style.model.system.Menu;
import com.google.style.model.system.MenuVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 
 * @author liangz
 */
@Service
public interface MenuService {
	/**
	 * 获取用户 菜单 tree
	 * @param id id
	 * @return  Tree<MenuVo>
	 */
	Tree<MenuVo> getSysMenuTree(Long id);

	/**
	 * 获取用户 菜单 tree
	 * @param id id
	 * @return  List<Tree<Menu>>
	 */
	List<Tree<Menu>> listMenuTree(Long id);

	/**
	 * 获取 y所有 tree
	 * @return Tree<Menu>
	 */
	Tree<Menu> getTree();

	/**
	 * 获取 角色 所有 tree
	 * @param  id id
	 * @return Tree<Menu>
	 */
	Tree<Menu> getTree(Long id);

	/**
	 * 获取 tree
	 * @param params params
	 * @return Tree<Menu>
	 */
	List<Menu> list(Map<String, Object> params);

	/**
	 * remove
	 * @param id id
	 * @return int
	 */
	int remove(Long id);

	/**
	 * save
	 * @param menu menu
	 * @return int
	 */
	int save(Menu menu);

	/**
	 * uodate
	 * @param menu menu
	 * @return int
	 */
	int update(Menu menu);

	/**
	 * get
	 * @param id id
	 * @return menu
	 */
	Menu get(Long id);

	/**
	 * list 权限
	 * @param userId userid
	 * @return set<String></>
	 */
	Set<String> listPerms(Long userId);
}
