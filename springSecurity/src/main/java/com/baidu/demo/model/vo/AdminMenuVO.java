package com.baidu.demo.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @author liangz
 * @date 2018/4/13 16:11
 *  系统菜单 vo
 **/
@Data
@ToString
public class AdminMenuVO {
	private Long id;
	private String name;
	private Integer type;
	private Integer parentId;
	private String url;
	private String icon;
	private Integer sort;
	private Integer isValid;
	//存放父菜单
	private ArrayList<AdminMenuVO>subMenus;

}
