package com.google.style.model.system;

import lombok.Data;

/**
 *
 * @date  2018/03/02 17:25
 * @author liangz
 * 角色菜单关系表
 *
 */
@Data
public class RoleMenu {
	private Long id;
	private Long  roleId;
	private Long menuId;

	@Override
	public String toString() {
		return "RoleMenu{" +
				"id=" + id +
				", roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}
