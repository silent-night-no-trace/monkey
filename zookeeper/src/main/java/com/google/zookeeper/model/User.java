package com.google.zookeeper.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liangz
 * @date 2018/6/25 17:26
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = -4793670597807817295L;

	private Integer id;
	private String name;


}
