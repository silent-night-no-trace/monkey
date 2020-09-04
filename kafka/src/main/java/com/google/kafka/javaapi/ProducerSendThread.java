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
 * @author leon
 * @date 2019-12-04 15:23:37
 */
@Slf4j
public class ProducerSendThread extends Thread {

	/**
	 * 发送主题
	 */
	private String topic;
	/**
	 * 生产着
	 */
	private KafkaProducer<Integer, String> producer;

	public ProducerSendThread(String topic) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.LOCAL_SERVER_ADDRESSES);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG,"practice-producer");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		this.producer = new KafkaProducer<>(properties);
		this.topic = topic;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			String msg="test message6 :"+i;
			ProducerRecord<Integer,String>producerRecord = new ProducerRecord<>(topic,msg);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info(JSON.toJSONString(producerRecord.value()));
			producer.send(producerRecord);
		}

	}

	public void asynchronousSendMessage(){
		for (int i = 0; i < 20; i++) {
			String msg="test message5:"+i;
			ProducerRecord<Integer,String>producerRecord = new ProducerRecord<>(topic,msg);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//可以同步阻塞等待 返回 future.get()
			producer.send(producerRecord, (recordMetadata, e) -> {
				int partition = recordMetadata.partition();
				System.out.println("recordMetadata:"+JSON.toJSONString(recordMetadata));
				log.info("分区: " + recordMetadata.topic() + " ,partition: "+partition+",offset: " + recordMetadata.offset());
			});
		}
	}

	public static void main(String[] args) {
		//new ProducerSendThread("replicationTopic").start();
		//new ProducerSendThread("multiPartition").start();
		new ProducerSendThread("multiPartitionAndMultiReplication").start();
//		System.out.println("replicationTopic: "+Math.abs(("replicationTopic".hashCode())%50));
	}

}
