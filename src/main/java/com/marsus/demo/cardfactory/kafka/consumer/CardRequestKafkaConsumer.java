package com.marsus.demo.cardfactory.kafka.consumer;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import com.marsus.demo.cardfactory.model.UpdateCardRequest;
import com.marsus.demo.cardfactory.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * A kafka consumer class for processing card request messages
 */
@Component
@Slf4j
public class CardRequestKafkaConsumer {

    private final CardService cardService;

    public CardRequestKafkaConsumer(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Process kafka event message. Expects message to represent {@linkplain UpdateCardRequest}.
     * Updates card request status as given in the {@linkplain UpdateCardRequest} for the specified
     * client ID and request ID.
     *
     * @param updateCardRequest {@link UpdateCardRequest}
     * @throws NotFoundException If card request is not found for specified client ID and request ID
     */
    @KafkaListener(topics = "card-request-events", groupId = "group-1",
            containerFactory = "cardRequestKafkaListenerContainerFactory")
    void processEvent(@Payload UpdateCardRequest updateCardRequest) throws NotFoundException {

        if (updateCardRequest == null || updateCardRequest.getRequest() == null) {
            throw new IllegalArgumentException("Update card request data not provided");
        }
        if (updateCardRequest.getClientId() == null) {
            throw new IllegalArgumentException("Client id not provided");
        }
        if (updateCardRequest.getRequest().getRequestId() == null) {
            throw new IllegalArgumentException("Request id not provided");
        }
        if (updateCardRequest.getRequest().getStatus() == null) {
            throw new IllegalArgumentException("Request status not provided");
        }

        log.info("processEvent: received update card request, client ID: {}, request ID: {}, status: {}",
                updateCardRequest.getClientId(),
                updateCardRequest.getRequest().getRequestId(),
                updateCardRequest.getRequest().getStatus());

        cardService.updateCardRequest(updateCardRequest);

        log.info("processEvent: card request update has been processed, client ID: {}, request ID: {}, new status: {}",
                updateCardRequest.getClientId(),
                updateCardRequest.getRequest().getRequestId(),
                updateCardRequest.getRequest().getStatus());
    }
}
