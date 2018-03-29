package com.google.style.charts;

import lombok.Data;

import java.util.List;

/**
 * x轴
 * @author liangz
 * @date  2018/03/22 17:27
 */
@Data
public class XAxis {
	private String type = "category";
	private Boolean boundaryGap = false;
	private List<String>data;

	public XAxis(List<String>data){
	    this.data = data;
    }

}