package com.google.style.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * http请求工具类
 * @author liangz
 */
public class HttpServletUtils {
    private static final String  HEADER = "x-requested-with";
    public static boolean jsAjax(HttpServletRequest req){
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(req.getHeader(HEADER)) && "XMLHttpRequest".equals(req.getHeader(HEADER))){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
