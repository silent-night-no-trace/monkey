package com.google.zookeeper.annoation;

import java.lang.annotation.*;

/**
 * @author liangz
 * @date 2018/7/2 16:04
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
	String value() default "";
}
