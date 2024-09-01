package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.CardRequest;
import com.marsus.demo.cardfactory.model.ClientInfo;
import com.marsus.demo.cardfactory.model.NewCardRequest;
import com.marsus.demo.cardfactory.dao.entity.ClientEntity;

import java.util.List;

/**
 * A client data mapper class handling transitions from one form of client model class to another.
 */
public class ClientMapper {

    /**
     * Map given {@linkplain NewCardRequest} to {@linkplain ClientEntity}.
     *
     * @param newCardRequest {@linkplain NewCardRequest} to map
     * @return {@linkplain ClientEntity} object mapped from the given {@linkplain NewCardRequest}
     */
    public static ClientEntity mapToClientEntity(final NewCardRequest newCardRequest) {

        if (newCardRequest == null) {
            return null;
        }
        return ClientEntity.builder()
                .id(newCardRequest.getClientId())
                .firstName(newCardRequest.getFirstName())
                .lastName(newCardRequest.getLastName())
                .oib(newCardRequest.getOib())
                .build();
    }

    /**
     * Map given {@linkplain ClientEntity} to {@linkplain ClientInfo}.
     *
     * @param clientEntity {@linkplain ClientEntity} to map
     * @return {@linkplain ClientInfo} object mapped from the given {@linkplain ClientEntity}
     */
    public static ClientInfo mapToClientInfo(final ClientEntity clientEntity) {

        if (clientEntity == null) {
            return null;
        }

        List<CardRequest> requests = clientEntity.getCardRequests().stream()
                .map(CardRequestMapper::mapToCardRequest)
                .toList();
        return ClientInfo.builder()
                .clientId(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .oib(clientEntity.getOib())
                .requests(requests)
                .build();
    }
}
