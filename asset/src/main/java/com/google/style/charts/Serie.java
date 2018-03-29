package com.google.style.charts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author liangz
 * @date 2018/3/22 15:45
 **/
@Data
@AllArgsConstructor
public class Serie {
    private String name;
    private String type;
    private String stack;
    private List<Integer>data;
}
