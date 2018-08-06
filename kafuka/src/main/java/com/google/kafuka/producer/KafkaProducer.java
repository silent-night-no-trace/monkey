/*
package com.google.kafuka.producer;

import com.alibaba.fastjson.JSON;
import com.google.kafuka.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString().replaceAll("-",""));
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal", JSON.toJSONString(message));
        kafkaTemplate.send("topic-ideal",UUID.randomUUID().toString().replaceAll("-",""),JSON.toJSONString(message));

    }
}
*/
