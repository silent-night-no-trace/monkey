package com.google.zookeeper.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author liangz
 * @date 2018/6/25 11:53
 **/
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Config implements Serializable {

	private static final long serialVersionUID = 8785840367019830966L;

	private String name;
	private Object value;

}
