package com.google.style.service.tools;


import java.util.List;
import java.util.Map;

/**
 * @author liangz
 * @Time 2018年3月12日
 * @description 代码生成service
 * 
 */
public interface GeneratorService {
	/**
	 * list
	 * @return  List<Map<String, Object>>
	 */
	List<Map<String, Object>> list();

	/**
	 * 代码生成
	 * @param tableNames tableNames
	 * @return byte[]
	 */
	byte[] generatorCode(String[] tableNames);
}
