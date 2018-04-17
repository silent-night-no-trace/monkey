package com.baidu.demo.model;

import lombok.Data;

/**
 * @author liangz
 * @date 2018/4/13 15:30
 * 用户角色关联表
 **/
@Data
public class AdminUserRoleDO {
	private Long id;
	private Long userId;
	private Long roleId;

}
