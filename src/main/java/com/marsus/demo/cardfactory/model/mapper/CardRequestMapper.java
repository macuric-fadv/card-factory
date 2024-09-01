package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.*;
import com.marsus.demo.cardfactory.dao.entity.CardRequestEntity;

/**
 * A card request mapper class handling transitions from one form of card request model class to another.
 */
public class CardRequestMapper {

    /**
     * Map given {@linkplain CardRequestEntity} to {@linkplain CardRequest}.
     *
     * @param cardRequestEntity {@linkplain CardRequestEntity} to map
     * @return {@linkplain CardRequest} object mapped from the given {@linkplain CardRequestEntity}
     */
    public static CardRequest mapToCardRequest(CardRequestEntity cardRequestEntity) {

        if (cardRequestEntity == null) {
            return null;
        }

        return CardRequest.builder()
                .requestId(cardRequestEntity.getId())
                .status(cardRequestEntity.getStatus())
                .build();
    }

    /**
     * Map given data to {@linkplain UpdateCardRequest}.
     *
     * @param clientId Client ID
     * @param requestId Card request ID
     * @param updateCardRequest {@linkplain com.marsus.demo.cardfactory.rest.model.UpdateCardRequest} to map
     * @return {@linkplain UpdateCardRequest} object mapped from the given data
     */
    public static UpdateCardRequest mapToUpdateCardRequest(Long clientId, Long requestId,
            com.marsus.demo.cardfactory.rest.model.UpdateCardRequest updateCardRequest) {

        if (clientId == null || requestId == null || updateCardRequest == null) {
            return null;
        }

        CardRequest cardRequest = CardRequest.builder()
                .requestId(requestId)
                .status(Status.valueOf(updateCardRequest.getStatus()))
                .build();
        return UpdateCardRequest.builder()
                .clientId(clientId)
                .request(cardRequest)
                .build();
    }
}
