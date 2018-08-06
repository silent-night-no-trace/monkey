package com.google.zookeeper.http;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liangz
 * @date 2018/6/26 17:29
 **/
public final class Calculator {
	private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	public static Object cal(String expression) throws ScriptException {
		return jse.eval(expression);
	}
}
