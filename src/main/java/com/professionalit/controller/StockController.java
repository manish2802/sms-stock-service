package com.professionalit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.professionalit.kafka.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

	private final KafkaProducerService producer;
	

	@PostMapping
	public String publish(@RequestParam String message) {
		producer.sendMessage(message);
		return "Message Published";
	}


}
