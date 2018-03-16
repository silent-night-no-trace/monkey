package com.google.style.manage.aspect;

import com.google.style.manage.annotation.Log;
import com.google.style.manage.utils.ShiroUtils;
import com.google.style.model.system.User;
import com.google.style.model.tools.SysLog;
import com.google.style.service.tools.SysLogService;
import com.google.style.utils.HttpContextUtils;
import com.google.style.utils.IPUtils;
import com.google.style.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author liangz
 * @date 2018/3/15 10:47
 **/
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    /**
     * 系统日志切点
     */
    @Pointcut("@annotation(com.google.style.manage.annotation.Log)")
    public void SysLogPointCut(){

    }

    @Around("SysLogPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object object = pjp.proceed();
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        //日志存储一发
        saveLog(pjp,time);
        return object;
    }

    /**
     * 系统日志保存
     * @param pjp
     * @param time
     */


    private void saveLog(ProceedingJoinPoint pjp, long time) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        SysLog sysLog = new SysLog();
        if (log != null) {
            sysLog.setOperation(log.value());
        }
        //设置操作时长
        sysLog.setTime((int) time);
        String className = pjp.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        sysLog.setMethod("类名：" + className + " 方法名：" + methodName);
        Object[] args = pjp.getArgs();
        System.out.println("args========" + args.toString());
        try {
            String params = JSONUtils.beanToJson(args[0]).substring(0, 4999);
            sysLog.setParams(params);
        } catch (Exception e) {

        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        //工具类获取 shiro
        User user = ShiroUtils.getUser();
        if(user!=null){
            sysLog.setUsername(user.getUsername());
            Long userId = user.getId();
            sysLog.setUserId(userId);
        }else{
            sysLog.setUserId(-1L);
            sysLog.setUsername("未知用户");
        }

        sysLog.setCreateTime(new Date());
        sysLogService.save(sysLog);
        System.out.println("========================日志保存成功===========================");
    }


}
