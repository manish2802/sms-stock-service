package com.professionalit.kafka.producer;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
    	String key = UUID.randomUUID().toString();
        SendResult<String, String> result;
		try {
			result = kafkaTemplate.send("banking-topic", key, message).get();
			log.info("Producer Key={} Partition={}",
	                key,
	                result.getRecordMetadata().partition());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(
                "Message Sent : " + message);
    }
}