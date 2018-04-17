package com.baidu.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author liangz
 * @date 2018/4/13 15:18
 * 角色
 **/
@Data
@ToString
public class AdminRoleDO {
	private Long id;
	private String name;
	private Long createdAt;
	private Long updatedAt;
}
