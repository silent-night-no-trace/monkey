package com.google.kafka.api;


import com.google.common.collect.Maps;
import com.google.kafka.javaapi.KafkaProperties;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author liangz
 * @date 2018/8/3 11:23
 */
public class KafkaConsumer implements Runnable {

    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.LOCAL_SERVER_ADDRESSES);
        props.put("group.id", KafkaProperties.GROUP_ID);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("receive：" + new String(it.next().message()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ConcurrentMap<String, Object> concurrentMap = Maps.newConcurrentMap();
        concurrentMap.put("1",1);
		Object o = concurrentMap.get("1");
	}

    public static void main(String[] args) {

//        System.out.println(16>>>2);
//        System.out.println(8&1);
//        System.out.println(12>>>16);
//        System.out.println(-1>>>16);
		ConcurrentMap<String, Object> map = new ConcurrentHashMap<>(16);
		new Thread(()->{
			map.put("1",1);
			map.put("2",1);
			map.put("3",1);
			map.put("4",1);
			map.put("5",1);
			map.put("6",1);
			map.put("7",1);
			map.put("8",1);
			map.put("9",1);
		}).start();

		new Thread(()->{
			map.put("10",1);
			map.put("11",1);
			map.put("14",1);
			map.put("15",1);
		}).start();

		new Thread(()->{
			map.put("12",1);
			map.put("13",1);
			map.put("16",1);
			map.put("17",1);
		}).start();


        System.out.println(1<<16-1);
        System.out.println((16 << 1) - (16 >>> 1));

		// 15 1111
		// 15 1111 // 15
		System.out.println((16-1)&15);
		// 31 11111
		// 15 1111 // 15
		System.out.println((32-1)&15);
		// 15 1111
		// 16 10000 //0
        System.out.println((16-1)&16);
        // 31 11111
		// 16 10000 //16
        System.out.println((32-1)&16);

		System.out.println(16&1);

		//32 100000
		//20 10100
		System.out.println("sss: "+(32&20));
		//16 10000
		//18 10010
		System.out.println("sss: "+(16&18));
		//1000000000011011
		System.out.println(reSizeStamp(16));
		System.out.println(15>>>1+1+15);
		System.out.println(tableSizeFor(18));
		System.out.println(17>>>2);
		int n = 17-1;
		n |= 17>>>1;
		System.out.println(n);
		int i = tableSizeFor(15 + (15 >>> 1) + 1);

		System.out.println("1::"+tableSizeFor(16 + (16 >>> 1) + 1));

		System.out.println("i:"+i);
		//0000 0000 0000 0000 1000 0000 0001 1011
		System.out.println("reSizeStamp:"+reSizeStamp(16));
		System.out.println("reSizeStamp32:"+reSizeStamp(32));
		System.out.println("reSizeStamp64:"+reSizeStamp(64));
		//-1111 1111 1100 1010 0000 0000 000 0000
		System.out.println(32795<<16);
		System.out.println(12>>>16);
		System.out.println(8>>>3);

		System.out.println(12>>>16);
		//rs << RESIZE_STAMP_SHIFT) + 2
		//2000 0123 0000 0002

		System.out.println((reSizeStamp(16)<<16)+2);
		System.out.println((2149253122L)>>16);
	}

    public static long reSizeStamp(int n){
		return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
	}

	private static int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= (1<<30) ? 1<<30 : n + 1);
	}
}
