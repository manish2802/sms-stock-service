package com.professionalit.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.professionalit.model.StockDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisStockService {

	private final RedisTemplate<String, Object> redisTemplate;

	public void saveStock(StockDto stock) {

		redisTemplate.opsForHash().put("STOCKS", stock.symbol(), stock);
	}

	public StockDto getStock(String symbol) {

		return (StockDto) redisTemplate.opsForHash().get("STOCKS", symbol);
	}
}