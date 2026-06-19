package com.professionalit.model;

import java.io.Serializable;

public record StockDto(
		String symbol,
		String companyName,
		Double currentPrice,
		Double previousPrice,
		Double change,
		Double changePercent,
		Long volume,
		String exchange,
		Long timestamp

) implements Serializable {
}
