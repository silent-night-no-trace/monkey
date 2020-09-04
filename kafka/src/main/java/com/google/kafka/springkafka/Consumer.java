package com.google.kafka.springkafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author leon
 * @date 2019-12-13 15:45:09
 */
@Component
public class Consumer  {

	@KafkaListener(topics = "test")
	public void receiveMessage(ConsumerRecord record){
		Optional<?> msg= Optional.ofNullable(record.value());
		msg.ifPresent(System.out::println);
	}
}
