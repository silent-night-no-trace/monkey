package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liangz
 * @date 2018/3/5 14:57
 *  menu vo
 **/
@ToString
@Data
public class MenuVo implements Serializable{
    private static final long serialVersionUID = 1L;
    //
    private Long id;
    // 父菜单ID，一级菜单为0 parent_id
    private Long parentId;
    // 菜单名称
    private String name;
    // 菜单URL
    private String url;
    // 授权(多个用逗号分隔，如：user:list,user:create)
    private String permission;
    // 类型 0：目录 1：菜单 2：按钮
    private Integer type;
    // 菜单图标
    private String icon;
    // 排序
    private Integer orderNum;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
}
