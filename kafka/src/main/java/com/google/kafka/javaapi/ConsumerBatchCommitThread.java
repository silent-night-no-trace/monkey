package com.google.kafka.javaapi;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 批量提交
 *
 * @author leon
 * @date 2019-12-04 17:38:45
 */
@Slf4j
public class ConsumerBatchCommitThread extends Thread {

	private String topic;
	private KafkaConsumer<Integer, String> consumer;

	public ConsumerBatchCommitThread(String topic) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.SERVER_ADDRESSES);
		//group id
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "practice-consumer");
		//设置 offset自动提交
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		// 自动提交间隔时间
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		//超时时间
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		//对于 当前groupid来说，消息的offset从最早的消息开始消费
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumer = new KafkaConsumer<>(properties);
		this.topic = topic;


	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singleton(topic));
		List<ConsumerRecord> batchCommitList = Lists.newArrayList();
		while (true) {
			ConsumerRecords<Integer, String> consumerRecords =
					consumer.poll(1);
			Iterable<ConsumerRecord<Integer, String>> records =
					consumerRecords.records(topic);
			records.forEach(record -> {
				long offset = record.offset();
				int partition = record.partition();
				String topic = record.topic();
				String value = record.value();
				log.info("分区: " + partition + ", offset: " + offset + ",主题: " + topic + " ,消息内容:" + value);
				batchCommitList.add(record);
			});
			if(batchCommitList.size()>=200){
				//消费了200条消息 手动提交
				//同步提交
				//consumer.commitSync();
				//异步提交
				consumer.commitAsync();
				batchCommitList.clear();
			}

		}

	}

	public void asynchronousReceiveMessage() {

	}


	public static void main(String[] args) {
//		new ConsumerBatchCommitThread("test").start();
		new ConsumerBatchCommitThread("test-partition").start();
	}

}
