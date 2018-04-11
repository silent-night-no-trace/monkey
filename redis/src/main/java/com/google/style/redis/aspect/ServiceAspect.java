//package com.google.style.redis.aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.google.style.redis.annotation.ServiceCache;
//import com.google.style.redis.util.RedisUtil;
//import com.google.style.redis.util.SerializeUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author liangz
// * @date 2018/4/9 18:00
// **/
//@Aspect
//@Component
//@Slf4j
//public class ServiceAspect {
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    /**
//     * 缓存key
//     */
//    private static final String SERVICE_CACHE_KEY = "style:service:cache:%s:%s:%s";
//
//
//    @Pointcut("execution(public * com.google.style.service.*.impl.*.*(..))")
//    public void allMethod(){
//    }
//
//    @Around("allMethod()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        Method method = methodSignature.getMethod();
//        ServiceCache serviceCache = method.getAnnotation(ServiceCache.class);
//        // 没有注解直接返回
//        if (serviceCache == null) {
//            // 不使用缓存执行
//            return pjp.proceed();
//        }
//        StringBuilder sb = new StringBuilder();
//        Object[] args = pjp.getArgs();
//        for(Object object:args){
//            sb.append(JSON.toJSONString(object)).append("-");
//        }
//        sb.append("eof");
//        String key = String.format(ServiceAspect.SERVICE_CACHE_KEY, pjp.getTarget().getClass().getName(), method.getName(), sb.toString());
//        byte [] cacheBytes;
//        if(redisUtil.exists(key)){
//            //key是否存在于redis 中
//            String cacheValue = redisUtil.get(key);
//            log.info("=====缓存中读取："+key+"====成功=====");
//            return SerializeUtil.deserialize(cacheValue.getBytes());
//        }
//        log.info("缓存键："+key+" 已失效------");
//        //执行
//        Object object = pjp.proceed();
//        //序列化 进行缓存
//        try {
//            cacheBytes = SerializeUtil.serialize(object);
//        }catch (Exception e){
//            log.info("序列化失败："+key,e);
//            return object;
//        }
//        redisUtil.setAdditionTime(key,new String(cacheBytes),serviceCache.value(),TimeUnit.MILLISECONDS);
//
//        return object;
//
////        byte [] key  = String.format(ServiceAspect.SERVICE_CACHE_KEY, pjp.getTarget().getClass().getName(), method.getName(), sb.toString()).getBytes();
////
////        byte [] cacheBytes;
////        if (redisUtil.exists(new String(key))) {
////
////            log.info(new String(key) + " - 启用了SERVICE缓存");
////            cacheBytes = redisUtil.getBytes(key);
////            try {
////                return SerializeUtil.deserialize(cacheBytes);
////            } catch (Exception e) {
////                log.error("反序列化失败: " + new String(key), e);
////            }
////        }
////
////        log.info(new String(key) + " - SERVICE缓存已失效");
////        // 执行
////        Object object = pjp.proceed();
////        try {
////            cacheBytes = SerializeUtil.serialize(object);
////        } catch (Exception e) {
////            log.error("序列化失败: " + new String(key), e);
////            return object;
////        }
////        redisUtil.setAdditionTime(new String(key),new String(cacheBytes),serviceCache.value(), TimeUnit.MILLISECONDS);
////        return object;
//    }
//}
