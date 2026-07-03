package com.professionalit.kafka.consumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.professionalit.model.StockDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
	private final SimpMessagingTemplate messagingTemplate;
	private final Map<String, Double> stockPrices = new ConcurrentHashMap<>();
	private final RedisTemplate<String, Object> redisTemplate;

	@PostConstruct
	public void initialize() {
		stockPrices.put("RELIANCE", 0.0);
		stockPrices.put("TCS", 0.0);
		stockPrices.put("INFY", 0.0);
		stockPrices.put("HDFCBANK", 0.0);
		stockPrices.put("ICICIBANK", 0.0);
		messagingTemplate.convertAndSend("/topic/stocks", stockPrices);
		messagingTemplate.convertAndSend("/topic/top-gainers", stockPrices);
		messagingTemplate.convertAndSend("/topic/top-losers", stockPrices);
	}

	@KafkaListener(topics = "stock-topic", groupId = "stock-group", concurrency = "2")
	public void consume(ConsumerRecord<String, String> record, StockDto message) {
		System.out.println(Thread.currentThread().getName() + " -> " + message);
		messagingTemplate.convertAndSend("/topic/stocks", message);
		redisTemplate.opsForHash().put("STOCKS", message.symbol(), message);
	}

	@KafkaListener(topics = "topgainer-stock-topic", groupId = "topgainer-stock-group", concurrency = "2")
	public void consume1(ConsumerRecord<String, String> record, StockDto message) {
		System.out.println(Thread.currentThread().getName() + " -> " + message);
		messagingTemplate.convertAndSend("/topic/top-gainers", message);
		redisTemplate.opsForHash().put("TOP_GAINER_STOCKS", message.symbol(), message);

	}

	@KafkaListener(topics = "toploser-stock-topic", groupId = "toploser-stock-group", concurrency = "2")
	public void consume2(ConsumerRecord<String, String> record, StockDto message) {
		System.out.println(Thread.currentThread().getName() + " -> " + message);
		redisTemplate.opsForHash().put("TOP_LOSER_STOCKS", message.symbol(), message);
		messagingTemplate.convertAndSend("/topic/top-losers", message);

	}
}
