package com.google.kafka.springkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author leon
 * @date 2019-12-12 14:09:23
 */

@Component
public class Producer implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;


	@Override
	public void run(String... strings) throws Exception {
		kafkaTemplate.send("test","1");
		kafkaTemplate.send("test","2");
		kafkaTemplate.send("test","3");
		kafkaTemplate.send("test","4");
		kafkaTemplate.send("test","5");
		kafkaTemplate.send("test","6");
		kafkaTemplate.send("test","7");
		kafkaTemplate.send("test","8");
		kafkaTemplate.send("test","9");
		kafkaTemplate.send("test","10");
	}
}
