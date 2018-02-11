package com.google.style.model.first;

import lombok.Data;
import lombok.ToString;

/**
 * @author liangz
 * @date 2018/2/11 14:48
 *  goods model
 **/
@Data
@ToString
public class Goods {
    private Integer id;
    private String name;
    private Integer status;
    private Integer num;
}
