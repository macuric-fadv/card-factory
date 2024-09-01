package com.marsus.demo.cardfactory.kafka.consumer;

import com.marsus.demo.cardfactory.model.CardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CardRequestKafkaConsumer {

    @KafkaListener(topics = "card-request-events", groupId = "group-1",
            containerFactory = "cardRequestKafkaListenerContainerFactory")
    void processEvent(@Payload CardRequest cardRequest) {

        log.info("processEvent: received new card request, request ID: {}, status: {}",
                cardRequest.getRequestId(), cardRequest.getStatus());
    }
}
