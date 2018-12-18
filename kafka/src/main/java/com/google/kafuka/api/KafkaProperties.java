package com.google.kafuka.api;

/**
 * @author liangz
 * @date 2018/8/3 11:31
 */
public class KafkaProperties {

      final static String zkConnect = "47.98.172.225:2181";

      final static String groupId = "group1";

      final static String topic = "topic1";

      final static String kafkaServerURL = "47.98.172.225";

      final static int kafkaServerPort = 9092;

      final static int kafkaProducerBufferSize = 64 * 1024;

      final static int connectionTimeOut = 20000;

      final static int reconnectInterval = 10000;

      final static String topic2 = "topic2";

      final static String topic3 = "topic3";

      final static String clientId = "SimpleConsumerDemoClient";
}
