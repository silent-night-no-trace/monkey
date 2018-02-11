package com.google.model.first;

import lombok.Data;

/**
 * @author liangz
 * @date 2018/2/9 18:03
 * 商品 model
 **/
@Data
public class Goods {
    private Integer id;
    private String name;
    private Integer status;
    private Integer num;
}
