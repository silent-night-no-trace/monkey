package com.google.zookeeper.xia;

import com.google.zookeeper.annoation.FruitColor;
import com.google.zookeeper.annoation.FruitName;
import com.google.zookeeper.annoation.FruitProvider;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author liangz
 * @date 2018/7/2 16:11
 **/
@Data
public class Apple {
	@FruitName("水果")
	private String appleName;

	@FruitColor(fruitColor = FruitColor.Color.GREEN)
	private String appleColor;

	@FruitProvider(id = 1,name = "小黄庄",addresses = {"寡妇村1","寡妇村2","寡妇村3"})
	private String appleProvider;

	public static void main(String[] args) {
		String strFruitName=" 水果名称：";
		String strFruitColor=" 水果颜色：";
		String strFruitProvicer="供应商信息：";
		Apple apple = new Apple();
		apple.getClass().getAnnotations();
		Field[] declaredFields = apple.getClass().getDeclaredFields();
		for(Field field :declaredFields){
			if(field.isAnnotationPresent(FruitName.class)){
				FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
				strFruitName=strFruitName+fruitName.value();
				System.out.println(strFruitName);
			}
			else if(field.isAnnotationPresent(FruitColor.class)){
				FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
				strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
				System.out.println(strFruitColor);
			}
			else if(field.isAnnotationPresent(FruitProvider.class)){
				FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
				strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+Arrays.toString(fruitProvider.addresses());
				System.out.println(strFruitProvicer);
			}
		}
//		System.out.println(annotations.length);
//		for (Annotation annotation :annotations) {
//			Class<? extends Annotation> aClass = annotation.getClass();
//			System.out.println(aClass);
//			FruitName annotation1 = annotation.getClass().getAnnotation(FruitName.class);
//			String value = annotation1.value();
//			System.out.println("水果名:"+value);
//			FruitColor annotation2 = annotation.getClass().getAnnotation(FruitColor.class);
//			FruitColor.Color color = annotation2.fruitColor();
//			System.out.println(color);
//			FruitProvider annotation3 = annotation.getClass().getAnnotation(FruitProvider.class);
//
//			System.out.println("id："+annotation3.id()+" name:"+annotation3.name()+" address"+ Arrays.toString(annotation3.addresses()));
//
//		}
	}
}
