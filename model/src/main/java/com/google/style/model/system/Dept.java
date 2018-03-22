package com.google.style.model.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 部门管理
 * 
 * @author liangz
 * @date 2017-09-27 14:28:36
 */
@ToString
@Data
public class Dept implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	//上级部门ID，一级部门为0
	private Long parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否删除  -1：已删除  0：正常
	private Integer delFlag;


}
