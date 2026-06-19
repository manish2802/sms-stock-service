package com.professionalit.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

	@KafkaListener(topics = "banking-topic", groupId = "banking-group")
	public void consume(ConsumerRecord<String, String> record) {
		log.info("Consumer1 Partition={} Value={}", record.partition(), record.value());
		System.out.println("Message   : " + record.value());
		System.out.println("Topic     : " + record.topic());
		System.out.println("Partition : " + record.partition());
		System.out.println("Offset    : " + record.offset());
		System.out.println("Key       : " + record.key());
		System.out.println("Timestamp : " + record.timestamp());
	}
}