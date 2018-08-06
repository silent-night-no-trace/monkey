package com.google.kafuka.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    /**
     * id
     */
    private Long id;
    /**
     * 消息
     */
    private String msg;
    /**
     * 时间戳
     */
    private Date sendTime;
}
