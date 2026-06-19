package com.professionalit.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.professionalit.kafka.producer.KafkaProducerService;
import com.professionalit.model.StockPrice;
import com.professionalit.service.StockPublisherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

	//private final StockService stockService;
	private final StockPublisherService publisher;
	private final KafkaProducerService producer;

	/**
	 *  API for fetching US stock
	 * @param symbol
	 * @return
	 */
//	@GetMapping("/{symbol}")
//	public StockDto getStock(@PathVariable String symbol) {
//		return stockService.getStock(symbol);
//	}

	@PostMapping("/publish/{price}")
	public String publish(@PathVariable Double price) {
		publisher.publish(new StockPrice("TCS", price * 2));
		publisher.publish(new StockPrice("INFY", price * 3));
		return "Message Published";
	}

	@PostMapping
	public String publish(@RequestParam String message) {
		producer.sendMessage(message);
		return "Message Published";
	}

}
