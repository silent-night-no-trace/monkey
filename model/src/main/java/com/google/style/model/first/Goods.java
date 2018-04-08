package com.google.style.model.first;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liangz
 * @date 2018/2/11 14:48
 *  goods model
 **/
@ToString
@Data
public class Goods  implements Serializable{
    private Integer id;
    private String name;
    private Integer status;
    private Integer num;

}
