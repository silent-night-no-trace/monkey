package com.google.style.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 图例组件
 *
 * @author liangz
 * @date  2018/03/22 18:02
 */
@Data
@AllArgsConstructor
public class Legend {
	private List<String> data;
}
