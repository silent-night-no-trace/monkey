package com.google.style.utils;

/**
 * 异常 工具类
 * @author liangz
 */
public class ExceptionUtils {
    public static String getExceptionAllinformation(Exception ex) {
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
}
