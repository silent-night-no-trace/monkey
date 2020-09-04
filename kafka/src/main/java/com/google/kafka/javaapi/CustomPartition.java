package com.google.kafka.javaapi;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 自定义分区策略
 *
 * @author leon
 * @date 2019-12-13 17:42:30
 */
public class CustomPartition implements Partitioner {

	private Random random = new Random();

	/**
	 * Compute the partition for the given record.
	 *
	 * @param topic The topic name
	 * @param key The key to partition on (or null if no key)
	 * @param keyBytes The serialized key to partition on( or null if no key)
	 * @param value The value to partition on or null
	 * @param valueBytes The serialized value to partition on or null
	 * @param cluster The current cluster metadata
	 */
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		//获取集群中指定topic的所有分区信息
		List<PartitionInfo> partitionerInfoList = cluster.partitionsForTopic(topic);
		int numOfPartition = partitionerInfoList.size();
		int partitionNum = 0;
		//key没有设置
		if (key == null) {
			partitionNum = random.nextInt(numOfPartition);
			partitionNum = Math.abs((value.hashCode())) % numOfPartition;
		}
		System.out.println("key->" + key + ",value->" + value + "->send to partition:" + partitionNum);
		return 0;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
