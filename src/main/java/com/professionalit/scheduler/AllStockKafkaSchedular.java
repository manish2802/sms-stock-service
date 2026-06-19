package com.professionalit.scheduler;

import java.util.List;
import java.util.Random;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.professionalit.model.StockDto;
import com.professionalit.model.StockMaster;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AllStockKafkaSchedular {
	private final KafkaTemplate<String, StockDto> kafkaTemplate;
	private final List<StockMaster> stocks = List.of(new StockMaster("RELIANCE", "Reliance Industries", "NSE", 2950.0),
			new StockMaster("TCS", "Tata Consultancy Services", "NSE", 4200.0),
			new StockMaster("INFY", "Infosys", "NSE", 1500.0), new StockMaster("HDFCBANK", "HDFC Bank", "NSE", 1900.0),
			new StockMaster("ICICIBANK", "ICICI Bank", "NSE", 1450.0),
			new StockMaster("SBIN", "State Bank of India", "NSE", 850.0),
			new StockMaster("LT", "Larsen & Toubro", "NSE", 3700.0), new StockMaster("WIPRO", "Wipro", "NSE", 280.0),
			new StockMaster("TECHM", "Tech Mahindra", "NSE", 1650.0),
			new StockMaster("AAPL", "Apple Inc.", "NASDAQ", 210.0),
			new StockMaster("MSFT", "Microsoft Corp", "NASDAQ", 480.0),
			new StockMaster("GOOGL", "Alphabet Inc.", "NASDAQ", 175.0),
			new StockMaster("AMZN", "Amazon.com", "NASDAQ", 225.0),
			new StockMaster("TSLA", "Tesla Inc.", "NASDAQ", 340.0));
	
	private final Random random = new Random();
	
	@Scheduled(fixedRate = 5000)
	public void publishPrices() {
		stocks.forEach(stock -> {
			double previousPrice = stock.basePrice();
			double change = (random.nextDouble() - 0.5) * 20;
			double currentPrice = previousPrice + change;
			double changePercent = (change / previousPrice) * 100;
			StockDto event = new StockDto(stock.symbol(), stock.companyName(), round(currentPrice),
					round(previousPrice), round(change), round(changePercent), 10000L + random.nextInt(50000),
					stock.exchange(), System.currentTimeMillis());

			kafkaTemplate.send("stock-topic", stock.symbol(), event);
		});
	}

	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
