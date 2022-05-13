package com.h2kfinfosys.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



public class KafkaConsumerTest {

	public static void main(String[] args) {
		String topics[] = {"MyTopic"}; //Don't change the topic name
        String groupId = "MyGroup"; 
        String url = "localhost:9092";
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, url);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String, String> consumer = new  KafkaConsumer<String, String>(props);
        //Subscribing  
        consumer.subscribe(Arrays.asList(topics));  
        //polling  
        while(true){  
        	ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(100));  
        	for(ConsumerRecord<String,String> record: records){  
        		System.out.println("Key: "+ record.key() + ", Value:" +record.value());  
        		System.out.println("Partition:" + record.partition()+",Offset:"+record.offset());  
        	}  
        }
	}

}
