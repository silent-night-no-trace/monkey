package com.google.style.manage.exception;

import com.google.style.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liangz
 * @date 2018/4/3 11:54
 * 异常处理器
 **/

@Slf4j
@ControllerAdvice
public class SystemExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R exception(Exception e){
        String message = e.getMessage();
        log.error("exception错误信息："+message+" 请及时解决！");
        return R.error("",message);

    }

    @ExceptionHandler(value = SystemException.class)
    public R systemException(SystemException se){
        String message = se.getMessage();
        log.error("systemException错误信息："+message+" 请及时解决!");
        return R.error("",message);
    }

}
