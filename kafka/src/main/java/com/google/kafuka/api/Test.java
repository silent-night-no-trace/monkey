package com.google.kafuka.api;

/**
 * @author liangz
 * @date 2018/8/3 11:48
 */
public class Test {
    public static void main(String[] args) {
        Thread  producerThread = new Thread(new KafkaProducer(KafkaProperties.topic));
        producerThread.start();
        Thread consumerThread = new Thread(new KafkaConsumer(KafkaProperties.topic));
        consumerThread.start();
    }
}
