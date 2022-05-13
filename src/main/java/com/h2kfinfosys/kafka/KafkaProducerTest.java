package com.h2kfinfosys.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerTest {

	public static void main(String[] args) {
		String BOOTSTRAP_SERVERS = "localhost:9092";
		String topic = "Mytopic";
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		KafkaProducer<String, String> producer = new  KafkaProducer<String, String>(props);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "","Hello");
		RecordMetadata metadata;
		try {
			metadata = producer.send(record).get();
			 System.out.println("" + record.key() +  record.value()+ metadata.partition()+ metadata.offset());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
       
		producer.close();
	        

	}

}
