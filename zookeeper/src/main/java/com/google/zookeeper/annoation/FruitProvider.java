package com.google.zookeeper.annoation;

import java.lang.annotation.*;

/**
 * @author liangz
 * @date 2018/7/2 16:08
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
 	int id() default -1;
 	String name() default "";
 	String [] addresses() default "";

}
