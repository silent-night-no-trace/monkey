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
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
}
