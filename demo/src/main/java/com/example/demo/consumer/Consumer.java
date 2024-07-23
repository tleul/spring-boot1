package com.example.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "todos", groupId = "todos")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
