//package com.google.style.redis.annotation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * service 缓存注解
// * 注意：缓存的返回对象必须含有空构造器，不然缓存失效
// * @author liangz
// * @date 2018/4/10 10:07
// *
// **/
//@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
//public @interface ServiceCache {
//    //缓存失效时间 默认一分钟
//    long value() default 60000L;
//}
