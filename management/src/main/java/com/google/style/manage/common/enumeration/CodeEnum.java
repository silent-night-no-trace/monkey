package com.google.style.manage.common.enumeration;


import lombok.Getter;

/**
 * @author liangz
 * @date 2018/4/3 11:45
 * code 枚举值
 **/
@Getter
public enum CodeEnum {

    SUCCESS("0","请求成功"),
    SYS_EXCEPTION("9001", "系统异常");


    private String code;
    private String message;

    CodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
