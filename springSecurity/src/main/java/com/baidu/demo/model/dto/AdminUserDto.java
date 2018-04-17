package com.baidu.demo.model.dto;

import com.baidu.demo.model.AdminMenuDO;
import com.baidu.demo.model.AdminRoleDO;
import com.baidu.demo.model.vo.AdminMenuVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author liangz
 * @date 2018/4/13 15:40
 **/
@Data
@ToString
public class AdminUserDto {

	private Long id;
	private String username;

	/**
	 * 用户角色列表
	 */
	private ArrayList<AdminRoleDO> roleList;
	/**
	 * 用户菜单列表
	 */
	@JsonIgnore
	private ArrayList<AdminMenuDO> menuList;

	/**
	 * 菜单 值对象
	 */
	private ArrayList<AdminMenuVO> menus;


	public ArrayList<AdminMenuVO> getMenus() {
		//值对象集合
		ArrayList<AdminMenuVO> menus = new ArrayList<>();
		//菜单 DO 为空
		if(menuList==null||menuList.size()==0){
			return menus;
		}
		//定义集合排序
		Collections.sort(menuList, (o1, o2) -> {
			if(o1.getSort() < o2.getSort()) {
				return -1;
			} else if(o1.getSort() == o2.getSort()) {
				return 0;
			} else {
				return 1;
			}
		});
		for (AdminMenuDO roleMenu : menuList) {
			if (roleMenu.getParentId() == 0) {
				menus.add(recursiveMenu(roleMenu, menuList));
			}
		}
		return menus;
	}

	/**
	 *  递归 菜单
	 * @return
	 */
	private AdminMenuVO recursiveMenu(AdminMenuDO parentRoleMenu, ArrayList<AdminMenuDO> sourceData) {
		AdminMenuVO result = new AdminMenuVO();

		result.setId(parentRoleMenu.getId());
		result.setName(parentRoleMenu.getName());
		result.setType(parentRoleMenu.getType());
		result.setUrl(parentRoleMenu.getUrl());
		result.setIcon(parentRoleMenu.getIcon());
		result.setSort(parentRoleMenu.getSort());
		result.setIsValid(parentRoleMenu.getIsValid());
		result.setParentId(parentRoleMenu.getParentId());

		ArrayList<AdminMenuVO> subMenus = new ArrayList<>();

		for (AdminMenuDO tmp : sourceData) {
			if (tmp.getParentId() == parentRoleMenu.getId()) {
				subMenus.add(recursiveMenu(tmp, sourceData));
			}
		}

		result.setSubMenus(subMenus);
		return result;
	}


}
