package com.google.kafka.javaapi;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 自定义分区
 *
 * @author leon
 * @date 2019-12-13 17:39:42
 */
@Slf4j
public class CustomPartitionProducerSendThread extends Thread{
	/**
	 * 发送主题
	 */
	private String topic;
	/**
	 * 生产着
	 */
	private KafkaProducer<Integer, String> producer;

	public CustomPartitionProducerSendThread(String topic) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.SERVER_ADDRESSES);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG,"practice-producer");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		//配置自定义分区策略
		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartition.class.getName());

		this.producer = new KafkaProducer<>(properties);
		this.topic = topic;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			String msg="custom partition message:"+i;
			ProducerRecord<Integer,String> producerRecord = new ProducerRecord<>(topic,msg);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(JSON.toJSONString(producerRecord.value()));
			producer.send(producerRecord);
		}
	}

	public static void main(String[] args) {
		new CustomPartitionProducerSendThread("test").start();
		System.out.println(CustomPartition.class.getName());
		System.out.println(CustomPartition.class.getSimpleName());
	}

}
