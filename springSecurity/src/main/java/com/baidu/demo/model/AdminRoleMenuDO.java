package com.baidu.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * 角色菜单 关联 表
 * @author liangz
 */
@Data
@ToString
public class AdminRoleMenuDO {
    private Long id;
    private Long roleId;
    private Long menuId;
}
