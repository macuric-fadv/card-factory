package com.marsus.demo.cardfactory.model.mapper;

import com.marsus.demo.cardfactory.model.dto.NewCardRequestDto;
import com.marsus.demo.cardfactory.model.entity.Client;

public class ClientMapper {

    public static Client mapToClient(final NewCardRequestDto newCardRequest) {

        if (newCardRequest == null || newCardRequest.getClient() == null) {
            throw new IllegalArgumentException("New card request client data not provided");
        }
        return Client.builder()
                .firstName(newCardRequest.getClient().getFirstName())
                .lastName(newCardRequest.getClient().getLastName())
                .oib(newCardRequest.getClient().getOib())
                .build();
    }
}
