package com.professionalit.scheduler;

import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.professionalit.model.StockPrice;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockKafkaScheduler {

	private final KafkaTemplate<String, StockPrice> kafkaTemplate;
	private final String[] symbols = { "RELIANCE", "TCS", "INFY", "HDFCBANK", "ICICIBANK" };
	private final Random random = new Random();
	
	//@Scheduled(fixedRate = 2000)
	public void publishPrice() {
		for (String symbol : symbols) {
			double price = 1400 + random.nextInt(100);
			kafkaTemplate.send("stock-topic", symbol, new StockPrice(symbol, price));
		}
	}
}