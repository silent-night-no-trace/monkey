package com.google.style.manage.exception;

import com.google.style.manage.common.enumeration.CodeEnum;

/**
 * @author liangz
 * @date 2018/4/3 11:40
 *  系统异常
 **/
public class SystemException  extends RuntimeException{

    private CodeEnum codeEnum;
    public SystemException(CodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }
}
