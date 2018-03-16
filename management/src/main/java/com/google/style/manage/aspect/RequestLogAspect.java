package com.google.style.manage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liangz
 * @date 2018/3/14 18:14
 * 请求日志 
 **/
@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    @Pointcut("execution(public * com.google.style.manage.controller..*.*(..))")
    public void logAspect(){
    }

    /**
     * controller 层方法执行之前  执行
     */
    @Before(value = "logAspect()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求ip地址:{}",request.getRemoteAddr());
        log.info("请求url地址："+request.getRequestURL().toString());
        log.info("处理class:{}.{}",joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName());
        log.info("请求方法："+request.getMethod());
        log.info("请求参数:{}",joinPoint.getArgs());
    }

    @Around(value = "logAspect()")
    public Object doAround(ProceedingJoinPoint pjp){
        Long startTime = System.currentTimeMillis();
        Object object = null;
        try {
             object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        log.info("接口耗时:{}",endTime-startTime);
        return object;
    }

    @AfterReturning(returning ="object",pointcut = "logAspect()")
    public void doAfterReturning(Object object){
        log.info("接口返回："+object);
    }
}
