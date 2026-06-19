package com.professionalit.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.professionalit.model.StockPrice;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockPublisherService {

    private final SimpMessagingTemplate messagingTemplate;

    public void publish(StockPrice stockPrice) {
        messagingTemplate.convertAndSend(
                "/topic/stocks",
                stockPrice
        );
    }
}