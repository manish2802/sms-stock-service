package com.professionalit.model;

public record StockMaster(
        String symbol,
        String companyName,
        String exchange,
        Double basePrice
) {
}