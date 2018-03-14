package com.google.style.service.system.impl;


import com.google.style.dao.mapper.system.MenuMapper;
import com.google.style.dao.mapper.system.RoleMenuMapper;
import com.google.style.model.Tree;
import com.google.style.model.system.Menu;
import com.google.style.model.system.MenuVo;
import com.google.style.service.system.MenuService;
import com.google.style.utils.BuildTree;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单管理
 * @author liangz
 * @date  2018/03/13 11:49
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper menuMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	@Override
	public Tree<MenuVo> getSysMenuTree(Long id) {
		List<Tree<MenuVo>> trees = new ArrayList<>();
		List<MenuVo> menus = menuMapper.listMenuByUserId(id);
		for (MenuVo sysMenu : menus) {
			Tree<MenuVo> tree = new Tree<>();
			tree.setId(sysMenu.getId().toString());
			tree.setParentId(sysMenu.getParentId().toString());
			tree.setText(sysMenu.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenu.getUrl());
			attributes.put("icon", sysMenu.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuVo> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<Menu> list(Map<String, Object> params) {
		List<Menu> menus = menuMapper.list(params);
		return menus;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int remove(Long id) {
		int result = menuMapper.delete(id);
		return result;
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int save(Menu menu) {
		int r = menuMapper.save(menu);
		return r;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(Menu menu) {
		int r = menuMapper.update(menu);
		return r;
	}

	@Override
	public Menu get(Long id) {
		Menu Menu = menuMapper.get(id);
		return Menu;
	}

	@Override
	public Tree<Menu> getTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> Menus = menuMapper.list(new HashMap<>(16));
		for (Menu sysMenu : Menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenu.getId().toString());
			tree.setParentId(sysMenu.getParentId().toString());
			tree.setText(sysMenu.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<Menu> getTree(Long id) {
		// 根据roleId查询权限
		List<Menu> menus = menuMapper.list(new HashMap<String, Object>(16));
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (Menu menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> Menus = menuMapper.list(new HashMap<String, Object>(16));
		for (Menu sysMenu : Menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenu.getId().toString());
			tree.setParentId(sysMenu.getParentId().toString());
			tree.setText(sysMenu.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenu.getId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<Menu>> listMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<>();
		List<MenuVo> Menus = menuMapper.listMenuByUserId(id);
		for (MenuVo mv : Menus) {
		    System.out.println("mv ===="+mv.toString());
			Tree<Menu> tree = new Tree<>();
			tree.setId(mv.getId().toString());
			tree.setParentId(mv.getParentId().toString());
			tree.setText(mv.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", mv.getUrl());
			attributes.put("icon", mv.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
		return list;
	}

}
