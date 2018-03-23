package com.google.style.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 标题组件
 * @author liangz
 */
@Data
@AllArgsConstructor
public class Title {
	private String text;
	private String subtext;
}
