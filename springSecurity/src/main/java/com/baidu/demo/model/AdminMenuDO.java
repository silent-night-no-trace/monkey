package com.baidu.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author liangz
 * @date 2018/4/13 15:32
 * 菜单
 **/
@Data
@ToString
public class AdminMenuDO {
	private long id;
	private String name;
	private Integer type;
	private Integer parentId;
	private String url;
	private String icon;
	private Integer sort;
	private Integer isValid;

}
