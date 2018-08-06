package com.google.zookeeper.annoation;

import java.lang.annotation.*;

/**
 * @author liangz
 * @date 2018/7/2 16:06
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
	enum Color{BULE,RED,GREEN}
	Color fruitColor() default Color.GREEN;
}
